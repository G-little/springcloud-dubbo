package com.little.g.springcloud.sample.model;

import java.io.Serializable;

public class AiAssistant implements Serializable {

	private Long id;

	private Long uid;

	private Byte role;

	private String roleName;

	private String avatar;

	private String sayHi;

	private Long hiId;

	private String aiName;

	private String masterName;

	private Byte timbre;

	private Long createTime;

	private Long updateTime;

	private Byte gender;

	private String headAvatar;

	private Integer speed;

	private String anniImgs;

	private Integer answerCount;

	private Byte type;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Byte getRole() {
		return role;
	}

	public void setRole(Byte role) {
		this.role = role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar == null ? null : avatar.trim();
	}

	public String getSayHi() {
		return sayHi;
	}

	public void setSayHi(String sayHi) {
		this.sayHi = sayHi == null ? null : sayHi.trim();
	}

	public Long getHiId() {
		return hiId;
	}

	public void setHiId(Long hiId) {
		this.hiId = hiId;
	}

	public String getAiName() {
		return aiName;
	}

	public void setAiName(String aiName) {
		this.aiName = aiName == null ? null : aiName.trim();
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName == null ? null : masterName.trim();
	}

	public Byte getTimbre() {
		return timbre;
	}

	public void setTimbre(Byte timbre) {
		this.timbre = timbre;
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

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getHeadAvatar() {
		return headAvatar;
	}

	public void setHeadAvatar(String headAvatar) {
		this.headAvatar = headAvatar == null ? null : headAvatar.trim();
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public String getAnniImgs() {
		return anniImgs;
	}

	public void setAnniImgs(String anniImgs) {
		this.anniImgs = anniImgs == null ? null : anniImgs.trim();
	}

	public Integer getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

}