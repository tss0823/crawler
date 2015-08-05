package com.usefullc.crawler.service.impl.testscript;

import com.usefullc.crawler.common.task.TaskContext;
import com.usefullc.crawler.service.abst.AbstTaskBizExecute;
import com.usefullc.platform.common.utils.JsonUtil;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by shengshan.tang on 8/4/2015 at 6:17 PM
 */
public class TaskBizExecuteImpl2Tel extends AbstTaskBizExecute {

    private final static Logger log = LoggerFactory.getLogger(TaskBizExecuteImpl2Tel.class);

    Queue<String> telQueue = null;

    @Override
    @SuppressWarnings("unchecked")
    public void init(Map<String, Object> initMap) {
        super.init(initMap);
        parseResult = new ConcurrentLinkedQueue<String>();
        telQueue = (Queue<String>) parseResult;
    }
    @Override
    public void afterReq(TaskContext taskContext) {
        try{
            Connection.Response response = taskContext.getResponse();
            Document rootDoc = response.parse();
            String tel = rootDoc.select("div[tel]").attr("tel");
            telQueue.offer(tel);
            log.info("tel="+tel);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void beforeReq(TaskContext taskContext) {
        String key = taskContext.getcThread().getParamMap().get("id").toString();
        String value = taskContext.getcThread().getParamMap().get("url").toString();
        int start = value.lastIndexOf("/")+1;
        int end = value.indexOf("?");
        String zanid = value.substring(start,end);
        String url = "http://w.58.com/poster/zpdetail2/"+key+"?zanid="+zanid+"&source=weixin&from=wzp_pcqy";
        taskContext.getcThread().getParamMap().put("url",url);
    }

    @Override
    public List<Map<String, Object>> getBeforeReqParamList() {
        List<Map<String, Object>> reqParamList = new java.util.ArrayList<Map<String,Object>>();
        Map<String,String> urlMap = JsonUtil.toObj(parseContent, Map.class);
        Set<Map.Entry<String,String>> set = urlMap.entrySet();
        for(Map.Entry<String,String> entry : set){
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("id",entry.getKey());
            paramMap.put("url",entry.getValue());
            reqParamList.add(paramMap);
        }
        return reqParamList;
    }



    @Override
    public void terminated() {
        log.info("telQueue size=" + telQueue.size());
        super.terminated();
    }



}
