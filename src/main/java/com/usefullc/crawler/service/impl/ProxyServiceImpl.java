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
import com.usefullc.crawler.dao.IProxyDao;
import com.usefullc.crawler.domain.Proxy;
import com.usefullc.crawler.service.IProxyService;


@Service
public class ProxyServiceImpl extends AbstractBaseService implements IProxyService {
	
	@Autowired
	private IProxyDao proxyDao;

	public Proxy getProxy(Long id) {
		return proxyDao.getProxy(id);
	}

	public List<Proxy> getProxyList(Map<String, Object> queryMap) {
		return proxyDao.getProxyList(queryMap);
	}
	
	@Override
	public Pagination<Proxy> getProxyListPage(Map<String, Object> queryMap) {
		return proxyDao.getProxyListPage(queryMap);
	}
	

	public void insertProxy(Proxy proxy) {
		proxyDao.insertProxy(proxy);
	}

	public void updateProxy(Proxy proxy) {
		proxyDao.updateProxy(proxy);
	}

	public void deleteProxy(Long id) {
		proxyDao.deleteProxy(id);
	}




	
}
