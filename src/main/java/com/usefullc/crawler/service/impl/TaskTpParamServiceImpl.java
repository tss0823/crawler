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
import com.usefullc.crawler.dao.ITaskTpParamDao;
import com.usefullc.crawler.domain.TaskTpParam;
import com.usefullc.crawler.service.ITaskTpParamService;


@Service
public class TaskTpParamServiceImpl extends AbstractBaseService implements ITaskTpParamService {
	
	@Autowired
	private ITaskTpParamDao taskTpParamDao;

	public TaskTpParam getTaskTpParam(Long id) {
		return taskTpParamDao.getTaskTpParam(id);
	}

	public List<TaskTpParam> getTaskTpParamList(Map<String, Object> queryMap) {
		return taskTpParamDao.getTaskTpParamList(queryMap);
	}
	
	public Pagination<TaskTpParam> getTaskTpParamListPage(Map<String, Object> queryMap) {
		return taskTpParamDao.getTaskTpParamListPage(queryMap);
	}
	

	public void insertTaskTpParam(TaskTpParam taskTpParam) {
		taskTpParamDao.insertTaskTpParam(taskTpParam);
	}

	public void updateTaskTpParam(TaskTpParam taskTpParam) {
		taskTpParamDao.updateTaskTpParam(taskTpParam);
	}

	public void deleteTaskTpParam(Long id) {
		taskTpParamDao.deleteTaskTpParam(id);
	}

	public void deleteByTaskTpId(Long taskTpId) {
		taskTpParamDao.deleteTaskTpParamByTaskTpId(taskTpId);
	}


}
