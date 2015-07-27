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
import com.usefullc.crawler.dao.ITaskTpParamDao;
import com.usefullc.crawler.domain.TaskTpParam;

@Service
public class TaskTpParamDaoImpl extends AbstractBaseDao implements ITaskTpParamDao {
	
	@Override
	public TaskTpParam getTaskTpParam(Long id) {
		 return (TaskTpParam) sqlSession.selectOne("TaskTpParamMapper.getTaskTpParam", id);
	}
	
	@Override
	public List<TaskTpParam> getTaskTpParamList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("TaskTpParamMapper.getTaskTpParamList",queryMap);
	}
	
	public Pagination<TaskTpParam> getTaskTpParamListPage(Map<String,Object> queryMap) {
		Pagination<TaskTpParam> page = getPagination(queryMap, "TaskTpParamMapper.getTaskTpParamListPage", "TaskTpParamMapper.getTaskTpParamListPageCount");
		return page;
	}

	@Override
	public void insertTaskTpParam(TaskTpParam domain) {
		sqlSession.insert("TaskTpParamMapper.insertTaskTpParam", domain);
	}

	@Override
	public void updateTaskTpParam(TaskTpParam domain) {
		sqlSession.update("TaskTpParamMapper.updateTaskTpParam", domain);
	}

	@Override
	public void deleteTaskTpParam(Long id) {
		sqlSession.delete("TaskTpParamMapper.deleteTaskTpParam", id);
	}

}
