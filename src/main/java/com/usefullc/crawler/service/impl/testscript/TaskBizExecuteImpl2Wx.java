package com.usefullc.crawler.service.impl.testscript;

import com.usefullc.crawler.common.task.TaskContext;
import com.usefullc.crawler.service.abst.AbstTaskBizExecute;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shengshan.tang on 8/4/2015 at 6:17 PM
 */
public class TaskBizExecuteImpl2Wx extends AbstTaskBizExecute {

    private final static Logger log = LoggerFactory.getLogger(TaskBizExecuteImpl2Wx.class);

    Map<String,String> urlMap = null;

    @Override
    @SuppressWarnings("unchecked")
    public void init(Map<String, Object> initMap) {
        super.init(initMap);
        parseResult = new ConcurrentHashMap<String, String>();
        urlMap = (Map<String, String>) parseResult;
    }
    @Override
    public void afterReq(TaskContext taskContext) {
        try{
            Connection.Response response = taskContext.getResponse();
            Document rootDoc = response.parse();
            String localtion = rootDoc.location();
            String id = taskContext.getcThread().getParamMap().get("id").toString();
            urlMap.put(id,localtion);
            log.info("location=" + localtion);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void beforeReq(TaskContext taskContext) {
        String url = taskContext.getcThread().getParamMap().get("url").toString();
        int start = url.lastIndexOf("/", url.length() - 2);
        String id = url.substring(start+1);
        if(id.indexOf("/") != -1){
            id = id.substring(0,id.length()-1);
        }
        url = "http://w.58.com/waitload/"+id+"/1002?from=wzp_pcqy&version=v1&send=true";
        taskContext.getcThread().getParamMap().put("url",url);
        taskContext.getcThread().getParamMap().put("id",id);
    }


    @Override
    public void terminated() {
        log.info("urlMap size="+urlMap.size());
        super.terminated();
    }



}
