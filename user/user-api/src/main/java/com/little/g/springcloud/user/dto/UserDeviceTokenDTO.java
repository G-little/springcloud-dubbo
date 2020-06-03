package com.little.g.springcloud.user.dto;

import java.io.Serializable;

public class UserDeviceTokenDTO implements Serializable {

	public UserDeviceTokenDTO() {
	}

	public UserDeviceTokenDTO(Integer uid, String deviceId, String accessToken) {
		this.uid = uid;
		this.deviceId = deviceId;
		this.accessToken = accessToken;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.id
	 *
	 * @mbg.generated
	 */
	private Long id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.uid
	 *
	 * @mbg.generated
	 */
	private Integer uid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.device_id
	 *
	 * @mbg.generated
	 */
	private String deviceId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.device_type
	 *
	 * @mbg.generated
	 */
	private Byte deviceType;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.os
	 *
	 * @mbg.generated
	 */
	private String os;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.access_token
	 *
	 * @mbg.generated
	 */
	private String accessToken;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.access_expires_in
	 *
	 * @mbg.generated
	 */
	private Long accessExpiresIn;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.refresh_token
	 *
	 * @mbg.generated
	 */
	private String refreshToken;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.refresh_expires_in
	 *
	 * @mbg.generated
	 */
	private Long refreshExpiresIn;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.create_time
	 *
	 * @mbg.generated
	 */
	private Long createTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.update_time
	 *
	 * @mbg.generated
	 */
	private Long updateTime;

	private String pass;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId == null ? null : deviceId.trim();
	}

	public Byte getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Byte deviceType) {
		this.deviceType = deviceType;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os == null ? null : os.trim();
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken == null ? null : accessToken.trim();
	}

	public Long getAccessExpiresIn() {
		return accessExpiresIn;
	}

	public void setAccessExpiresIn(Long accessExpiresIn) {
		this.accessExpiresIn = accessExpiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken == null ? null : refreshToken.trim();
	}

	public Long getRefreshExpiresIn() {
		return refreshExpiresIn;
	}

	public void setRefreshExpiresIn(Long refreshExpiresIn) {
		this.refreshExpiresIn = refreshExpiresIn;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
