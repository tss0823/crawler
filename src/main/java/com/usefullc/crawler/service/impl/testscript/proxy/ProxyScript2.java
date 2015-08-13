package com.usefullc.crawler.service.impl.testscript.proxy;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by shengshan.tang on 8/3/2015 at 3:19 PM
 */
public class ProxyScript2 {

    public List<String> execute(Map<String,Object> paramMap){
        System.out.println("testscript ok!");
        try{
            Connection.Response response = (Connection.Response) paramMap.get("response");
            Document rootDoc = response.parse();
            Elements eles = Jsoup.parse(response.body()).select("#ip_list").select("tr");
            List<String> proxyList = new ArrayList();
            for(Element ele : eles){
                if(ele.select("h2").size() > 0){
                    System.out.println(ele.select("h2").text());
                    continue;
                }else if(ele.select("th").size() > 0){
                    continue;
                }
                String host = ele.select("td").get(1).text().trim();
                String port = ele.select("td").get(2).text().trim();
                proxyList.add(host+":"+port);

            }
            return proxyList;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
