package com.usefullc.crawler.service.impl.testscript;

import com.usefullc.crawler.common.task.TaskContext;
import com.usefullc.crawler.service.abst.AbstTaskBizExecute;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by shengshan.tang on 8/4/2015 at 6:17 PM
 */
public class TaskBizExecuteImpl extends AbstTaskBizExecute {

    private final static Logger log = LoggerFactory.getLogger(TaskBizExecuteImpl.class);

    Queue<String> urlQueue = null;

    @Override
    public void init(Map<String, Object> initMap) {
        super.init(initMap);
        parseResult = new ConcurrentLinkedQueue<String>();
        urlQueue = (Queue<String>) parseResult;
    }

    @Override
    public void afterReq(TaskContext taskContext) {
        try{
            Connection.Response response = taskContext.getResponse();
            Document rootDoc = response.parse();
            Elements elements = rootDoc.select("div.compList").select("a[href]");
            for (Element ele : elements) {
                urlQueue.add(ele.attr("href"));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void beforeReq(TaskContext taskContext) {

    }

    @Override
    public void terminated() {
        log.info("urlQueue size="+urlQueue.size());
        super.terminated();
    }
}
