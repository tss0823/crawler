/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.service;
import java.util.List;
import java.util.Map;
import com.usefullc.crawler.domain.TaskTemplate;
import com.usefullc.platform.common.web.Pagination;

/**
 *  任务模板 Service
 * @author admin
 *
 * @2015-05-31 18
 */
public interface ITaskTemplateService {
	
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
	 * @param taskTemplate
	 */
	void insertTaskTemplate(TaskTemplate domain);
	
	/**
	 * 修改任务模板
	 * @param taskTemplate
	 */
	void updateTaskTemplate(TaskTemplate domain);
	
	/**
	 * 删除任务模板
	 * @param id
	 */
	void deleteTaskTemplate(Long id);
    

}

