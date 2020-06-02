package com.little.g.springcloud.mall.dto;

import com.little.g.springcloud.common.enums.Deleted;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LitemallGoodsAttributeDTO implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_goods_attribute
	 *
	 * @mbg.generated
	 */
	public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_goods_attribute
	 *
	 * @mbg.generated
	 */
	public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_attribute.id
	 *
	 * @mbg.generated
	 */
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_attribute.goods_id
	 *
	 * @mbg.generated
	 */
	private Integer goodsId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_attribute.attribute
	 *
	 * @mbg.generated
	 */
	private String attribute;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_attribute.value
	 *
	 * @mbg.generated
	 */
	private String value;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_attribute.add_time
	 *
	 * @mbg.generated
	 */
	private LocalDateTime addTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_attribute.update_time
	 *
	 * @mbg.generated
	 */
	private LocalDateTime updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods_attribute.deleted
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

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
        this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
        this.value = value;
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
