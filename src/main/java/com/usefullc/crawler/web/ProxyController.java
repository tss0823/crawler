/**
 * 
 */
package com.usefullc.crawler.web;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.common.utils.BeanUtils;
import com.usefullc.crawler.web.query.ProxyQuery;
import com.usefullc.crawler.domain.Proxy;
import com.usefullc.crawler.service.IProxyService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 代理 Controller
 * @author admin
 *
 * @2015-05-31 18
 */
@Controller
@RequestMapping(value="/proxy")
public class ProxyController extends BaseController {
	

    @Autowired
    private IProxyService proxyService;
    

    /**
     * 进入代理列表
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(ProxyQuery query, Model model) {
        Map<String,Object> queryMap = BeanUtils.beanToQueryMap(query);
        List<Proxy> dataList = proxyService.getProxyList(queryMap);
        model.addAttribute("dataList", dataList);
        return "proxy/list";
    }
    
    /**
     * 进入代理新增
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model){
    	return "proxy/new";
    }
    
    /**
     * 进入代理修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id,Model model){
    	Proxy domain = proxyService.getProxy(id);
    	model.addAttribute("domain", domain);
    	return "proxy/edit";
    }
    
    /**
     * 进入代理查看
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id,Model model){
    	Proxy domain = proxyService.getProxy(id);
    	model.addAttribute("domain", domain);
    	return "proxy/view";
    }
    
    /**
     * 代理文件导入
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadFile.htm")
	@ResponseBody
    public String uploadFile(@RequestParam  MultipartFile file){
        try {
            proxyService.uploadFile(file.getBytes());
        } catch (IOException e) {
            log.error("upload file failed",e);
            return FAILED;
        }
        return SUCCESS;
    }

    /**
     * 代理文本导入
     * @param content
     * @return
     */
    @RequestMapping(value = "/importText.htm")
	@ResponseBody
    public String importText(@RequestParam String content){
        proxyService.importText(content);
        return SUCCESS;
    }


    /**
     * 代理保存
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
	@ResponseBody
    public String save(Proxy domain){
    	proxyService.insertProxy(domain);
    	return SUCCESS;
    }

    /**
     * 代理修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
	@ResponseBody
    public String update(Proxy domain){
    	Proxy oldDomain = proxyService.getProxy(domain.getId());
    	BeanUtils.beanCopy(domain, oldDomain);
    	proxyService.updateProxy(oldDomain);
    	return SUCCESS;
    }
    
    /**
     * 代理删除
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
	@ResponseBody
    public String delete(@RequestParam Long id){
    	proxyService.deleteProxy(id);
    	return SUCCESS;
    }
	
	
	
	

}
