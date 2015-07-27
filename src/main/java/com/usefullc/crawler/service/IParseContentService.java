/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.service;
import java.util.List;
import java.util.Map;
import com.usefullc.crawler.domain.ParseContent;
import com.usefullc.platform.common.web.Pagination;

/**
 *  解析内容 Service
 * @author admin
 *
 * @2015-05-31 18
 */
public interface IParseContentService {
	
	/**
	 * 获得解析内容
	 * @param id
	 * @return
	 */
	ParseContent getParseContent(Long id);
	
	/**
	 * 获得解析内容列表
	 * @param queryMap
	 * @return
	 */
	List<ParseContent> getParseContentList(Map<String,Object> queryMap);
	
	/**
	 * 获得解析内容分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<ParseContent> getParseContentListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增解析内容
	 * @param parseContent
	 */
	void insertParseContent(ParseContent domain);
	
	/**
	 * 修改解析内容
	 * @param parseContent
	 */
	void updateParseContent(ParseContent domain);
	
	/**
	 * 删除解析内容
	 * @param id
	 */
	void deleteParseContent(Long id);
    

}

