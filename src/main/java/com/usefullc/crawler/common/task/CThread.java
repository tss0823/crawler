package com.usefullc.crawler.common.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by shengshan.tang on 7/29/2015 at 5:35 PM
 */
public class CThread implements  Runnable {

    private final static Logger log = LoggerFactory.getLogger(CThread.class);

    long   startTime;

    int    index;

    private ExecutorService ec;

    private Map<String,Object> paramMap;  //form outer

    private ITaskBizExecute taskBizExecute;  //from outer

    public CThread(int index) {
        this.index = index;
    }

    public void run() {
        startTime = System.currentTimeMillis();
        log.info(""+Thread.currentThread().getName()+" start,index="+index);
        this.taskBizExecute.execute(this);

    }

    public long getStartTime() {
        return startTime;
    }

    public int getIndex() {
        return index;
    }

    public void setTaskBizExecute(ITaskBizExecute taskBizExecute) {
        this.taskBizExecute = taskBizExecute;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public ExecutorService getEc() {
        return ec;
    }

    public void setEc(ExecutorService ec) {
        this.ec = ec;
    }
}
