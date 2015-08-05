package com.usefullc.crawler.web;

import com.usefullc.crawler.common.dto.TaskExecuteDto;
import com.usefullc.crawler.service.ITaskExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;

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
     * @return
     */
    @RequestMapping(value = "/startMulti.htm")
    @ResponseBody
    public TaskExecuteDto startMulti(@RequestParam Long taskTpId,@RequestParam Long parseContentId){
        TaskExecuteDto taskExecuteDto = taskExecuteService.startMulti(taskTpId,parseContentId);
        return taskExecuteDto;
    }
}
