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
import com.usefullc.crawler.dao.IHttpResDao;
import com.usefullc.crawler.domain.HttpRes;

@Service
public class HttpResDaoImpl extends AbstractBaseDao implements IHttpResDao {
	
	@Override
	public HttpRes getHttpRes(Long id) {
		 return (HttpRes) sqlSession.selectOne("HttpResMapper.getHttpRes", id);
	}
	
	@Override
	public List<HttpRes> getHttpResList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("HttpResMapper.getHttpResList",queryMap);
	}
	
	public Pagination<HttpRes> getHttpResListPage(Map<String,Object> queryMap) {
		Pagination<HttpRes> page = getPagination(queryMap, "HttpResMapper.getHttpResListPage", "HttpResMapper.getHttpResListPageCount");
		return page;
	}

	@Override
	public void insertHttpRes(HttpRes domain) {
		sqlSession.insert("HttpResMapper.insertHttpRes", domain);
	}

	@Override
	public void updateHttpRes(HttpRes domain) {
		sqlSession.update("HttpResMapper.updateHttpRes", domain);
	}

	@Override
	public void deleteHttpRes(Long id) {
		sqlSession.delete("HttpResMapper.deleteHttpRes", id);
	}

}
