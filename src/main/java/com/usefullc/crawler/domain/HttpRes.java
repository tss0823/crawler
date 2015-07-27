/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.crawler.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 返回内容
 * @author admin
 *
 * @2015-05-31 18
 */
public class HttpRes extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  任务模板ID * */
	private java.lang.Long taskTpId;
		
	/**  任务模板实例ID * */
	private java.lang.Long taskInstId;
		
	/**  URL * */
	private java.lang.String url;
		
	/**  Header * */
	private java.lang.String header;
		
	/**  Body * */
	private java.lang.String body;
		
	/**  状态(http返回状态) * */
	private java.lang.String status;
		
	/**  ID * */
	private java.lang.Long id;
		
	/**  创建时间 * */
	private java.util.Date gmtCreate;
		
	/**  修改时间 * */
	private java.util.Date gmtModify;
		
	/**  删除状态（0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	
	public HttpRes(){
	}

	public void setTaskTpId(java.lang.Long value) {
		this.taskTpId = value;
	}
	
	public java.lang.Long getTaskTpId() {
		return this.taskTpId;
	}
	public void setTaskInstId(java.lang.Long value) {
		this.taskInstId = value;
	}
	
	public java.lang.Long getTaskInstId() {
		return this.taskInstId;
	}
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setHeader(java.lang.String value) {
		this.header = value;
	}
	
	public java.lang.String getHeader() {
		return this.header;
	}
	public void setBody(java.lang.String value) {
		this.body = value;
	}
	
	public java.lang.String getBody() {
		return this.body;
	}
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
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

