/**
 * 
 */
package com.usefullc.crawler.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.common.utils.BeanUtils;
import com.usefullc.crawler.web.query.ParseContentQuery;
import com.usefullc.crawler.domain.ParseContent;
import com.usefullc.crawler.service.IParseContentService;

/**
 * 解析内容 Controller
 * @author admin
 *
 * @2015-05-31 18
 */
@Controller
@RequestMapping(value="/parseContent")
public class ParseContentController extends BaseController {
	

    @Autowired
    private IParseContentService parseContentService;
    

    /**
     * 进入解析内容列表
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(ParseContentQuery query, Model model) {
        Map<String,Object> queryMap = BeanUtils.beanToQueryMap(query);
        List<ParseContent> dataList = parseContentService.getParseContentList(queryMap);
        model.addAttribute("dataList", dataList);
        return "parseContent/list";
    }
    
    /**
     * 进入解析内容新增
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model){
    	return "parseContent/new";
    }
    
    /**
     * 进入解析内容修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id,Model model){
    	ParseContent domain = parseContentService.getParseContent(id);
    	model.addAttribute("domain", domain);
    	return "parseContent/edit";
    }
    
    /**
     * 进入解析内容查看
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id,Model model){
    	ParseContent domain = parseContentService.getParseContent(id);
    	model.addAttribute("domain", domain);
    	return "parseContent/view";
    }
    
    /**
     * 解析内容保存
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
	@ResponseBody
    public String save(ParseContent domain){
    	parseContentService.insertParseContent(domain);
    	return SUCCESS;
    }
    
    /**
     * 解析内容修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
	@ResponseBody
    public String update(ParseContent domain){
    	ParseContent oldDomain = parseContentService.getParseContent(domain.getId());
    	BeanUtils.beanCopy(domain, oldDomain);
    	parseContentService.updateParseContent(oldDomain);
    	return SUCCESS;
    }
    
    /**
     * 解析内容删除
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
	@ResponseBody
    public String delete(@RequestParam Long id){
    	parseContentService.deleteParseContent(id);
    	return SUCCESS;
    }
	
	
	
	

}
