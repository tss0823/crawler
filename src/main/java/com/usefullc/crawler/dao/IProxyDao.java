/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.dao;
import java.util.List;
import java.util.Map;

import com.usefullc.platform.common.web.Pagination;

import com.usefullc.crawler.domain.Proxy;

/**
 * 代理DAO
 * @author admin
 *
 * @2015-05-31 18
 */
public interface  IProxyDao {
	
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
	 * @param domain
	 */
	void insertProxy(Proxy domain);
	
	/**
	 * 修改代理
	 * @param domain
	 */
	void updateProxy(Proxy domain);
	
	/**
	 * 删除代理
	 * @param id
	 */
	void deleteProxy(Long id);


}

