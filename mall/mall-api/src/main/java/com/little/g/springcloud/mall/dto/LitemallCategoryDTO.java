package com.little.g.springcloud.mall.dto;

import com.little.g.springcloud.common.enums.Deleted;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LitemallCategoryDTO implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_category
	 *
	 * @mbg.generated
	 */
	public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_category
	 *
	 * @mbg.generated
	 */
	public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_category.id
	 *
	 * @mbg.generated
	 */
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_category.name
	 *
	 * @mbg.generated
	 */
	private String name;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_category.keywords
	 *
	 * @mbg.generated
	 */
	private String keywords;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_category.desc
	 *
	 * @mbg.generated
	 */
	private String desc;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_category.pid
	 *
	 * @mbg.generated
	 */
	private Integer pid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_category.icon_url
	 *
	 * @mbg.generated
	 */
	private String iconUrl;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_category.pic_url
	 *
	 * @mbg.generated
	 */
	private String picUrl;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_category.level
	 *
	 * @mbg.generated
	 */
	private String level;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_category.sort_order
	 *
	 * @mbg.generated
	 */
	private Byte sortOrder;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_category.add_time
	 *
	 * @mbg.generated
	 */
	private LocalDateTime addTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_category.update_time
	 *
	 * @mbg.generated
	 */
	private LocalDateTime updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_category.deleted
	 *
	 * @mbg.generated
	 */
	private Boolean deleted;

    public static Boolean getIsDeleted() {
        return IS_DELETED;
    }

    public static Boolean getNotDeleted() {
        return NOT_DELETED;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
        this.name = name;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
        this.keywords = keywords;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
        this.desc = desc;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
        this.level = level;
	}

	public Byte getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Byte sortOrder) {
		this.sortOrder = sortOrder;
	}

	public LocalDateTime getAddTime() {
		return addTime;
	}

	public void setAddTime(LocalDateTime addTime) {
		this.addTime = addTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
