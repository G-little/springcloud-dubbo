package com.little.g.springcloud.mall.dto;

import com.little.g.springcloud.common.enums.Deleted;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel("订单产品")
public class LitemallOrderGoodsDTO implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_order_goods
	 *
	 * @mbg.generated
	 */
	public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_order_goods
	 *
	 * @mbg.generated
	 */
	public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.id
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("订单产品Id")
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.order_id
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("订单号")
	private Integer orderId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.goods_id
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("产品ID")
	private Integer goodsId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.goods_name
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("产品名")
	private String goodsName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.goods_sn
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("产品SN")
	private String goodsSn;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.product_id
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("产品ID")
	private Integer productId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.number
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("编号")
	private Short number;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.price
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("价格")
	private BigDecimal price;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.specifications
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("商品规格")
	private String[] specifications;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.pic_url
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("规格图片")
	private String picUrl;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.comment
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("描述")
	private Integer comment;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.add_time
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("添加时间")
	private LocalDateTime addTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.update_time
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("更新时间")
	private LocalDateTime updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_order_goods.deleted
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("是否删除")
	private Boolean deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public String getGoodsSn() {
		return goodsSn;
	}

	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Short getNumber() {
		return number;
	}

	public void setNumber(Short number) {
		this.number = number;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String[] getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String[] specifications) {
		this.specifications = specifications;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
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
