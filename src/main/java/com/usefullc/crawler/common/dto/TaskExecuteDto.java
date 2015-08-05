package com.usefullc.crawler.common.dto;

import com.usefullc.crawler.common.script.ScriptResult;
import com.usefullc.crawler.domain.Script;
import com.usefullc.crawler.domain.TaskTemplate;
import com.usefullc.crawler.domain.TaskTpParam;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shengshan.tang on 8/4/2015 at 2:08 PM
 */
public class TaskExecuteDto implements Serializable {

    private Long taskTpId;   //模板Id

    private String url;  //请求url

    private TaskTemplate taskTemplate;   //模板

    private List<TaskTpParam> taskTpParamList;  //模板参数列表

    private Script script;  //脚本

    private ScriptResult scriptResult;  //脚本执行结果

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

    public Long getTaskTpId() {
        return taskTpId;
    }

    public void setTaskTpId(Long taskTpId) {
        this.taskTpId = taskTpId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public ScriptResult getScriptResult() {
        return scriptResult;
    }

    public void setScriptResult(ScriptResult scriptResult) {
        this.scriptResult = scriptResult;
    }
}
