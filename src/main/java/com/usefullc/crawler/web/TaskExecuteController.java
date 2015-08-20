package com.usefullc.crawler.web;

import com.usefullc.crawler.common.dto.ProxyDto;
import com.usefullc.crawler.common.dto.TaskExecuteDto;
import com.usefullc.crawler.domain.Proxy;
import com.usefullc.crawler.service.ITaskExecuteService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.util.*;

/**
 * Created by shengshan.tang on 8/4/2015 at 2:05 PM
 */
@Controller
@RequestMapping(value="/taskExecute")
public class TaskExecuteController  extends BaseController {

    @Autowired
    private ITaskExecuteService taskExecuteService;

    /**
     * 任务开始
     * @param taskTpId
     * @return
     */
    @RequestMapping(value = "/start.htm")
    @ResponseBody
    public TaskExecuteDto start(@RequestParam Long taskTpId){
        TaskExecuteDto taskExecuteDto = taskExecuteService.start(taskTpId);
        return taskExecuteDto;
    }
    /**
     * 任务开始
     * @param taskTpId
     * @param proxys  格式（xxx.xx.xx.xx:yy,....）
     * @return
     */
    @RequestMapping(value = "/startMulti.htm")
    @ResponseBody
    public TaskExecuteDto startMulti(@RequestParam Long taskTpId,@RequestParam Long parseContentId,
                                     @RequestParam(required = false) List<String> proxys){
        List<Proxy> proxyList = new java.util.ArrayList<Proxy>();
        if(CollectionUtils.isNotEmpty(proxys)){
            for(String proxyStr : proxys){
                String ip = proxyStr.split(":")[0];
                String port = proxyStr.split(":")[1];
                Proxy proxy = new Proxy();
                proxy.setIp(ip);
                proxy.setPort(Integer.valueOf(port));
                proxyList.add(proxy);
            }
        }
        TaskExecuteDto taskExecuteDto = taskExecuteService.startMulti(taskTpId,parseContentId,proxyList);
        return taskExecuteDto;
    }

    @RequestMapping(value = "/checkHighQualityProxy.htm")
    @ResponseBody
    public List<ProxyDto> checkHighQualityProxy(@RequestParam String url,@RequestParam String content){
        List<Proxy> proxyList = new java.util.ArrayList<Proxy>();
        StringTokenizer st = new StringTokenizer(content,",");
        while(st.hasMoreElements()){
            String proxyStr = st.nextElement().toString();
                String ip = proxyStr.split(":")[0];
                String port = proxyStr.split(":")[1];
                Proxy proxy = new Proxy();
                proxy.setIp(ip);
                proxy.setPort(Integer.valueOf(port));
                proxyList.add(proxy);
        }
        List<ProxyDto> proxyDtoList = taskExecuteService.checkHighQualityProxy(url,proxyList);
        return proxyDtoList;
    }
}
