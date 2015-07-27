/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 任务模板参数
 * @author admin
 *
 * @2015-05-31 18
 */
public class TaskTpParam extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  键 * */
	private java.lang.String key;
		
	/**  值 * */
	private java.lang.String value;
		
	/**  ID * */
	private java.lang.Long id;
		
	/**  创建时间 * */
	private java.util.Date gmtCreate;
		
	/**  修改时间 * */
	private java.util.Date gmtModify;
		
	/**  删除状态（0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	/**  任务模板ID * */
	private java.lang.Long taskTpId;
		
	
	public TaskTpParam(){
	}

	public void setKey(java.lang.String value) {
		this.key = value;
	}
	
	public java.lang.String getKey() {
		return this.key;
	}
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	
	public java.lang.String getValue() {
		return this.value;
	}
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setGmtCreate(java.util.Date value) {
		this.gmtCreate = value;
	}
	
	public java.util.Date getGmtCreate() {
		return this.gmtCreate;
	}
	public void setGmtModify(java.util.Date value) {
		this.gmtModify = value;
	}
	
	public java.util.Date getGmtModify() {
		return this.gmtModify;
	}
	public void setDelState(java.lang.Boolean value) {
		this.delState = value;
	}
	
	public java.lang.Boolean getDelState() {
		return this.delState;
	}
	public void setTaskTpId(java.lang.Long value) {
		this.taskTpId = value;
	}
	
	public java.lang.Long getTaskTpId() {
		return this.taskTpId;
	}
	



}

