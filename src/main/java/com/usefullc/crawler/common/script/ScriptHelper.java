package com.usefullc.crawler.common.script;

import com.usefullc.platform.common.utils.MD5Utils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shengshan.tang on 8/3/2015 at 2:10 PM
 * 脚本
 */
public class ScriptHelper {

    private final static Logger log = LoggerFactory.getLogger(ScriptHelper.class);


    public static Class takeClass(String scriptContent, String libPath) {
        try {
            //
            String folder = System.getProperty("java.io.tmpdir");
//            String fileId = UUID.randomUUID().toString();
            String basePath = folder + "crawler";
            String sourceFilePath = basePath + File.separator + "source";
            if (new File(sourceFilePath).mkdir()) {
                log.info("init create base source folder");
            }
            String fileId = "Script_" + MD5Utils.toMD5(scriptContent);
            String filePath = sourceFilePath + File.separator + fileId + ".java";
            log.info("filePath=" + filePath);

            //operate source java
            //获取package
            int startIndex = scriptContent.indexOf("package");
            int endIndex = scriptContent.indexOf(";", startIndex);
            String packageStr = scriptContent.substring(startIndex + 7, endIndex).trim();
            log.info("packageStr=" + packageStr);

            //get className
            startIndex = scriptContent.indexOf("class");
            endIndex = scriptContent.indexOf("{", startIndex);
            String className = scriptContent.substring(startIndex + 5, endIndex).trim();
            String [] clsStrs = className.split("\\s+");
            if(clsStrs.length > 1){
                className = clsStrs[0];
            }
            log.info("className="+className);

            scriptContent = scriptContent.replaceAll(className, fileId);   //class name replace
            FileUtils.write(new File(filePath), scriptContent, "utf-8");


            //compiler
            String classFilePath = basePath + File.separator + "classs";
            if (new File(classFilePath).mkdir()) {
                log.info("init create class folder");
            }

            String thisBaseClassPath = Thread.currentThread().getContextClassLoader().getResource(".").getPath();
            String thisClassPath = thisBaseClassPath.substring(1);
            if (StringUtils.isEmpty(libPath)) {
                libPath = thisBaseClassPath;
                libPath = libPath.replace("classes", "lib"); //
                libPath = libPath.substring(1);
            }
            log.info("libPath=" + libPath);

            String classPath = "$CLASS_PATH";
            if (SystemUtils.OS_NAME.startsWith("Windows")) {
                classPath = "%CLASS_PATH%";
            }

            String cmd = "javac -g  -encoding UTF8 -Djava.ext.dirs=" + libPath + " -cp " +thisClassPath+";"+ classPath + " -sourcepath " + sourceFilePath + " -d " + classFilePath + " " + filePath+" -Xlint:unchecked";
            log.info("cmd=" + cmd);

            //exeuce compiler
            Process process = Runtime.getRuntime().exec(cmd);
            int exitValue = process.waitFor();
            log.info("exitValue=" + exitValue);
            List<String> errList = IOUtils.readLines(process.getErrorStream(), "GBK");
            if (CollectionUtils.isNotEmpty(errList)) {
                String errMsg = StringUtils.join(errList, "\r\n");
                log.error("javac execute error! \r\n" + errMsg);
            }

            //class loader
            ScriptClassLoader classLoader = new ScriptClassLoader(classFilePath);
            Class scriptClass = classLoader.findClass(packageStr + "." + fileId);
           return scriptClass;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ScriptResult execute(String scriptContent, String libPath, Map<String, Object> paramMap) {
        ScriptResult scriptResult = new ScriptResult();
        try {
            Class scriptClass = takeClass(scriptContent, libPath);
            Object instance = scriptClass.newInstance();
            Method method = scriptClass.getDeclaredMethod("execute", Map.class);

            Object result = method.invoke(instance, paramMap);
            scriptResult.setResult(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return scriptResult;
    }

    public static ScriptResult execute(String scriptContent, Map<String, Object> paramMap) {
        return execute(scriptContent, null, paramMap);
    }

    public static void main(String[] args) {
        String sourceStr = "package com.usefullc.crawler.common.script;\n" +
                "\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n" +
                "import java.util.Set;\n" +
                "\n" +
                "/**\n" +
                " * Created by shengshan.tang on 8/3/2015 at 3:19 PM\n" +
                " */\n" +
                "public class Script {\n" +
                "\n" +
                "    public Map<String,Object> execute(Map<String,Object> paramMap){\n" +
                "        System.out.println(\"testscript ok!\");\n" +
                "        if(paramMap != null && !paramMap.isEmpty()){\n" +
                "            Set<Map.Entry<String,Object>> entrySet = paramMap.entrySet();\n" +
                "            for(Map.Entry<String,Object>  entry : entrySet){\n" +
                "                System.out.println(entry.getKey()+\"=\"+entry.getValue());\n" +
                "            }\n" +
                "        }\n" +
                "        Map<String,Object> resultMap = new HashMap<String, Object>();\n" +
                "        resultMap.put(\"testscript\",\"ok\");\n" +
                "        return resultMap;\n" +
                "    }\n" +
                "}\n";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", "tangshengshan");
        paramMap.put("sex", "男");
        ScriptResult scriptResult = execute(sourceStr, paramMap);
        Map<String, Object> methodReturnMap = (Map<String, Object>) scriptResult.getResult();
        System.out.println(methodReturnMap.get("test"));
    }
}
