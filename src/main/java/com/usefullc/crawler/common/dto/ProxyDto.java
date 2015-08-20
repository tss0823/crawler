package com.usefullc.crawler.common.dto;

import com.usefullc.crawler.domain.Proxy;

/**
 * Created by shengshan.tang on 8/18/2015 at 11:21 AM
 */
public class ProxyDto extends Proxy {

    private long time;  //开销时间

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
