package com.little.g.springcloud.admin.model;

import java.io.Serializable;

public class AdminUser implements Serializable {

	private Integer id;

	private String realName;

	private Byte gender;

	private String cardId;

	private Long birthday;

	private String wxNum;

	private String mobile;

	private Integer store;

	private String contactName;

	private String contactMobile;

	private Byte relationship;

	private String idCardImg1;

	private String idCardImg2;

	private String password;

	private Byte belongTo;

	private Byte status;

	private String privilege;

	private Byte type;

	private Long createTime;

	private Long updateTime;

	private Integer roleId;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId == null ? null : cardId.trim();
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	public String getWxNum() {
		return wxNum;
	}

	public void setWxNum(String wxNum) {
		this.wxNum = wxNum == null ? null : wxNum.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName == null ? null : contactName.trim();
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile == null ? null : contactMobile.trim();
	}

	public Byte getRelationship() {
		return relationship;
	}

	public void setRelationship(Byte relationship) {
		this.relationship = relationship;
	}

	public String getIdCardImg1() {
		return idCardImg1;
	}

	public void setIdCardImg1(String idCardImg1) {
		this.idCardImg1 = idCardImg1 == null ? null : idCardImg1.trim();
	}

	public String getIdCardImg2() {
		return idCardImg2;
	}

	public void setIdCardImg2(String idCardImg2) {
		this.idCardImg2 = idCardImg2 == null ? null : idCardImg2.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Byte getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(Byte belongTo) {
		this.belongTo = belongTo;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege == null ? null : privilege.trim();
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}