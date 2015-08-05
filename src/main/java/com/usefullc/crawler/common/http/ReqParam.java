package com.usefullc.crawler.common.http;

import java.io.Serializable;

/**
 * Created by shengshan.tang on 8/4/2015 at 2:26 PM
 */
public class ReqParam implements Serializable {
    private String agentType;  //pc,mobile

    private String cookies;  //

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }
}
