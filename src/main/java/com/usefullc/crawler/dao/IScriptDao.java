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

import com.usefullc.crawler.domain.Script;

/**
 * 脚本DAO
 * @author admin
 *
 * @2015-05-31 18
 */
public interface  IScriptDao {
	
	/**
	 * 获得脚本
	 * @param id
	 * @return
	 */
	Script getScript(Long id);
	
	/**
	 * 获得脚本列表
	 * @param queryMap
	 * @return
	 */
	List<Script> getScriptList(Map<String,Object> queryMap);
	
	/**
	 * 获得脚本分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<Script> getScriptListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增脚本
	 * @param domain
	 */
	void insertScript(Script domain);
	
	/**
	 * 修改脚本
	 * @param domain
	 */
	void updateScript(Script domain);
	
	/**
	 * 删除脚本
	 * @param id
	 */
	void deleteScript(Long id);

	/**
	 * 删除脚本
	 * @param taskTpId
	 */
	void deleteScriptByTaskTpId(Long taskTpId);


}

