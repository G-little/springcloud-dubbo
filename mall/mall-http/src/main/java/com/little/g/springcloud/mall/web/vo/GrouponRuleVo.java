package com.little.g.springcloud.mall.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel("团购规则")
public class GrouponRuleVo {

	@ApiModelProperty("产品ID")
	private Integer id;

	@ApiModelProperty("产品名")
	private String name;

	@ApiModelProperty("产品介绍")
	private String brief;

	@ApiModelProperty("产品图片")
	private String picUrl;

	@ApiModelProperty("专柜价格")
	private BigDecimal counterPrice;

	@ApiModelProperty("零售价")
	private BigDecimal retailPrice;

	@ApiModelProperty("团购价")
	private BigDecimal grouponPrice;

	@ApiModelProperty("优惠金额")
	private BigDecimal grouponDiscount;

	@ApiModelProperty("达到优惠条件的人数")
	private Integer grouponMember;

	@ApiModelProperty("过期时间")
	private LocalDateTime expireTime;

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public BigDecimal getGrouponDiscount() {
		return grouponDiscount;
	}

	public void setGrouponDiscount(BigDecimal grouponDiscount) {
		this.grouponDiscount = grouponDiscount;
	}

	public Integer getGrouponMember() {
		return grouponMember;
	}

	public void setGrouponMember(Integer grouponMember) {
		this.grouponMember = grouponMember;
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

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public BigDecimal getCounterPrice() {
		return counterPrice;
	}

	public void setCounterPrice(BigDecimal counterPrice) {
		this.counterPrice = counterPrice;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getGrouponPrice() {
		return grouponPrice;
	}

	public void setGrouponPrice(BigDecimal grouponPrice) {
		this.grouponPrice = grouponPrice;
	}

}
