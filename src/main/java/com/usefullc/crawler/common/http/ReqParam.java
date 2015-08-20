package com.usefullc.crawler.common.http;

import org.jsoup.Connection;
import org.jsoup.helper.ProxyInfo;

import java.io.Serializable;
import java.util.*;

/**
 * Created by shengshan.tang on 8/4/2015 at 2:26 PM
 */
public class ReqParam implements Serializable {

    private String userAgent;

    private Integer timeout;  //超时

    private String agentType;  //pc,mobile

    private Map<String,String> cookies;  //

    private Map<String,String> data;

    private Map<String,String> headers;

    private String referrer;

    private Connection.Method method;

    private ProxyInfo proxyInfo;

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }



    public ProxyInfo getProxyInfo() {
        return proxyInfo;
    }

    public void setProxyInfo(ProxyInfo proxyInfo) {
        this.proxyInfo = proxyInfo;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public Connection.Method getMethod() {
        return method;
    }

    public void setMethod(Connection.Method method) {
        this.method = method;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
