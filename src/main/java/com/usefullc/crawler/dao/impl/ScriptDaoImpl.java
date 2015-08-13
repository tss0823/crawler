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
import com.usefullc.crawler.dao.IScriptDao;
import com.usefullc.crawler.domain.Script;

@Service
public class ScriptDaoImpl extends AbstractBaseDao implements IScriptDao {
	
	@Override
	public Script getScript(Long id) {
		 return (Script) sqlSession.selectOne("ScriptMapper.getScript", id);
	}
	
	@Override
	public List<Script> getScriptList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("ScriptMapper.getScriptList",queryMap);
	}
	
	public Pagination<Script> getScriptListPage(Map<String,Object> queryMap) {
		Pagination<Script> page = getPagination(queryMap, "ScriptMapper.getScriptListPage", "ScriptMapper.getScriptListPageCount");
		return page;
	}

	@Override
	public void insertScript(Script domain) {
		sqlSession.insert("ScriptMapper.insertScript", domain);
	}

	@Override
	public void updateScript(Script domain) {
		sqlSession.update("ScriptMapper.updateScript", domain);
	}

	@Override
	public void deleteScript(Long id) {
		sqlSession.delete("ScriptMapper.deleteScript", id);
	}

	public void deleteScriptByTaskTpId(Long taskTpId) {
		sqlSession.delete("ScriptMapper.deleteScriptByTaskTpId", taskTpId);
	}

}
