package com.little.g.springcloud.mall.dto;

import com.little.g.springcloud.common.enums.Deleted;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel("专题")
public class LitemallTopicDTO implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_topic
	 *
	 * @mbg.generated
	 */
	public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_topic
	 *
	 * @mbg.generated
	 */
	public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_topic.id
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("专题Id")
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_topic.title
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("标题")
	private String title;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_topic.subtitle
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("专题子标题")
	private String subtitle;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_topic.price
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("专题相关商品最低价")
	private BigDecimal price;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_topic.read_count
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("专题阅读量")
	private String readCount;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_topic.pic_url
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("专题图片")
	private String picUrl;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_topic.sort_order
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("排序")
	private Integer sortOrder;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_topic.goods
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("专题相关商品，采用JSON数组格式")
	private Integer[] goods;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_topic.add_time
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("添加时间")
	private LocalDateTime addTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_topic.update_time
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("更新时间")
	private LocalDateTime updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_topic.deleted
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("是否已删除")
	private Boolean deleted;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_topic.content
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty("专题内容")
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getReadCount() {
		return readCount;
	}

	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer[] getGoods() {
		return goods;
	}

	public void setGoods(Integer[] goods) {
		this.goods = goods;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
