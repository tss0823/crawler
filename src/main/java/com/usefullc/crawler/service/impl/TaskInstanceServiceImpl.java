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
import com.usefullc.crawler.dao.ITaskInstanceDao;
import com.usefullc.crawler.domain.TaskInstance;
import com.usefullc.crawler.service.ITaskInstanceService;


@Service
public class TaskInstanceServiceImpl extends AbstractBaseService implements ITaskInstanceService {
	
	@Autowired
	private ITaskInstanceDao taskInstanceDao;

	public TaskInstance getTaskInstance(Long id) {
		return taskInstanceDao.getTaskInstance(id);
	}

	public List<TaskInstance> getTaskInstanceList(Map<String, Object> queryMap) {
		return taskInstanceDao.getTaskInstanceList(queryMap);
	}
	
	@Override
	public Pagination<TaskInstance> getTaskInstanceListPage(Map<String, Object> queryMap) {
		return taskInstanceDao.getTaskInstanceListPage(queryMap);
	}
	

	public void insertTaskInstance(TaskInstance taskInstance) {
		taskInstanceDao.insertTaskInstance(taskInstance);
	}

	public void updateTaskInstance(TaskInstance taskInstance) {
		taskInstanceDao.updateTaskInstance(taskInstance);
	}

	public void deleteTaskInstance(Long id) {
		taskInstanceDao.deleteTaskInstance(id);
	}




	
}
