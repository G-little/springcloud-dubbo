package com.little.g.springcloud.admin.dto;

import java.io.Serializable;
import java.util.Arrays;

public class AdminUserDTO implements Serializable {

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

	private Long[] privilege;

	private Byte type;

	private Integer roleId;

	private Long createTime;

	private Long updateTime;

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
		this.realName = realName;
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
		this.cardId = cardId;
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
		this.wxNum = wxNum;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
		this.contactName = contactName;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
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
		this.idCardImg1 = idCardImg1;
	}

	public String getIdCardImg2() {
		return idCardImg2;
	}

	public void setIdCardImg2(String idCardImg2) {
		this.idCardImg2 = idCardImg2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Long[] getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Long[] privilege) {
		this.privilege = privilege;
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

	@Override
	public String toString() {
		return "AdminUserDTO{" + "id=" + id + ", realName='" + realName + '\''
				+ ", gender=" + gender + ", cardId='" + cardId + '\'' + ", birthday="
				+ birthday + ", wxNum='" + wxNum + '\'' + ", mobile='" + mobile + '\''
				+ ", store=" + store + ", contactName='" + contactName + '\''
				+ ", contactMobile='" + contactMobile + '\'' + ", relationship="
				+ relationship + ", idCardImg1='" + idCardImg1 + '\'' + ", idCardImg2='"
				+ idCardImg2 + '\'' + ", password='" + password + '\'' + ", belongTo="
				+ belongTo + ", status=" + status + ", privilege="
				+ Arrays.toString(privilege) + ", type=" + type + ", createTime="
				+ createTime + ", updateTime=" + updateTime + '}';
	}

}