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
import com.usefullc.crawler.web.query.HttpResQuery;
import com.usefullc.crawler.domain.HttpRes;
import com.usefullc.crawler.service.IHttpResService;

/**
 * 返回内容 Controller
 * @author admin
 *
 * @2015-05-31 18
 */
@Controller
@RequestMapping(value="/httpRes")
public class HttpResController extends BaseController {
	

    @Autowired
    private IHttpResService httpResService;
    

    /**
     * 进入返回内容列表
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(HttpResQuery query, Model model) {
        Map<String,Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<HttpRes> page = httpResService.getHttpResListPage(queryMap);
        model.addAttribute("page", page);
        return "httpRes/list";
    }
    
    /**
     * 进入返回内容新增
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model){
    	return "httpRes/new";
    }
    
    /**
     * 进入返回内容修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id,Model model){
    	HttpRes domain = httpResService.getHttpRes(id);
    	model.addAttribute("domain", domain);
    	return "httpRes/edit";
    }
    
    /**
     * 进入返回内容查看
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id,Model model){
    	HttpRes domain = httpResService.getHttpRes(id);
    	model.addAttribute("domain", domain);
    	return "httpRes/view";
    }
    
    /**
     * 返回内容保存
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
	@ResponseBody
    public String save(HttpRes domain){
    	httpResService.insertHttpRes(domain);
    	return SUCCESS;
    }
    
    /**
     * 返回内容修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
	@ResponseBody
    public String update(HttpRes domain){
    	HttpRes oldDomain = httpResService.getHttpRes(domain.getId());
    	BeanUtils.beanCopy(domain, oldDomain);
    	httpResService.updateHttpRes(oldDomain);
    	return SUCCESS;
    }
    
    /**
     * 返回内容删除
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
	@ResponseBody
    public String delete(@RequestParam Long id){
    	httpResService.deleteHttpRes(id);
    	return SUCCESS;
    }
	
	
	
	

}
