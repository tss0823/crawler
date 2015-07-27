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

import com.usefullc.crawler.domain.TaskInstance;

/**
 * 任务实例DAO
 * @author admin
 *
 * @2015-05-31 18
 */
public interface  ITaskInstanceDao {
	
	/**
	 * 获得任务实例
	 * @param id
	 * @return
	 */
	TaskInstance getTaskInstance(Long id);
	
	/**
	 * 获得任务实例列表
	 * @param queryMap
	 * @return
	 */
	List<TaskInstance> getTaskInstanceList(Map<String,Object> queryMap);
	
	/**
	 * 获得任务实例分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<TaskInstance> getTaskInstanceListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增任务实例
	 * @param domain
	 */
	void insertTaskInstance(TaskInstance domain);
	
	/**
	 * 修改任务实例
	 * @param domain
	 */
	void updateTaskInstance(TaskInstance domain);
	
	/**
	 * 删除任务实例
	 * @param id
	 */
	void deleteTaskInstance(Long id);


}

