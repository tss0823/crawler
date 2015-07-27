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
import com.usefullc.crawler.dao.ITaskTemplateDao;
import com.usefullc.crawler.domain.TaskTemplate;
import com.usefullc.crawler.service.ITaskTemplateService;


@Service
public class TaskTemplateServiceImpl extends AbstractBaseService implements ITaskTemplateService {
	
	@Autowired
	private ITaskTemplateDao taskTemplateDao;

	public TaskTemplate getTaskTemplate(Long id) {
		return taskTemplateDao.getTaskTemplate(id);
	}

	public List<TaskTemplate> getTaskTemplateList(Map<String, Object> queryMap) {
		return taskTemplateDao.getTaskTemplateList(queryMap);
	}
	
	@Override
	public Pagination<TaskTemplate> getTaskTemplateListPage(Map<String, Object> queryMap) {
		return taskTemplateDao.getTaskTemplateListPage(queryMap);
	}
	

	public void insertTaskTemplate(TaskTemplate taskTemplate) {
		taskTemplateDao.insertTaskTemplate(taskTemplate);
	}

	public void updateTaskTemplate(TaskTemplate taskTemplate) {
		taskTemplateDao.updateTaskTemplate(taskTemplate);
	}

	public void deleteTaskTemplate(Long id) {
		taskTemplateDao.deleteTaskTemplate(id);
	}




	
}
