package com.little.g.springcloud.mall.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel("优惠券")
public class CouponVo {
    @ApiModelProperty("用户领取后的优惠券ID")
    private Integer id;
    @ApiModelProperty("优惠券ID")
    private Integer cid;
    @ApiModelProperty("优惠券名称")
    private String name;
    @ApiModelProperty("优惠券介绍，通常是显示优惠券使用限制文字")
    private String desc;
    @ApiModelProperty("优惠券标签，例如新人专用")
    private String tag;
    @ApiModelProperty("最少消费金额才能使用优惠券。")
    private BigDecimal min;
    @ApiModelProperty("优惠金额")
    private BigDecimal discount;
    @ApiModelProperty("使用券开始时间")
    private LocalDateTime startTime;
    @ApiModelProperty("使用券结束时间")
    private LocalDateTime endTime;
    @ApiModelProperty("是否可用")
    private boolean available;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
