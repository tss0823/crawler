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
import com.usefullc.crawler.web.query.ScriptQuery;
import com.usefullc.crawler.domain.Script;
import com.usefullc.crawler.service.IScriptService;

/**
 * 脚本 Controller
 * @author admin
 *
 * @2015-05-31 18
 */
@Controller
@RequestMapping(value="/script")
public class ScriptController extends BaseController {
	

    @Autowired
    private IScriptService scriptService;
    

    /**
     * 进入脚本列表
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(ScriptQuery query, Model model) {
        Map<String,Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<Script> page = scriptService.getScriptListPage(queryMap);
        model.addAttribute("page", page);
        return "script/list";
    }
    
    /**
     * 进入脚本新增
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model){
    	return "script/new";
    }
    
    /**
     * 进入脚本修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id,Model model){
    	Script domain = scriptService.getScript(id);
    	model.addAttribute("domain", domain);
    	return "script/edit";
    }
    
    /**
     * 进入脚本查看
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id,Model model){
    	Script domain = scriptService.getScript(id);
    	model.addAttribute("domain", domain);
    	return "script/view";
    }
    
    /**
     * 脚本保存
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
	@ResponseBody
    public String save(Script domain){
    	scriptService.insertScript(domain);
    	return SUCCESS;
    }
    
    /**
     * 脚本修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
	@ResponseBody
    public String update(Script domain){
    	Script oldDomain = scriptService.getScript(domain.getId());
    	BeanUtils.beanCopy(domain, oldDomain);
    	scriptService.updateScript(oldDomain);
    	return SUCCESS;
    }
    
    /**
     * 脚本删除
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
	@ResponseBody
    public String delete(@RequestParam Long id){
    	scriptService.deleteScript(id);
    	return SUCCESS;
    }
	
	
	
	

}
