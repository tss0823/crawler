package com.usefullc.crawler.common.http;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.ProxyInfo;

import java.util.*;

/**
 * Created by shengshan.tang on 8/4/2015 at 2:18 PM
 */
public class HttpHelper {

    private static final String agent          = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2194.2 Safari/537.36";
    private static final String mobile_agent   = "Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn; MI 4LTE Build/KTU84P) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025411 Mobile Safari/533.1 MicroMessenger/6.1.0.73_r1097298.543 NetType/WIFI";
    private static final String acceptEncoding = "gzip, deflate, sdch";
    private static final String acceptLanguage = "zh-CN,zh;q=0.8,en;q=0.6";
    private static final String accept         = "application/vnd.wap.xhtml+xml, text/vnd.wap.wml, application/xhtml+xml, text/html, image/png, image/jpeg, image/gif, */*;q=0.1";

    private static final int defaultTimeout = 10000;

    private static final List<String> pcAgentList = new java.util.ArrayList<String>();
    private static final List<String> mobileAgentList = new java.util.ArrayList<String>();

    static{

        pcAgentList.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");
        pcAgentList.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");
        pcAgentList.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0");
        pcAgentList.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)");
        pcAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)");
        pcAgentList.add("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        pcAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1");
        pcAgentList.add("Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1");
        pcAgentList.add("Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11");
        pcAgentList.add("Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11");
        pcAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
        pcAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)");
        pcAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)");
        pcAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
        pcAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)");
        pcAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)");
        pcAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)");

        mobileAgentList.add("Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");
        mobileAgentList.add("Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");
        mobileAgentList.add("Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");
        mobileAgentList.add("Mozilla/5.0 (Linux; U; Android 2.3.7; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
        mobileAgentList.add("MQQBrowser/26 Mozilla/5.0 (Linux; U; Android 2.3.7; zh-cn; MB200 Build/GRJ22; CyanogenMod-7) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
        mobileAgentList.add("Mozilla/5.0 (BlackBerry; U; BlackBerry 9800; en) AppleWebKit/534.1+ (KHTML, like Gecko) Version/6.0.0.337 Mobile Safari/534.1+");
        mobileAgentList.add("Mozilla/5.0 (BlackBerry; U; BlackBerry 9800; en) AppleWebKit/534.1+ (KHTML, like Gecko) Version/6.0.0.337 Mobile Safari/534.1+");
        mobileAgentList.add("Mozilla/5.0 (SymbianOS/9.4; Series60/5.0 NokiaN97-1/20.0.019; Profile/MIDP-2.1 Configuration/CLDC-1.1) AppleWebKit/525 (KHTML, like Gecko) BrowserNG/7.1.18124");
    }


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
            int timeout = defaultTimeout;  //default
            if(reqParam.getTimeout() != null){
                timeout = reqParam.getTimeout();
            }
            String userAgent = reqParam.getUserAgent();  //自定义最高级
            if(StringUtils.isEmpty(userAgent)){  //如果没有自定义，就用默认的
                String agentType = reqParam.getAgentType();
                Random random = new Random();
                if(StringUtils.equals(agentType,"mobile")){  //mobile agent
                    int index = random.nextInt(mobileAgentList.size());
                    userAgent = mobileAgentList.get(index);
                }else{  //pc agent
                    int index = random.nextInt(pcAgentList.size());
                    userAgent = pcAgentList.get(index);
                }
            }

            Connection.Response response = null;
            Connection connection = Jsoup.connect(url).userAgent(userAgent)
                    .timeout(timeout);
            if(reqParam.getReferrer() != null){
                connection.referrer(reqParam.getReferrer());
            }
            if(reqParam.getMethod() != null){
                connection.method(reqParam.getMethod());
            }
            if(reqParam.getCookies() != null){
                connection.cookies(reqParam.getCookies());
            }
            if(reqParam.getHeaders() != null){
                Set<Map.Entry<String,String>> set = reqParam.getHeaders().entrySet();
                for(Map.Entry<String,String> entry : set){
                    String key = entry.getKey();
                    String value = entry.getValue();
                    connection.header(key,value);
                }
            }
            if(reqParam.getData() != null){
                connection.data(reqParam.getData());
            }
            ProxyInfo  proxyInfo = reqParam.getProxyInfo();
            if(proxyInfo != null){
                connection = connection.proxy(proxyInfo.getIp(), proxyInfo.getPort());
            }
            response = connection.execute();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
