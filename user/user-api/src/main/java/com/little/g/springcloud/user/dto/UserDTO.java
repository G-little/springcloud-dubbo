package com.little.g.springcloud.user.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.uid
     *
     * @mbg.generated
     */
    private Integer uid;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.avatar
     *
     * @mbg.generated
     */
    private String avatar;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.gender
     *
     * @mbg.generated
     */
    private Byte gender;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.birthday
     *
     * @mbg.generated
     */
    private Long birthday;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.mobile
     *
     * @mbg.generated
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.update_time
     *
     * @mbg.generated
     */
    private Long updateTime;

	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

    @Override
    public String toString() {
        return "UserDTO{" + "uid=" + uid + ", avatar='" + avatar + '\'' + ", name='"
                + name + '\'' + ", gender=" + gender + ", birthday=" + birthday
                + ", status=" + status + ", mobile='" + mobile + '\'' + ", createTime="
                + createTime + ", updateTime=" + updateTime + '}';
    }

}
