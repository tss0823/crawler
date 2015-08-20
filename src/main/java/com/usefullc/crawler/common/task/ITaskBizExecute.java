package com.usefullc.crawler.common.task;

import java.util.List;
import java.util.Map;

/**
 * Created by shengshan.tang on 8/4/2015 at 6:11 PM
 * 任务执行抽象类
 */
public interface ITaskBizExecute {

    /**
     * 外部调用初始化
     * @param initMap
     */
    void init(Map<String, Object> initMap);

    /**
     * thread run execute
     * @param cThread
     */
    void execute(CThread cThread);

    /**
     * thread after execute
     * @param cThread
     */
    void afterExecute(CThread cThread);

    /**
     * 线程池结束执行
     */
    void terminated();

    /**
     * 获取请求前参数列表,业务脚本执行
     */
    List<Map<String,Object>> getBeforeReqParamList();
}
