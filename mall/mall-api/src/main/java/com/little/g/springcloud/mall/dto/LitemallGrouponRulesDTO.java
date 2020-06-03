package com.little.g.springcloud.mall.dto;

import com.little.g.springcloud.common.enums.Deleted;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LitemallGrouponRulesDTO implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_groupon_rules
	 *
	 * @mbg.generated
	 */
	public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_groupon_rules
	 *
	 * @mbg.generated
	 */
	public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_groupon_rules.id
	 *
	 * @mbg.generated
	 */
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_groupon_rules.goods_id
	 *
	 * @mbg.generated
	 */
	private Integer goodsId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_groupon_rules.goods_name
	 *
	 * @mbg.generated
	 */
	private String goodsName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_groupon_rules.pic_url
	 *
	 * @mbg.generated
	 */
	private String picUrl;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_groupon_rules.discount
	 *
	 * @mbg.generated
	 */
	private BigDecimal discount;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_groupon_rules.discount_member
	 *
	 * @mbg.generated
	 */
	private Integer discountMember;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_groupon_rules.expire_time
	 *
	 * @mbg.generated
	 */
	private LocalDateTime expireTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_groupon_rules.status
	 *
	 * @mbg.generated
	 */
	private Short status;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_groupon_rules.add_time
	 *
	 * @mbg.generated
	 */
	private LocalDateTime addTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_groupon_rules.update_time
	 *
	 * @mbg.generated
	 */
	private LocalDateTime updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_groupon_rules.deleted
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getDiscountMember() {
		return discountMember;
	}

	public void setDiscountMember(Integer discountMember) {
		this.discountMember = discountMember;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
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
