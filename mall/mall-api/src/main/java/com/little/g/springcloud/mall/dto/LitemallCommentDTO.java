package com.little.g.springcloud.mall.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class LitemallCommentDTO implements Serializable {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database table litemall_comment
     *
     * @mbg.generated
     */
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database table litemall_comment
     *
     * @mbg.generated
     */
    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column litemall_comment.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column litemall_comment.value_id
     *
     * @mbg.generated
     */
    private Integer valueId;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column litemall_comment.type
     *
     * @mbg.generated
     */
    private Byte type;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column litemall_comment.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column litemall_comment.admin_content
     *
     * @mbg.generated
     */
    private String adminContent;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column litemall_comment.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column litemall_comment.has_picture
     *
     * @mbg.generated
     */
    private Boolean hasPicture;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column litemall_comment.pic_urls
     *
     * @mbg.generated
     */
    private String[] picUrls;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column litemall_comment.star
     *
     * @mbg.generated
     */
    private Short star;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column litemall_comment.add_time
     *
     * @mbg.generated
     */
    private LocalDateTime addTime;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column litemall_comment.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the
     * database column litemall_comment.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column litemall_comment.id
     *
     * @return the value of litemall_comment.id
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column litemall_comment.id
     *
     * @param id the value for litemall_comment.id
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column litemall_comment.value_id
     *
     * @return the value of litemall_comment.value_id
     * @mbg.generated
     */
    public Integer getValueId() {
        return valueId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column litemall_comment.value_id
     *
     * @param valueId the value for litemall_comment.value_id
     * @mbg.generated
     */
    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column litemall_comment.type
     *
     * @return the value of litemall_comment.type
     * @mbg.generated
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column litemall_comment.type
     *
     * @param type the value for litemall_comment.type
     * @mbg.generated
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column litemall_comment.content
     *
     * @return the value of litemall_comment.content
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column litemall_comment.content
     *
     * @param content the value for litemall_comment.content
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column litemall_comment.admin_content
     *
     * @return the value of litemall_comment.admin_content
     * @mbg.generated
     */
    public String getAdminContent() {
        return adminContent;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column litemall_comment.admin_content
     *
     * @param adminContent the value for litemall_comment.admin_content
     * @mbg.generated
     */
    public void setAdminContent(String adminContent) {
        this.adminContent = adminContent == null ? null : adminContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column litemall_comment.user_id
     *
     * @return the value of litemall_comment.user_id
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column litemall_comment.user_id
     *
     * @param userId the value for litemall_comment.user_id
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column litemall_comment.has_picture
     *
     * @return the value of litemall_comment.has_picture
     * @mbg.generated
     */
    public Boolean getHasPicture() {
        return hasPicture;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column litemall_comment.has_picture
     *
     * @param hasPicture the value for litemall_comment.has_picture
     * @mbg.generated
     */
    public void setHasPicture(Boolean hasPicture) {
        this.hasPicture = hasPicture;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column litemall_comment.pic_urls
     *
     * @return the value of litemall_comment.pic_urls
     * @mbg.generated
     */
    public String[] getPicUrls() {
        return picUrls;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column litemall_comment.pic_urls
     *
     * @param picUrls the value for litemall_comment.pic_urls
     * @mbg.generated
     */
    public void setPicUrls(String[] picUrls) {
        this.picUrls = picUrls;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column litemall_comment.star
     *
     * @return the value of litemall_comment.star
     * @mbg.generated
     */
    public Short getStar() {
        return star;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column litemall_comment.star
     *
     * @param star the value for litemall_comment.star
     * @mbg.generated
     */
    public void setStar(Short star) {
        this.star = star;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column litemall_comment.add_time
     *
     * @return the value of litemall_comment.add_time
     * @mbg.generated
     */
    public LocalDateTime getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column litemall_comment.add_time
     *
     * @param addTime the value for litemall_comment.add_time
     * @mbg.generated
     */
    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column litemall_comment.update_time
     *
     * @return the value of litemall_comment.update_time
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column litemall_comment.update_time
     *
     * @param updateTime the value for litemall_comment.update_time
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_comment
     *
     * @mbg.generated
     */
    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of
     * the database column litemall_comment.deleted
     *
     * @return the value of litemall_comment.deleted
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the
     * database column litemall_comment.deleted
     *
     * @param deleted the value for litemall_comment.deleted
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_comment
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", IS_DELETED=").append(IS_DELETED);
        sb.append(", NOT_DELETED=").append(NOT_DELETED);
        sb.append(", id=").append(id);
        sb.append(", valueId=").append(valueId);
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", adminContent=").append(adminContent);
        sb.append(", userId=").append(userId);
        sb.append(", hasPicture=").append(hasPicture);
        sb.append(", picUrls=").append(picUrls);
        sb.append(", star=").append(star);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_comment
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
        LitemallCommentDTO other = (LitemallCommentDTO) that;
        return (this.getId() == null ? other.getId() == null
                : this.getId().equals(other.getId()))
                && (this.getValueId() == null ? other.getValueId() == null
                : this.getValueId().equals(other.getValueId()))
                && (this.getType() == null ? other.getType() == null
                : this.getType().equals(other.getType()))
                && (this.getContent() == null ? other.getContent() == null
                : this.getContent().equals(other.getContent()))
                && (this.getAdminContent() == null ? other.getAdminContent() == null
                : this.getAdminContent().equals(other.getAdminContent()))
                && (this.getUserId() == null ? other.getUserId() == null
                : this.getUserId().equals(other.getUserId()))
                && (this.getHasPicture() == null ? other.getHasPicture() == null
                : this.getHasPicture().equals(other.getHasPicture()))
                && (Arrays.equals(this.getPicUrls(), other.getPicUrls()))
                && (this.getStar() == null ? other.getStar() == null
                : this.getStar().equals(other.getStar()))
                && (this.getAddTime() == null ? other.getAddTime() == null
                : this.getAddTime().equals(other.getAddTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null
                : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getDeleted() == null ? other.getDeleted() == null
                : this.getDeleted().equals(other.getDeleted()));
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_comment
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getValueId() == null) ? 0 : getValueId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result
                + ((getAdminContent() == null) ? 0 : getAdminContent().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result
                + ((getHasPicture() == null) ? 0 : getHasPicture().hashCode());
        result = prime * result + (Arrays.hashCode(getPicUrls()));
        result = prime * result + ((getStar() == null) ? 0 : getStar().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result
                + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator. This enum corresponds to the database
     * table litemall_comment
     *
     * @mbg.generated
     */
    public enum Deleted {

        NOT_DELETED(new Boolean("0"), "未删除"), IS_DELETED(new Boolean("1"), "已删除");

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        private final Boolean value;

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        public Boolean getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        public Boolean value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        public String getName() {
            return this.name;
        }

    }

    /**
     * This enum was generated by MyBatis Generator. This enum corresponds to the database
     * table litemall_comment
     *
     * @mbg.generated
     */
    public enum Column {

        id("id", "id", "INTEGER", false), valueId("value_id", "valueId", "INTEGER",
                false), type("type", "type", "TINYINT", true), content("content",
                "content", "VARCHAR", false), adminContent("admin_content",
                "adminContent", "VARCHAR", false), userId("user_id",
                "userId", "INTEGER", false), hasPicture(
                "has_picture", "hasPicture", "BIT",
                false), picUrls("pic_urls", "picUrls",
                "VARCHAR", false), star("star",
                "star", "SMALLINT",
                false), addTime(
                "add_time",
                "addTime",
                "TIMESTAMP",
                false), updateTime(
                "update_time",
                "updateTime",
                "TIMESTAMP",
                false), deleted(
                "deleted",
                "deleted",
                "BIT",
                false);

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator. This field corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table litemall_comment
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
         * database table litemall_comment
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table litemall_comment
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table litemall_comment
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
         * database table litemall_comment
         *
         * @mbg.generated
         */
        public static Column[] all() {
            return Column.values();
        }

        /**
         * This method was generated by MyBatis Generator. This method corresponds to the
         * database table litemall_comment
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
         * database table litemall_comment
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }

    }

}
