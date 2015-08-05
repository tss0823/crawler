package com.usefullc.crawler.common.http;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by shengshan.tang on 8/4/2015 at 2:18 PM
 */
public class HttpHelper {

    private static final String agent          = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2194.2 Safari/537.36";
    private static final String mobile_agent   = "Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn; MI 4LTE Build/KTU84P) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025411 Mobile Safari/533.1 MicroMessenger/6.1.0.73_r1097298.543 NetType/WIFI";
    private static final String acceptEncoding = "gzip, deflate, sdch";
    private static final String acceptLanguage = "zh-CN,zh;q=0.8,en;q=0.6";
    private static final String accept         = "application/vnd.wap.xhtml+xml, text/vnd.wap.wml, application/xhtml+xml, text/html, image/png, image/jpeg, image/gif, */*;q=0.1";


    public static Connection.Response req(String url){
        try{
            Connection.Response response = Jsoup.connect(url).userAgent(agent)
                    .timeout(10000).execute();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static Connection.Response req(String url,ReqParam reqParam){
        if(reqParam == null){
            return req(url);
        }
        try{
            String agentType = reqParam.getAgentType();
            String thisAgent = agent;
            if(StringUtils.equals(agentType,"mobile")){
                thisAgent = mobile_agent;
            }
            Connection.Response response = Jsoup.connect(url).userAgent(thisAgent)
                    .timeout(10000).execute();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
