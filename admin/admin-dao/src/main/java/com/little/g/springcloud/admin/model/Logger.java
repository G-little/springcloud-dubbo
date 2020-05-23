package com.little.g.springcloud.admin.model;


import com.little.g.springcloud.admin.common.BaseEntity;

import java.util.Date;


/**
 * 数据库表操作日志
 * */
public class Logger extends BaseEntity {

	//操作ID.一个操作动作可能会涉及到多张表的日志
	private Long actionId;

	// 日志类型 @LogType
	private Integer logType;

	private String tableName;

	private String tableDescription;

	private String comment;
	/**
	 * 创建时间
	 * */
	private Date gmtCreate;

	/**
	 * 上次修改时间
	 * */
	private Date gmtModified;

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableDescription() {
		return tableDescription;
	}

	public void setTableDescription(String tableDescription) {
		this.tableDescription = tableDescription;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
