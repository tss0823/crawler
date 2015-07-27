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
import com.usefullc.crawler.dao.ITaskInstanceDao;
import com.usefullc.crawler.domain.TaskInstance;

@Service
public class TaskInstanceDaoImpl extends AbstractBaseDao implements ITaskInstanceDao {
	
	@Override
	public TaskInstance getTaskInstance(Long id) {
		 return (TaskInstance) sqlSession.selectOne("TaskInstanceMapper.getTaskInstance", id);
	}
	
	@Override
	public List<TaskInstance> getTaskInstanceList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("TaskInstanceMapper.getTaskInstanceList",queryMap);
	}
	
	public Pagination<TaskInstance> getTaskInstanceListPage(Map<String,Object> queryMap) {
		Pagination<TaskInstance> page = getPagination(queryMap, "TaskInstanceMapper.getTaskInstanceListPage", "TaskInstanceMapper.getTaskInstanceListPageCount");
		return page;
	}

	@Override
	public void insertTaskInstance(TaskInstance domain) {
		sqlSession.insert("TaskInstanceMapper.insertTaskInstance", domain);
	}

	@Override
	public void updateTaskInstance(TaskInstance domain) {
		sqlSession.update("TaskInstanceMapper.updateTaskInstance", domain);
	}

	@Override
	public void deleteTaskInstance(Long id) {
		sqlSession.delete("TaskInstanceMapper.deleteTaskInstance", id);
	}

}
