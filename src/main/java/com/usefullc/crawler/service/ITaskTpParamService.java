/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.service;
import java.util.List;
import java.util.Map;
import com.usefullc.crawler.domain.TaskTpParam;
import com.usefullc.platform.common.web.Pagination;

/**
 *  任务模板参数 Service
 * @author admin
 *
 * @2015-05-31 18
 */
public interface ITaskTpParamService {
	
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
	 * @param taskTpParam
	 */
	void insertTaskTpParam(TaskTpParam domain);
	
	/**
	 * 修改任务模板参数
	 * @param taskTpParam
	 */
	void updateTaskTpParam(TaskTpParam domain);
	
	/**
	 * 删除任务模板参数
	 * @param id
	 */
	void deleteTaskTpParam(Long id);
    

}

