/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 任务实例
 * @author admin
 *
 * @2015-05-31 18
 */
public class TaskInstance extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**  魔板ID * */
	private java.lang.Long taskTpId;
		
	/**  名称 * */
	private java.lang.String name;
		
	/**  类型（http,db） * */
	private java.lang.String type;
		
	/**  状态（1：完成，0：未完成） * */
	private java.lang.String status;
		
	/**  创建时间 * */
	private java.util.Date gmtCreate;
		
	/**  修改时间 * */
	private java.util.Date gmtModify;
		
	/**  删除状态（0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	
	public TaskInstance(){
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setTaskTpId(java.lang.Long value) {
		this.taskTpId = value;
	}
	
	public java.lang.Long getTaskTpId() {
		return this.taskTpId;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
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
	



}

