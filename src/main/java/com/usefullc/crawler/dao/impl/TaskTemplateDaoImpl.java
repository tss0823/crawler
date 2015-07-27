/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.dao.AbstractBaseDao;
import com.usefullc.crawler.dao.ITaskTemplateDao;
import com.usefullc.crawler.domain.TaskTemplate;

@Service
public class TaskTemplateDaoImpl extends AbstractBaseDao implements ITaskTemplateDao {
	
	@Override
	public TaskTemplate getTaskTemplate(Long id) {
		 return (TaskTemplate) sqlSession.selectOne("TaskTemplateMapper.getTaskTemplate", id);
	}
	
	@Override
	public List<TaskTemplate> getTaskTemplateList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("TaskTemplateMapper.getTaskTemplateList",queryMap);
	}
	
	public Pagination<TaskTemplate> getTaskTemplateListPage(Map<String,Object> queryMap) {
		Pagination<TaskTemplate> page = getPagination(queryMap, "TaskTemplateMapper.getTaskTemplateListPage", "TaskTemplateMapper.getTaskTemplateListPageCount");
		return page;
	}

	@Override
	public void insertTaskTemplate(TaskTemplate domain) {
		sqlSession.insert("TaskTemplateMapper.insertTaskTemplate", domain);
	}

	@Override
	public void updateTaskTemplate(TaskTemplate domain) {
		sqlSession.update("TaskTemplateMapper.updateTaskTemplate", domain);
	}

	@Override
	public void deleteTaskTemplate(Long id) {
		sqlSession.delete("TaskTemplateMapper.deleteTaskTemplate", id);
	}

}
