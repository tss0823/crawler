/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.service.impl;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.usefullc.crawler.common.dto.TaskTplDto;
import com.usefullc.crawler.domain.Script;
import com.usefullc.crawler.domain.TaskInstance;
import com.usefullc.crawler.domain.TaskTpParam;
import com.usefullc.crawler.service.IScriptService;
import com.usefullc.crawler.service.ITaskTpParamService;
import org.apache.commons.collections.CollectionUtils;
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

	@Autowired
	private ITaskTpParamService taskTpParamService;

	@Autowired
	private IScriptService scriptService;

	public TaskTemplate getTaskTemplate(Long id) {
		return taskTemplateDao.getTaskTemplate(id);
	}

	public List<TaskTemplate> getTaskTemplateList(Map<String, Object> queryMap) {
		return taskTemplateDao.getTaskTemplateList(queryMap);
	}
	
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

	public void save(TaskTplDto dto){
		TaskTemplate taskTemplate = dto.getTaskTemplate();
		List<TaskTpParam> taskTpParamList = dto.getTaskTpParamList();
		//创建任务实例
		TaskInstance taskInstance = new TaskInstance();
		taskInstance.setTaskTpId(taskTemplate.getId());
		taskInstance.setName(taskTemplate.getName());
		taskInstance.setType("");
//        taskInstance.setStatus();
//		taskInstanceService.insertTaskInstance(taskInstance);
	}

	public TaskTplDto getDtoById(Long taskTpId){
		TaskTplDto taskTplDto = new TaskTplDto();
		//get taskTemplate
		TaskTemplate taskTemplate  = getTaskTemplate(taskTpId);
		taskTplDto.setTaskTemplate(taskTemplate);

		//get taskParam list
		Map<String,Object> taskParamMap = new HashMap<String, Object>();
		taskParamMap.put("taskTpId",taskTpId);
		List<TaskTpParam> taskTpParamList  = taskTpParamService.getTaskTpParamList(taskParamMap);
		taskTplDto.setTaskTpParamList(taskTpParamList);

		//get script
		Map<String,Object> scriptMap = new HashMap<String, Object>();
		scriptMap.put("taskTpId",taskTpId);
		List<Script> scriptList  = scriptService.getScriptList(scriptMap);
		if(CollectionUtils.size(scriptList) > 0){
			taskTplDto.setScript(scriptList.get(0));
		}

		return taskTplDto;
	}




	
}
