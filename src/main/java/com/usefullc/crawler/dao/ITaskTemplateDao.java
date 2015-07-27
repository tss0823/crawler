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

import com.usefullc.crawler.domain.TaskTemplate;

/**
 * 任务模板DAO
 * @author admin
 *
 * @2015-05-31 18
 */
public interface  ITaskTemplateDao {
	
	/**
	 * 获得任务模板
	 * @param id
	 * @return
	 */
	TaskTemplate getTaskTemplate(Long id);
	
	/**
	 * 获得任务模板列表
	 * @param queryMap
	 * @return
	 */
	List<TaskTemplate> getTaskTemplateList(Map<String,Object> queryMap);
	
	/**
	 * 获得任务模板分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<TaskTemplate> getTaskTemplateListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增任务模板
	 * @param domain
	 */
	void insertTaskTemplate(TaskTemplate domain);
	
	/**
	 * 修改任务模板
	 * @param domain
	 */
	void updateTaskTemplate(TaskTemplate domain);
	
	/**
	 * 删除任务模板
	 * @param id
	 */
	void deleteTaskTemplate(Long id);


}

