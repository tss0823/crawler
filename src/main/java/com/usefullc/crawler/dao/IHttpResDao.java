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

import com.usefullc.crawler.domain.HttpRes;

/**
 * 返回内容DAO
 * @author admin
 *
 * @2015-05-31 18
 */
public interface  IHttpResDao {
	
	/**
	 * 获得返回内容
	 * @param id
	 * @return
	 */
	HttpRes getHttpRes(Long id);
	
	/**
	 * 获得返回内容列表
	 * @param queryMap
	 * @return
	 */
	List<HttpRes> getHttpResList(Map<String,Object> queryMap);
	
	/**
	 * 获得返回内容分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<HttpRes> getHttpResListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增返回内容
	 * @param domain
	 */
	void insertHttpRes(HttpRes domain);
	
	/**
	 * 修改返回内容
	 * @param domain
	 */
	void updateHttpRes(HttpRes domain);
	
	/**
	 * 删除返回内容
	 * @param id
	 */
	void deleteHttpRes(Long id);


}

