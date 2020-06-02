package com.little.g.springcloud.mall.dto;

import com.little.g.springcloud.common.enums.Deleted;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LitemallGoodsSpecificationDTO implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_specification.id
	 *
	 * @mbg.generated
	 */
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_specification.goods_id
	 *
	 * @mbg.generated
	 */
	private Integer goodsId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_specification.specification
	 *
	 * @mbg.generated
	 */
	private String specification;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_specification.value
	 *
	 * @mbg.generated
	 */
	private String value;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_specification.pic_url
	 *
	 * @mbg.generated
	 */
	private String picUrl;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_specification.add_time
	 *
	 * @mbg.generated
	 */
	private LocalDateTime addTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_specification.update_time
	 *
	 * @mbg.generated
	 */
	private LocalDateTime updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_specification.deleted
	 *
	 * @mbg.generated
	 */
	private Boolean deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
        this.specification = specification;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
        this.value = value;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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
