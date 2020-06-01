package com.little.g.springcloud.user.model;

import java.util.ArrayList;
import java.util.Arrays;

public class UserDeviceToken {

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
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.device_id
	 *
	 * @mbg.generated
	 */
	private String deviceId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.device_type
	 *
	 * @mbg.generated
	 */
	private Byte deviceType;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.os
	 *
	 * @mbg.generated
	 */
	private String os;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.access_token
	 *
	 * @mbg.generated
	 */
	private String accessToken;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.access_expires_in
	 *
	 * @mbg.generated
	 */
	private Long accessExpiresIn;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.refresh_token
	 *
	 * @mbg.generated
	 */
	private String refreshToken;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.refresh_expires_in
	 *
	 * @mbg.generated
	 */
	private Long refreshExpiresIn;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.create_time
	 *
	 * @mbg.generated
	 */
	private Long createTime;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column user_device_token.update_time
	 *
	 * @mbg.generated
	 */
	private Long updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column user_device_token.id
	 * @return the value of user_device_token.id
	 *
	 * @mbg.generated
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column user_device_token.id
	 * @param id the value for user_device_token.id
	 *
	 * @mbg.generated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column user_device_token.uid
	 * @return the value of user_device_token.uid
	 * @mbg.generated
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column user_device_token.uid
	 * @param uid the value for user_device_token.uid
	 *
	 * @mbg.generated
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column user_device_token.device_id
	 * @return the value of user_device_token.device_id
	 *
	 * @mbg.generated
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column user_device_token.device_id
	 * @param deviceId the value for user_device_token.device_id
	 *
	 * @mbg.generated
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId == null ? null : deviceId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column user_device_token.device_type
	 * @return the value of user_device_token.device_type
	 *
	 * @mbg.generated
	 */
	public Byte getDeviceType() {
		return deviceType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column user_device_token.device_type
	 * @param deviceType the value for user_device_token.device_type
	 *
	 * @mbg.generated
	 */
	public void setDeviceType(Byte deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column user_device_token.os
	 * @return the value of user_device_token.os
	 *
	 * @mbg.generated
	 */
	public String getOs() {
		return os;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column user_device_token.os
	 * @param os the value for user_device_token.os
	 *
	 * @mbg.generated
	 */
	public void setOs(String os) {
		this.os = os == null ? null : os.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column user_device_token.access_token
	 * @return the value of user_device_token.access_token
	 *
	 * @mbg.generated
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column user_device_token.access_token
	 * @param accessToken the value for user_device_token.access_token
	 *
	 * @mbg.generated
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken == null ? null : accessToken.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column user_device_token.access_expires_in
	 * @return the value of user_device_token.access_expires_in
	 *
	 * @mbg.generated
	 */
	public Long getAccessExpiresIn() {
		return accessExpiresIn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column user_device_token.access_expires_in
	 * @param accessExpiresIn the value for user_device_token.access_expires_in
	 *
	 * @mbg.generated
	 */
	public void setAccessExpiresIn(Long accessExpiresIn) {
		this.accessExpiresIn = accessExpiresIn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column user_device_token.refresh_token
	 * @return the value of user_device_token.refresh_token
	 *
	 * @mbg.generated
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column user_device_token.refresh_token
	 * @param refreshToken the value for user_device_token.refresh_token
	 *
	 * @mbg.generated
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken == null ? null : refreshToken.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column user_device_token.refresh_expires_in
	 * @return the value of user_device_token.refresh_expires_in
	 *
	 * @mbg.generated
	 */
	public Long getRefreshExpiresIn() {
		return refreshExpiresIn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column user_device_token.refresh_expires_in
	 * @param refreshExpiresIn the value for user_device_token.refresh_expires_in
	 *
	 * @mbg.generated
	 */
	public void setRefreshExpiresIn(Long refreshExpiresIn) {
		this.refreshExpiresIn = refreshExpiresIn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column user_device_token.create_time
	 * @return the value of user_device_token.create_time
	 *
	 * @mbg.generated
	 */
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column user_device_token.create_time
	 * @param createTime the value for user_device_token.create_time
	 *
	 * @mbg.generated
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column user_device_token.update_time
	 * @return the value of user_device_token.update_time
	 *
	 * @mbg.generated
	 */
	public Long getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column user_device_token.update_time
	 * @param updateTime the value for user_device_token.update_time
	 *
	 * @mbg.generated
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table user_device_token
	 *
	 * @mbg.generated
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", uid=").append(uid);
		sb.append(", deviceId=").append(deviceId);
		sb.append(", deviceType=").append(deviceType);
		sb.append(", os=").append(os);
		sb.append(", accessToken=").append(accessToken);
		sb.append(", accessExpiresIn=").append(accessExpiresIn);
		sb.append(", refreshToken=").append(refreshToken);
		sb.append(", refreshExpiresIn=").append(refreshExpiresIn);
		sb.append(", createTime=").append(createTime);
		sb.append(", updateTime=").append(updateTime);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table user_device_token
	 *
	 * @mbg.generated
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		UserDeviceToken other = (UserDeviceToken) that;
		return (this.getId() == null ? other.getId() == null
				: this.getId().equals(other.getId()))
				&& (this.getUid() == null ? other.getUid() == null
						: this.getUid().equals(other.getUid()))
				&& (this.getDeviceId() == null ? other.getDeviceId() == null
						: this.getDeviceId().equals(other.getDeviceId()))
				&& (this.getDeviceType() == null ? other.getDeviceType() == null
						: this.getDeviceType().equals(other.getDeviceType()))
				&& (this.getOs() == null ? other.getOs() == null
						: this.getOs().equals(other.getOs()))
				&& (this.getAccessToken() == null ? other.getAccessToken() == null
						: this.getAccessToken().equals(other.getAccessToken()))
				&& (this.getAccessExpiresIn() == null ? other.getAccessExpiresIn() == null
						: this.getAccessExpiresIn().equals(other.getAccessExpiresIn()))
				&& (this.getRefreshToken() == null ? other.getRefreshToken() == null
						: this.getRefreshToken().equals(other.getRefreshToken()))
				&& (this.getRefreshExpiresIn() == null
						? other.getRefreshExpiresIn() == null
						: this.getRefreshExpiresIn().equals(other.getRefreshExpiresIn()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getUpdateTime() == null ? other.getUpdateTime() == null
						: this.getUpdateTime().equals(other.getUpdateTime()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table user_device_token
	 *
	 * @mbg.generated
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
		result = prime * result
				+ ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
		result = prime * result
				+ ((getDeviceType() == null) ? 0 : getDeviceType().hashCode());
		result = prime * result + ((getOs() == null) ? 0 : getOs().hashCode());
		result = prime * result
				+ ((getAccessToken() == null) ? 0 : getAccessToken().hashCode());
		result = prime * result
				+ ((getAccessExpiresIn() == null) ? 0 : getAccessExpiresIn().hashCode());
		result = prime * result
				+ ((getRefreshToken() == null) ? 0 : getRefreshToken().hashCode());
		result = prime * result + ((getRefreshExpiresIn() == null) ? 0
				: getRefreshExpiresIn().hashCode());
		result = prime * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result
				+ ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
		return result;
	}

	/**
	 * This enum was generated by MyBatis Generator. This enum corresponds to the database
	 * table user_device_token
	 *
	 * @mbg.generated
	 */
	public enum Column {

		id("id", "id", "BIGINT", false), uid("uid", "uid", "INTEGER", true), deviceId(
				"device_id", "deviceId", "VARCHAR", false), deviceType("device_type",
						"deviceType", "TINYINT",
						false), os("os", "os", "VARCHAR", false), accessToken(
								"access_token", "accessToken", "VARCHAR",
								false), accessExpiresIn("access_expires_in",
										"accessExpiresIn", "BIGINT", false), refreshToken(
												"refresh_token", "refreshToken",
												"VARCHAR", false), refreshExpiresIn(
														"refresh_expires_in",
														"refreshExpiresIn", "BIGINT",
														false), createTime("create_time",
																"createTime", "BIGINT",
																false), updateTime(
																		"update_time",
																		"updateTime",
																		"BIGINT", false);

		/**
		 * This field was generated by MyBatis Generator. This field corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		private static final String BEGINNING_DELIMITER = "`";

		/**
		 * This field was generated by MyBatis Generator. This field corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		private static final String ENDING_DELIMITER = "`";

		/**
		 * This field was generated by MyBatis Generator. This field corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		private final String column;

		/**
		 * This field was generated by MyBatis Generator. This field corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		private final boolean isColumnNameDelimited;

		/**
		 * This field was generated by MyBatis Generator. This field corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		private final String javaProperty;

		/**
		 * This field was generated by MyBatis Generator. This field corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		private final String jdbcType;

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		public String value() {
			return this.column;
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		public String getValue() {
			return this.column;
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		public String getJavaProperty() {
			return this.javaProperty;
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		public String getJdbcType() {
			return this.jdbcType;
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		Column(String column, String javaProperty, String jdbcType,
				boolean isColumnNameDelimited) {
			this.column = column;
			this.javaProperty = javaProperty;
			this.jdbcType = jdbcType;
			this.isColumnNameDelimited = isColumnNameDelimited;
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		public String desc() {
			return this.getEscapedColumnName() + " DESC";
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		public String asc() {
			return this.getEscapedColumnName() + " ASC";
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		public static Column[] excludes(Column... excludes) {
			ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
			if (excludes != null && excludes.length > 0) {
				columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
			}
			return columns.toArray(new Column[] {});
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		public static Column[] all() {
			return Column.values();
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		public String getEscapedColumnName() {
			if (this.isColumnNameDelimited) {
				return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column)
						.append(ENDING_DELIMITER).toString();
			}
			else {
				return this.column;
			}
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table user_device_token
		 *
		 * @mbg.generated
		 */
		public String getAliasedEscapedColumnName() {
			return this.getEscapedColumnName();
		}

	}

}
