package com.usefullc.crawler.common.script;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 脚本加载类
 * Created by shengshan.tang on 8/3/2015 at 3:52 PM
 */
public class ScriptClassLoader extends  ClassLoader {

    String classTargetPath;



    public ScriptClassLoader(String classTargetPath) {
        super(ScriptClassLoader.class.getClassLoader());
        this.classTargetPath = classTargetPath;
    }

    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        try {
            byte bytes[] = getClassBytes(name);
            Class thisClass = defineClass(name,bytes,0,bytes.length);
            return thisClass;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte [] getClassBytes(String className) throws IOException {
        //builder class file path
        className = className.replace('.', File.separatorChar) + ".class";
        String classFilePath = classTargetPath +File.separator+className;
        byte [] bytes = FileUtils.readFileToByteArray(new File(classFilePath));
        return bytes;

    }





}
