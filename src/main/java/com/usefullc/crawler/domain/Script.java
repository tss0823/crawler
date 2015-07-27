/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 脚本
 * @author admin
 *
 * @2015-05-31 18
 */
public class Script extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  模板ID * */
	private java.lang.Long taskTpId;
		
	/**  脚本内容 * */
	private java.lang.String content;
		
	/**  ID * */
	private java.lang.Long id;
		
	/**  创建时间 * */
	private java.util.Date gmtCreate;
		
	/**  修改时间 * */
	private java.util.Date gmtModify;
		
	/**  删除状态（0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	
	public Script(){
	}

	public void setTaskTpId(java.lang.Long value) {
		this.taskTpId = value;
	}
	
	public java.lang.Long getTaskTpId() {
		return this.taskTpId;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
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
	



}

