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

import com.usefullc.crawler.domain.TaskTpParam;

/**
 * 任务模板参数DAO
 * @author admin
 *
 * @2015-05-31 18
 */
public interface  ITaskTpParamDao {
	
	/**
	 * 获得任务模板参数
	 * @param id
	 * @return
	 */
	TaskTpParam getTaskTpParam(Long id);
	
	/**
	 * 获得任务模板参数列表
	 * @param queryMap
	 * @return
	 */
	List<TaskTpParam> getTaskTpParamList(Map<String,Object> queryMap);
	
	/**
	 * 获得任务模板参数分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<TaskTpParam> getTaskTpParamListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增任务模板参数
	 * @param domain
	 */
	void insertTaskTpParam(TaskTpParam domain);
	
	/**
	 * 修改任务模板参数
	 * @param domain
	 */
	void updateTaskTpParam(TaskTpParam domain);
	
	/**
	 * 删除任务模板参数
	 * @param id
	 */
	void deleteTaskTpParam(Long id);


}

