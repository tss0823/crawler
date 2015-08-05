package com.usefullc.crawler.common.dto;

import com.usefullc.crawler.domain.Script;
import com.usefullc.crawler.domain.TaskTemplate;
import com.usefullc.crawler.domain.TaskTpParam;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shengshan.tang on 8/4/2015 at 2:08 PM
 */
public class TaskTplDto implements Serializable {

    private TaskTemplate taskTemplate;   //模板

    private List<TaskTpParam> taskTpParamList;  //模板参数列表

    private Script script;  //脚本



    public TaskTemplate getTaskTemplate() {
        return taskTemplate;
    }

    public void setTaskTemplate(TaskTemplate taskTemplate) {
        this.taskTemplate = taskTemplate;
    }


    public List<TaskTpParam> getTaskTpParamList() {
        return taskTpParamList;
    }

    public void setTaskTpParamList(List<TaskTpParam> taskTpParamList) {
        this.taskTpParamList = taskTpParamList;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }
}
