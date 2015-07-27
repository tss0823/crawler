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
import com.usefullc.crawler.web.query.TaskInstanceQuery;
import com.usefullc.crawler.domain.TaskInstance;
import com.usefullc.crawler.service.ITaskInstanceService;

/**
 * 任务实例 Controller
 * @author admin
 *
 * @2015-05-31 18
 */
@Controller
@RequestMapping(value="/taskInstance")
public class TaskInstanceController extends BaseController {
	

    @Autowired
    private ITaskInstanceService taskInstanceService;
    

    /**
     * 进入任务实例列表
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(TaskInstanceQuery query, Model model) {
        Map<String,Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<TaskInstance> page = taskInstanceService.getTaskInstanceListPage(queryMap);
        model.addAttribute("page", page);
        return "taskInstance/list";
    }
    
    /**
     * 进入任务实例新增
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model){
    	return "taskInstance/new";
    }
    
    /**
     * 进入任务实例修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id,Model model){
    	TaskInstance domain = taskInstanceService.getTaskInstance(id);
    	model.addAttribute("domain", domain);
    	return "taskInstance/edit";
    }
    
    /**
     * 进入任务实例查看
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id,Model model){
    	TaskInstance domain = taskInstanceService.getTaskInstance(id);
    	model.addAttribute("domain", domain);
    	return "taskInstance/view";
    }
    
    /**
     * 任务实例保存
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
	@ResponseBody
    public String save(TaskInstance domain){
    	taskInstanceService.insertTaskInstance(domain);
    	return SUCCESS;
    }
    
    /**
     * 任务实例修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
	@ResponseBody
    public String update(TaskInstance domain){
    	TaskInstance oldDomain = taskInstanceService.getTaskInstance(domain.getId());
    	BeanUtils.beanCopy(domain, oldDomain);
    	taskInstanceService.updateTaskInstance(oldDomain);
    	return SUCCESS;
    }
    
    /**
     * 任务实例删除
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
	@ResponseBody
    public String delete(@RequestParam Long id){
    	taskInstanceService.deleteTaskInstance(id);
    	return SUCCESS;
    }
	
	
	
	

}
