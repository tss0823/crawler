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
import com.usefullc.crawler.dao.IParseContentDao;
import com.usefullc.crawler.domain.ParseContent;

@Service
public class ParseContentDaoImpl extends AbstractBaseDao implements IParseContentDao {
	
	@Override
	public ParseContent getParseContent(Long id) {
		 return (ParseContent) sqlSession.selectOne("ParseContentMapper.getParseContent", id);
	}
	
	@Override
	public List<ParseContent> getParseContentList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("ParseContentMapper.getParseContentList",queryMap);
	}
	
	public Pagination<ParseContent> getParseContentListPage(Map<String,Object> queryMap) {
		Pagination<ParseContent> page = getPagination(queryMap, "ParseContentMapper.getParseContentListPage", "ParseContentMapper.getParseContentListPageCount");
		return page;
	}

	@Override
	public void insertParseContent(ParseContent domain) {
		sqlSession.insert("ParseContentMapper.insertParseContent", domain);
	}

	@Override
	public void updateParseContent(ParseContent domain) {
		sqlSession.update("ParseContentMapper.updateParseContent", domain);
	}

	@Override
	public void deleteParseContent(Long id) {
		sqlSession.delete("ParseContentMapper.deleteParseContent", id);
	}

}
