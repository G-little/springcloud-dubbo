package com.little.g.springcloud.mall.dto;

import com.little.g.springcloud.common.enums.Deleted;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel
public class LitemallCartDTO implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_cart
	 *
	 * @mbg.generated
	 */
	public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_cart
	 *
	 * @mbg.generated
	 */
	public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.id
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("ID")
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.user_id
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("用户ID")
	private Integer userId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.goods_id
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("产品ID")
	private Integer goodsId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.goods_sn
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("产品SN")
	private String goodsSn;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.goods_name
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("产品名")
	private String goodsName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.product_id
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("产品ID")
	private Integer productId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.price
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("价格")
	private BigDecimal price;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.number
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("数量")
	private Short number;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.specifications
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("规格")
	private String[] specifications;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.checked
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("选中")
	private Boolean checked;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.pic_url
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("图片")
	private String picUrl;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.add_time
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("添加时间")
	private LocalDateTime addTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.update_time
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("更新时间")
	private LocalDateTime updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_cart.deleted
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("删除状态")
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsSn() {
		return goodsSn;
	}

	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Short getNumber() {
		return number;
	}

	public void setNumber(Short number) {
		this.number = number;
	}

	public String[] getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String[] specifications) {
		this.specifications = specifications;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
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
