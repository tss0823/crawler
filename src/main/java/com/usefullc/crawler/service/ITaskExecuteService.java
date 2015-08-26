package com.usefullc.crawler.service;

import com.usefullc.crawler.common.dto.ProxyDto;
import com.usefullc.crawler.common.dto.TaskExecuteDto;
import com.usefullc.crawler.common.http.ReqParam;
import com.usefullc.crawler.domain.Proxy;

import java.util.*;

/**
 * Created by shengshan.tang on 7/29/2015 at 11:00 AM
 */
public interface ITaskExecuteService {

    TaskExecuteDto start(Long taskTpId);

    TaskExecuteDto startMulti(Long taskTpId,Long parseContentId,List<Proxy> proxyList);

    void checkReqUrl(final String url,final ReqParam reqParam,Integer taskNum,Integer corePoolSize );

    List<ProxyDto> checkHighQualityProxy(String url,List<Proxy> proxyList);


}
