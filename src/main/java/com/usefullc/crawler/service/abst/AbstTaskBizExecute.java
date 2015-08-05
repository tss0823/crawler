package com.usefullc.crawler.service.abst;

import com.usefullc.crawler.common.http.HttpHelper;
import com.usefullc.crawler.common.http.ReqParam;
import com.usefullc.crawler.common.task.CThread;
import com.usefullc.crawler.common.task.ITaskBizExecute;
import com.usefullc.crawler.common.task.TaskContext;
import com.usefullc.crawler.domain.HttpRes;
import com.usefullc.crawler.domain.ParseContent;
import com.usefullc.crawler.service.IHttpResService;
import com.usefullc.crawler.service.IParseContentService;
import com.usefullc.crawler.service.ITaskExecuteService;
import com.usefullc.platform.common.utils.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by shengshan.tang on 8/5/2015 at 2:04 PM
 */
public abstract class AbstTaskBizExecute implements ITaskBizExecute {

    private final static Logger log = LoggerFactory.getLogger(AbstTaskBizExecute.class);

    private ApplicationContext applicationContext;      //from outer

    private Map<String,Object> initMap;   //from outer

    private IHttpResService httpResService;  //builder from system

    private IParseContentService parseContentService;  //builder from system

    private Long taskTpId;  //from initMap

    private Long taskInstId;  //from initMap

    protected  Object parseResult;

    protected  String parseContent;


    public void init(Map<String,Object> initMap) {
        this.initMap = initMap;
        this.applicationContext = (ApplicationContext) this.initMap.get("applicationContext");
        this.parseContent = this.initMap.get("parseContent").toString();
        this.taskTpId = (Long) this.initMap.get("taskTpId");
        this.taskInstId = (Long) this.initMap.get("taskInstId");
        httpResService = (IHttpResService) applicationContext.getBean("httpResService");
        parseContentService = (IParseContentService) applicationContext.getBean("parseContentService");

    }

    public void execute(CThread cThread) {
        //
        try{
            TaskContext taskContext = new TaskContext();
            taskContext.setcThread(cThread);
            beforeReq(taskContext);   //before biz execute

            String url = cThread.getParamMap().get("url").toString();
            Object agentType = cThread.getParamMap().get("agentType");
            ReqParam reqParam = new ReqParam();
            if(agentType != null){
                reqParam.setAgentType(agentType.toString());
            }
            Connection.Response response =  HttpHelper.req(url,reqParam);

            //insert http_res
            HttpRes httpRes = new HttpRes();
            httpRes.setStatus(response.statusMessage());
            httpRes.setTaskTpId(taskTpId);
            httpRes.setTaskInstId(taskInstId);
            httpRes.setUrl(url);
            String jsonHeaders = JsonUtil.toStr(response.headers());
            httpRes.setHeader(jsonHeaders);
            httpRes.setBody(response.body());
            httpResService.insertHttpRes(httpRes);

            taskContext.setResponse(response);
            taskContext.setUrl(url);
            afterReq(taskContext);   //after biz execute

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public abstract void afterReq(TaskContext taskContext);
    public abstract void beforeReq(TaskContext taskContext);

    public void afterExecute(CThread cThread) {
        //

    }

    public List<Map<String, Object>> getBeforeReqParamList() {
        return null;
    }

    public void terminated() {
        //save to db
        if(parseResult == null){
            return;
        }
        String jsonReslut = JsonUtil.toStr(this.parseResult);
        int len = StringUtils.length(jsonReslut);
        log.info("parse content str len="+len);
        ParseContent parseContent = new ParseContent();
        parseContent.setContent(jsonReslut);
        parseContent.setTaskTpId(taskTpId);
        parseContent.setTaskInstId(taskTpId);
        parseContentService.insertParseContent(parseContent);
    }

}
