package com.usefullc.crawler.common.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by shengshan.tang on 7/29/2015 at 5:06 PM
 */
public class TaskExecuteHelper {

    private final static Logger log = LoggerFactory.getLogger(TaskExecuteHelper.class);

    public static void execute(int corePoolSize,int maximumPoolSize,List<CThread> taskList,final ITaskBizExecute taskBizExecute) {
        try {
            final long startMainTime = System.currentTimeMillis();
            ExecutorService ec = new ThreadPoolExecutor(corePoolSize,maximumPoolSize , 0, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>()) {

                @Override protected void terminated() {
                    super.terminated();
                    System.out.println("finish, take time = " + (System.currentTimeMillis() - startMainTime));
                    //业务执行start
                    taskBizExecute.terminated();
                }


                @Override protected void afterExecute(Runnable r, Throwable t) {
                    super.afterExecute(r, t);
                    try {
                        CThread myThread = (CThread) r;
                        Long startTime = myThread.getStartTime();
                        int index = myThread.getIndex();
                        System.out.println(
                                Thread.currentThread().getName() + "finish! take time=" + (System.currentTimeMillis()
                                        - startTime) + "," + index);
                        //业务执行start
                        taskBizExecute.afterExecute(myThread);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };

            for (CThread cThread : taskList) {
                cThread.setEc(ec);
                cThread.setTaskBizExecute(taskBizExecute);
                ec.execute(cThread);
            }
            ec.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
