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
import com.usefullc.crawler.web.query.TaskTemplateQuery;
import com.usefullc.crawler.domain.TaskTemplate;
import com.usefullc.crawler.service.ITaskTemplateService;

/**
 * 任务模板 Controller
 * @author admin
 *
 * @2015-05-31 18
 */
@Controller
@RequestMapping(value="/taskTemplate")
public class TaskTemplateController extends BaseController {
	

    @Autowired
    private ITaskTemplateService taskTemplateService;
    

    /**
     * 进入任务模板列表
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(TaskTemplateQuery query, Model model) {
        Map<String,Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<TaskTemplate> page = taskTemplateService.getTaskTemplateListPage(queryMap);
        model.addAttribute("page", page);
        return "taskTemplate/list";
    }
    
    /**
     * 进入任务模板新增
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model){
    	return "taskTemplate/new";
    }
    
    /**
     * 进入任务模板修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id,Model model){
    	TaskTemplate domain = taskTemplateService.getTaskTemplate(id);
    	model.addAttribute("domain", domain);
    	return "taskTemplate/edit";
    }
    
    /**
     * 进入任务模板查看
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id,Model model){
    	TaskTemplate domain = taskTemplateService.getTaskTemplate(id);
    	model.addAttribute("domain", domain);
    	return "taskTemplate/view";
    }
    
    /**
     * 任务模板保存
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
	@ResponseBody
    public String save(TaskTemplate domain){
    	taskTemplateService.insertTaskTemplate(domain);
    	return SUCCESS;
    }
    
    /**
     * 任务模板修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
	@ResponseBody
    public String update(TaskTemplate domain){
    	TaskTemplate oldDomain = taskTemplateService.getTaskTemplate(domain.getId());
    	BeanUtils.beanCopy(domain, oldDomain);
    	taskTemplateService.updateTaskTemplate(oldDomain);
    	return SUCCESS;
    }
    
    /**
     * 任务模板删除
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
	@ResponseBody
    public String delete(@RequestParam Long id){
    	taskTemplateService.deleteTaskTemplate(id);
    	return SUCCESS;
    }
	
	
	
	

}
