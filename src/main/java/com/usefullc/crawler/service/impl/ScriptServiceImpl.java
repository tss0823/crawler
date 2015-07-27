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
import com.usefullc.crawler.dao.IScriptDao;
import com.usefullc.crawler.domain.Script;
import com.usefullc.crawler.service.IScriptService;


@Service
public class ScriptServiceImpl extends AbstractBaseService implements IScriptService {
	
	@Autowired
	private IScriptDao scriptDao;

	public Script getScript(Long id) {
		return scriptDao.getScript(id);
	}

	public List<Script> getScriptList(Map<String, Object> queryMap) {
		return scriptDao.getScriptList(queryMap);
	}
	
	@Override
	public Pagination<Script> getScriptListPage(Map<String, Object> queryMap) {
		return scriptDao.getScriptListPage(queryMap);
	}
	

	public void insertScript(Script script) {
		scriptDao.insertScript(script);
	}

	public void updateScript(Script script) {
		scriptDao.updateScript(script);
	}

	public void deleteScript(Long id) {
		scriptDao.deleteScript(id);
	}




	
}
