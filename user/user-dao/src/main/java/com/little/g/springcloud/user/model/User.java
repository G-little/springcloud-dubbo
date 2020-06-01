package com.little.g.springcloud.user.model;

import java.util.ArrayList;
import java.util.Arrays;

public class User {

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
     *
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.gender
     *
     * @mbg.generated
     */
    private Byte gender;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.birthday
     *
     * @mbg.generated
     */
    private Long birthday;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.mobile
     *
     * @mbg.generated
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column user.update_time
     *
     * @mbg.generated
     */
    private Long updateTime;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column user.uid
     *
     * @return the value of user.uid
     * @mbg.generated
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column user.uid
     * @param uid the value for user.uid
     *
     * @mbg.generated
	 */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column user.avatar
     * @return the value of user.avatar
     *
     * @mbg.generated
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column user.avatar
     * @param avatar the value for user.avatar
     *
     * @mbg.generated
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column user.name
     * @return the value of user.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column user.name
     * @param name the value for user.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column user.gender
     * @return the value of user.gender
     *
     * @mbg.generated
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column user.gender
     * @param gender the value for user.gender
     *
     * @mbg.generated
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column user.birthday
     * @return the value of user.birthday
     *
     * @mbg.generated
     */
    public Long getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column user.birthday
     * @param birthday the value for user.birthday
     *
     * @mbg.generated
     */
    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column user.status
     * @return the value of user.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column user.status
     * @param status the value for user.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column user.mobile
     * @return the value of user.mobile
     *
     * @mbg.generated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column user.mobile
     * @param mobile the value for user.mobile
     *
     * @mbg.generated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column user.create_time
     * @return the value of user.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column user.create_time
     * @param createTime the value for user.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column user.update_time
     * @return the value of user.update_time
     *
     * @mbg.generated
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column user.update_time
     * @param updateTime the value for user.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table user
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", avatar=").append(avatar);
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", birthday=").append(birthday);
        sb.append(", status=").append(status);
        sb.append(", mobile=").append(mobile);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table user
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
        User other = (User) that;
        return (this.getUid() == null ? other.getUid() == null
                : this.getUid().equals(other.getUid()))
                && (this.getAvatar() == null ? other.getAvatar() == null
                : this.getAvatar().equals(other.getAvatar()))
                && (this.getName() == null ? other.getName() == null
                : this.getName().equals(other.getName()))
                && (this.getGender() == null ? other.getGender() == null
                : this.getGender().equals(other.getGender()))
                && (this.getBirthday() == null ? other.getBirthday() == null
                : this.getBirthday().equals(other.getBirthday()))
                && (this.getStatus() == null ? other.getStatus() == null
                : this.getStatus().equals(other.getStatus()))
                && (this.getMobile() == null ? other.getMobile() == null
                : this.getMobile().equals(other.getMobile()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null
                : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null
                : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table user
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result
                + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result
                + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result
                + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator. This enum corresponds to the database
     * table user
     *
     * @mbg.generated
     */
    public enum Column {

        uid("uid", "uid", "INTEGER", true), avatar("avatar", "avatar", "VARCHAR",
                false), name("name", "name", "VARCHAR", true), gender("gender", "gender",
                "TINYINT",
                false), birthday("birthday", "birthday", "BIGINT", false), status(
                "status", "status", "TINYINT", true), mobile("mobile",
                "mobile", "VARCHAR", false), createTime(
                "create_time", "createTime", "BIGINT",
                false), updateTime("update_time",
                "updateTime", "BIGINT", false);

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table user
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
         * database table user
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        public static Column[] all() {
            return Column.values();
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column)
                        .append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table user
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }

	}

}
