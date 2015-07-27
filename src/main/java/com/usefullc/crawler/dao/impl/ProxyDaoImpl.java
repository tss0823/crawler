/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.dao.AbstractBaseDao;
import com.usefullc.crawler.dao.IProxyDao;
import com.usefullc.crawler.domain.Proxy;

@Service
public class ProxyDaoImpl extends AbstractBaseDao implements IProxyDao {
	
	@Override
	public Proxy getProxy(Long id) {
		 return (Proxy) sqlSession.selectOne("ProxyMapper.getProxy", id);
	}
	
	@Override
	public List<Proxy> getProxyList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("ProxyMapper.getProxyList",queryMap);
	}
	
	public Pagination<Proxy> getProxyListPage(Map<String,Object> queryMap) {
		Pagination<Proxy> page = getPagination(queryMap, "ProxyMapper.getProxyListPage", "ProxyMapper.getProxyListPageCount");
		return page;
	}

	@Override
	public void insertProxy(Proxy domain) {
		sqlSession.insert("ProxyMapper.insertProxy", domain);
	}

	@Override
	public void updateProxy(Proxy domain) {
		sqlSession.update("ProxyMapper.updateProxy", domain);
	}

	@Override
	public void deleteProxy(Long id) {
		sqlSession.delete("ProxyMapper.deleteProxy", id);
	}

}
