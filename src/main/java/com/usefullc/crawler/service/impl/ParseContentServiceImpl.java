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
import com.usefullc.crawler.dao.IParseContentDao;
import com.usefullc.crawler.domain.ParseContent;
import com.usefullc.crawler.service.IParseContentService;


@Service("parseContentService")
public class ParseContentServiceImpl extends AbstractBaseService implements IParseContentService {
	
	@Autowired
	private IParseContentDao parseContentDao;

	public ParseContent getParseContent(Long id) {
		return parseContentDao.getParseContent(id);
	}

	public List<ParseContent> getParseContentList(Map<String, Object> queryMap) {
		return parseContentDao.getParseContentList(queryMap);
	}
	
	public Pagination<ParseContent> getParseContentListPage(Map<String, Object> queryMap) {
		return parseContentDao.getParseContentListPage(queryMap);
	}
	

	public void insertParseContent(ParseContent parseContent) {
		parseContentDao.insertParseContent(parseContent);
	}

	public void updateParseContent(ParseContent parseContent) {
		parseContentDao.updateParseContent(parseContent);
	}

	public void deleteParseContent(Long id) {
		parseContentDao.deleteParseContent(id);
	}




	
}
