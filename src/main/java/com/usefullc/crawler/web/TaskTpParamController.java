/**
 * 
 */
package com.usefullc.crawler.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.common.utils.BeanUtils;
import com.usefullc.crawler.web.query.TaskTpParamQuery;
import com.usefullc.crawler.domain.TaskTpParam;
import com.usefullc.crawler.service.ITaskTpParamService;

/**
 * 任务模板参数 Controller
 * @author admin
 *
 * @2015-05-31 18
 */
@Controller
@RequestMapping(value="/taskTpParam")
public class TaskTpParamController extends BaseController {
	

    @Autowired
    private ITaskTpParamService taskTpParamService;
    

    /**
     * 进入任务模板参数列表
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(TaskTpParamQuery query, Model model) {
        Map<String,Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<TaskTpParam> page = taskTpParamService.getTaskTpParamListPage(queryMap);
        model.addAttribute("page", page);
        return "taskTpParam/list";
    }
    
    /**
     * 进入任务模板参数新增
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model){
    	return "taskTpParam/new";
    }
    
    /**
     * 进入任务模板参数修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id,Model model){
    	TaskTpParam domain = taskTpParamService.getTaskTpParam(id);
    	model.addAttribute("domain", domain);
    	return "taskTpParam/edit";
    }
    
    /**
     * 进入任务模板参数查看
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id,Model model){
    	TaskTpParam domain = taskTpParamService.getTaskTpParam(id);
    	model.addAttribute("domain", domain);
    	return "taskTpParam/view";
    }
    
    /**
     * 任务模板参数保存
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
	@ResponseBody
    public String save(TaskTpParam domain){
    	taskTpParamService.insertTaskTpParam(domain);
    	return SUCCESS;
    }
    
    /**
     * 任务模板参数修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
	@ResponseBody
    public String update(TaskTpParam domain){
    	TaskTpParam oldDomain = taskTpParamService.getTaskTpParam(domain.getId());
    	BeanUtils.beanCopy(domain, oldDomain);
    	taskTpParamService.updateTaskTpParam(oldDomain);
    	return SUCCESS;
    }
    
    /**
     * 任务模板参数删除
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
	@ResponseBody
    public String delete(@RequestParam Long id){
    	taskTpParamService.deleteTaskTpParam(id);
    	return SUCCESS;
    }
	
	
	
	

}
