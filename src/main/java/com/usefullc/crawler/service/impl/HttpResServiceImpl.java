/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.crawler.dao.IHttpResDao;
import com.usefullc.crawler.domain.HttpRes;
import com.usefullc.crawler.service.IHttpResService;


@Service("httpResService")
public class HttpResServiceImpl extends AbstractBaseService implements IHttpResService {
	
	@Autowired
	private IHttpResDao httpResDao;

	public HttpRes getHttpRes(Long id) {
		return httpResDao.getHttpRes(id);
	}

	public List<HttpRes> getHttpResList(Map<String, Object> queryMap) {
		return httpResDao.getHttpResList(queryMap);
	}
	
	public Pagination<HttpRes> getHttpResListPage(Map<String, Object> queryMap) {
		return httpResDao.getHttpResListPage(queryMap);
	}
	

	public void insertHttpRes(HttpRes httpRes) {
		httpResDao.insertHttpRes(httpRes);
	}

	public void updateHttpRes(HttpRes httpRes) {
		httpResDao.updateHttpRes(httpRes);
	}

	public void deleteHttpRes(Long id) {
		httpResDao.deleteHttpRes(id);
	}




	
}
