package com.usefullc.crawler.service.impl;

import com.usefullc.crawler.common.dto.ProxyDto;
import com.usefullc.crawler.common.dto.TaskExecuteDto;
import com.usefullc.crawler.common.dto.TaskTplDto;
import com.usefullc.crawler.common.http.HttpHelper;
import com.usefullc.crawler.common.http.ReqParam;
import com.usefullc.crawler.common.script.ScriptHelper;
import com.usefullc.crawler.common.script.ScriptResult;
import com.usefullc.crawler.common.task.CThread;
import com.usefullc.crawler.common.task.ITaskBizExecute;
import com.usefullc.crawler.common.task.TaskExecuteHelper;
import com.usefullc.crawler.domain.*;
import com.usefullc.crawler.service.*;
import com.usefullc.crawler.service.abst.AbstTaskBizExecute;
import com.usefullc.platform.common.utils.JsonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Connection;
import org.jsoup.helper.ProxyInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by shengshan.tang on 7/29/2015 at 11:01 AM
 */
@Service
public class TaskExecuteServiceImpl implements ITaskExecuteService,ApplicationContextAware {

    private final static Logger log = LoggerFactory.getLogger(TaskExecuteServiceImpl.class);

    @Autowired
    private ITaskInstanceService taskInstanceService;

    @Autowired
    private ITaskTemplateService taskTemplateService;

    @Autowired
    private IHttpResService httpResService;

    @Autowired
    private IParseContentService parseContentService;

    @Value("${libPath}")
    private String libPath;

    private ApplicationContext applicationContext;


    @Transactional
    public TaskExecuteDto start(Long taskTpId){
        TaskTplDto dto = taskTemplateService.getDtoById(taskTpId);
        TaskTemplate taskTemplate = dto.getTaskTemplate();
        List<TaskTpParam> taskTpParamList = dto.getTaskTpParamList();

        //创建任务实例
        TaskInstance taskInstance = new TaskInstance();
        taskInstance.setTaskTpId(taskTemplate.getId());
        taskInstance.setName(taskTemplate.getName());
        taskInstance.setType("");
        taskInstance.setStatus("start");
        taskInstanceService.insertTaskInstance(taskInstance);

        //http request
        //get url
        String url = null;
        for(TaskTpParam taskTpParam : taskTpParamList){
            if(taskTpParam.getKey().equals("url")){
                url  = taskTpParam.getValue();
                break;
            }
        }
        Connection.Response response = HttpHelper.req(url);

        //insert http_res
        HttpRes httpRes = new HttpRes();
        httpRes.setStatus(response.statusMessage());
        httpRes.setTaskTpId(taskTpId);
        httpRes.setTaskInstId(taskInstance.getId());
        httpRes.setUrl(url);
        String jsonHeaders = JsonUtil.toStr(response.headers());
        httpRes.setHeader(jsonHeaders);
        httpRes.setBody(response.body());
        httpResService.insertHttpRes(httpRes);


        //execute script
        Script script = dto.getScript();
        Map<String,Object> scriptMap = new HashMap<String,Object>();
        scriptMap.put("response",response);
        ScriptResult scriptResult = ScriptHelper.execute(script.getContent(), libPath,scriptMap);

        //save parse content
        String jsonReslut = JsonUtil.toStr(scriptResult.getResult());
        ParseContent parseContent = new ParseContent();
        parseContent.setContent(jsonReslut);
        parseContent.setTaskTpId(taskTpId);
        parseContent.setTaskInstId(taskInstance.getId());
        parseContentService.insertParseContent(parseContent);

        TaskExecuteDto taskExecuteDto = new TaskExecuteDto();
        taskExecuteDto.setScriptResult(scriptResult);
        return taskExecuteDto;

    }
    @Transactional
    public TaskExecuteDto startMulti(Long taskTpId,Long parseContentId,List<Proxy> proxyList){
        TaskTplDto dto = taskTemplateService.getDtoById(taskTpId);
        TaskTemplate taskTemplate = dto.getTaskTemplate();
        List<TaskTpParam> taskTpParamList = dto.getTaskTpParamList();
        //获取模板参数
        Map<String,Object> paramMap = new HashMap<String, Object>();
//        Properties properties = new Properties();
        if(CollectionUtils.isNotEmpty(taskTpParamList)){
            for(TaskTpParam taskTpParam : taskTpParamList){
                paramMap.put(taskTpParam.getKey(),taskTpParam.getValue());
            }
        }

        //创建任务实例
        TaskInstance taskInstance = new TaskInstance();
        taskInstance.setTaskTpId(taskTemplate.getId());
        taskInstance.setName(taskTemplate.getName());
        taskInstance.setType("");
        taskInstance.setStatus("start");
        taskInstanceService.insertTaskInstance(taskInstance);

        //get parseContent
        ParseContent parseContent = parseContentService.getParseContent(parseContentId);

        //execute script
        Script script = dto.getScript();
        Map<String,Object> initMap = new HashMap<String,Object>();
        Class takeClass = ScriptHelper.takeClass(script.getContent(), libPath);
        ITaskBizExecute taskBizExecute = null;
        try {
            taskBizExecute = (ITaskBizExecute) takeClass.newInstance();
        } catch (Exception  e) {
            throw new RuntimeException(e);
        }
        //builder task init param
        initMap.put("applicationContext",applicationContext);
        initMap.put("parseContent",parseContent.getContent());
        initMap.put("taskTpId",taskTpId);
        initMap.put("taskInstId",taskInstance.getId());
        initMap.put("proxyList",proxyList);
        taskBizExecute.init(initMap);
        List<Map<String,Object>> reqParamList = taskBizExecute.getBeforeReqParamList();

        //multi http request
        List<CThread> taskList = new java.util.ArrayList<CThread>();
        Integer corePoolSize = Integer.valueOf(paramMap.get("corePoolSize").toString());
        Integer maximumPoolSize = Integer.valueOf(paramMap.get("maximumPoolSize").toString());

        if(CollectionUtils.isEmpty(reqParamList)){  //默认解析出来的url list 集合
            List<String> urlList = JsonUtil.toObj(parseContent.getContent(),List.class);
            for(int i = 0; i < urlList.size(); i++){
                String url = urlList.get(i);
                Map<String,Object> taskParamMap = new HashMap<String, Object>();
                taskParamMap.put("url",url);
                CThread myThread = new CThread(i);
                myThread.setParamMap(taskParamMap);
                taskList.add(myThread);
            }
        }else{    //脚本处理参数,获取上一次解析的内容
            for(int i = 0; i < reqParamList.size(); i++){
                Map<String,Object> taskParamMap = reqParamList.get(i);
                CThread myThread = new CThread(i);
                myThread.setParamMap(taskParamMap);
                taskList.add(myThread);
            }
        }

        //multi task execute
        TaskExecuteHelper.execute(corePoolSize,maximumPoolSize,taskList,taskBizExecute);

        //builder return
        TaskExecuteDto taskExecuteDto = new TaskExecuteDto();
        ScriptResult scriptResult = new ScriptResult();
        scriptResult.setResult("ok");
        taskExecuteDto.setScriptResult(scriptResult);
        return taskExecuteDto;

    }

