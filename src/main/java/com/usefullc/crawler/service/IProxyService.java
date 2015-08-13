/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.service;
import java.util.List;
import java.util.Map;
import com.usefullc.crawler.domain.Proxy;
import com.usefullc.platform.common.web.Pagination;

/**
 *  代理 Service
 * @author admin
 *
 * @2015-05-31 18
 */
public interface IProxyService {
	
	/**
	 * 获得代理
	 * @param id
	 * @return
	 */
	Proxy getProxy(Long id);
	
	/**
	 * 获得代理列表
	 * @param queryMap
	 * @return
	 */
	List<Proxy> getProxyList(Map<String,Object> queryMap);
	
	/**
	 * 获得代理分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<Proxy> getProxyListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增代理
	 * @param proxy
	 */
	void insertProxy(Proxy domain);
	
	/**
	 * 修改代理
	 * @param proxy
	 */
	void updateProxy(Proxy domain);
	
	/**
	 * 删除代理
	 * @param id
	 */
	void deleteProxy(Long id);

	/**
	 * 上传文件，导入代理
	 * @param fileBytes
	 */
	void uploadFile(byte [] fileBytes);
	/**
	 * 导入文本，导入代理
	 * @param text
	 */
	void importText(String text);


}

