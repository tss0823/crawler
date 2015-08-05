package com.usefullc.crawler.common.task;

import java.util.List;
import java.util.Map;

/**
 * Created by shengshan.tang on 8/4/2015 at 6:11 PM
 * 任务执行抽象类
 */
public interface ITaskBizExecute {

    void init(Map<String, Object> initMap);

    void execute(CThread cThread);

    void afterExecute(CThread cThread);

    void terminated();

    //获取请求前参数列表
    List<Map<String,Object>> getBeforeReqParamList();
}