    public void checkReqUrl(final String url,final ReqParam reqParam,Integer taskNum,Integer corePoolSize ){
        log.info("taskNum="+taskNum);
        List<CThread> taskList = new java.util.ArrayList<CThread>(taskNum);
        for(int i = 0; i < taskNum; i++){
            CThread myThread = new CThread(i);
            Map<String,Object> taskParamMap = new HashMap<String, Object>();
            myThread.setParamMap(taskParamMap);
            taskList.add(myThread);
        }
        TaskExecuteHelper.execute(corePoolSize, corePoolSize, taskList, new ITaskBizExecute() {
            public void init(Map<String, Object> initMap) {

            }

            public void execute(CThread cThread) {
                Connection.Response response = HttpHelper.req(url,reqParam);
                if(response.statusCode() == 200){

                }
            }

            public void afterExecute(CThread cThread) {

            }

            public void terminated() {

            }

            public List<Map<String, Object>> getBeforeReqParamList() {
                return null;
            }
        });

    }

    public List<ProxyDto> checkHighQualityProxy(final String url, List<Proxy> proxyList) {
        int size = proxyList.size();
        log.info("proxy size="+size);
        List<CThread> taskList = new java.util.ArrayList<CThread>(size);
        for(int i = 0; i < proxyList.size(); i++){
            Proxy proxy = proxyList.get(i);
            CThread myThread = new CThread(i);
            Map<String,Object> taskParamMap = new HashMap<String, Object>();
            taskParamMap.put("proxy",proxy);
            myThread.setParamMap(taskParamMap);
            taskList.add(myThread);
        }
        final List<ProxyDto> proxyDtoList = new CopyOnWriteArrayList<ProxyDto>();

        TaskExecuteHelper.execute(2, 2, taskList, new ITaskBizExecute() {
            public void init(Map<String, Object> initMap) {

            }

            public void execute(CThread cThread) {
                Map<String,Object> taskParamMap = cThread.getParamMap();
                Proxy proxy = (Proxy) taskParamMap.get("proxy");
//                System.setProperty("proxySet", "true");
//                System.setProperty("http.proxyHost", proxy.getIp());
//                System.setProperty("http.proxyPort", String.valueOf(proxy.getPort()));
                ReqParam reqParam = new ReqParam();
                reqParam.setTimeout(15000);
                reqParam.setProxyInfo(new ProxyInfo(proxy.getIp(),proxy.getPort()));
                Connection.Response response = HttpHelper.req(url,reqParam);
                if(response.statusCode() == 200){
                    long time = System.currentTimeMillis() - cThread.getStartTime();
                    ProxyDto proxyDto = new ProxyDto();
                    proxyDto.setIp(proxy.getIp());
                    proxyDto.setPort(proxy.getPort());
                    proxyDto.setTime(time);
                    proxyDtoList.add(proxyDto);

                }
            }

            public void afterExecute(CThread cThread) {
            }

            public void terminated() {
                //排序
                ProxyDto proxyDtoArr [] = proxyDtoList.toArray(new ProxyDto[]{});
                Arrays.sort(proxyDtoArr,new Comparator<ProxyDto>(){
                    public int compare(ProxyDto o1, ProxyDto o2) {
                        return (int)(o1.getTime() - o2.getTime());
                    }
                });

                //打印
                log.info("after sort size="+proxyDtoArr.length);
                for(ProxyDto proxyDto : proxyDtoArr){
                    log.info(proxyDto.getIp()+":"+proxyDto.getPort()+" "+proxyDto.getTime());
                }
            }

            public List<Map<String, Object>> getBeforeReqParamList() {
                return null;
            }
        });
        return proxyDtoList;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
