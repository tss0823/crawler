package com.usefullc.crawler.common.script;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by shengshan.tang on 8/3/2015 at 3:19 PM
 */
public class Script2 {

    public List<String> execute(Map<String,Object> paramMap){
        System.out.println("testscript ok!");
        try{
            Connection.Response response = (Connection.Response) paramMap.get("response");
            Document rootDoc = response.parse();
            Elements elements = rootDoc.select("div.compList").select("a[href]");
            List<String> urlList = new java.util.ArrayList<String>();
            for (Element ele : elements) {
                //            System.out.println(ele.attr("href")+"="+ele.text());
                urlList.add(ele.attr("href"));
            }
            return urlList;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
