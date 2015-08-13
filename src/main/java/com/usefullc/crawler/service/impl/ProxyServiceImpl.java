/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.service.impl;


import java.util.*;

import org.apache.commons.io.IOUtils;
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

	@Override
	public void uploadFile(byte[] fileBytes) {
		String content = new String(fileBytes);
		importText(content);
	}

	@Override
	public void importText(String text) {
		StringTokenizer st = new StringTokenizer(text,",");
		while(st.hasMoreElements()){
			String str = st.nextElement().toString().trim();
			str = str.substring(1,str.length()-1);
			String ip = str.split(":")[0];
			String port = str.split(":")[1];
			Proxy proxy = new Proxy();
			proxy.setIp(ip);
			proxy.setPort(Integer.valueOf(port));
			proxyDao.insertProxy(proxy);
		}
	}


}
