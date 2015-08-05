package com.usefullc.crawler.common.task;

import org.jsoup.Connection;

/**
 * Created by shengshan.tang on 8/5/2015 at 2:54 PM
 * 任务上下文
 */
public class TaskContext {

    private CThread cThread;

    private String url;

    private Connection.Response response;

    public CThread getcThread() {
        return cThread;
    }

    public void setcThread(CThread cThread) {
        this.cThread = cThread;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Connection.Response getResponse() {
        return response;
    }

    public void setResponse(Connection.Response response) {
        this.response = response;
    }
}
