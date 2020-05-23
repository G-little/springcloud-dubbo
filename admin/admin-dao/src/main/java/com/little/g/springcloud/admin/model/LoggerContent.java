package com.little.g.springcloud.admin.model;



import com.little.g.springcloud.admin.common.BaseEntity;

import java.util.Date;

public class LoggerContent extends BaseEntity {

	// 对应的日志ID
	private Long loggerId;

	// 字段名(数据库里字段名)
	private String columnName;

	// 字段描述(举例:由于数据库里字段名是business_id这样的,看不懂,因此为字段起了一个名字,如企业用户ID)
	private String columnDescription;

	private String oldValue;

	private String newValue;
	
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

	public Long getLoggerId() {
		return loggerId;
	}

	public void setLoggerId(Long loggerId) {
		this.loggerId = loggerId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnDescription() {
		return columnDescription;
	}

	public void setColumnDescription(String columnDescription) {
		this.columnDescription = columnDescription;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
