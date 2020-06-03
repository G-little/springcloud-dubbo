package com.little.g.springcloud.mall.dto;

import com.little.g.springcloud.common.enums.Deleted;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LitemallGoodsDTO implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_goods
	 *
	 * @mbg.generated
	 */
	public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table litemall_goods
	 *
	 * @mbg.generated
	 */
	public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.id
	 *
	 * @mbg.generated
	 */
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.goods_sn
	 *
	 * @mbg.generated
	 */
	private String goodsSn;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.name
	 *
	 * @mbg.generated
	 */
	private String name;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.category_id
	 *
	 * @mbg.generated
	 */
	private Integer categoryId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.brand_id
	 *
	 * @mbg.generated
	 */
	private Integer brandId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.gallery
	 *
	 * @mbg.generated
	 */
	private String[] gallery;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.keywords
	 *
	 * @mbg.generated
	 */
	private String keywords;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.brief
	 *
	 * @mbg.generated
	 */
	private String brief;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.is_on_sale
	 *
	 * @mbg.generated
	 */
	private Boolean isOnSale;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.sort_order
	 *
	 * @mbg.generated
	 */
	private Short sortOrder;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.pic_url
	 *
	 * @mbg.generated
	 */
	private String picUrl;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.share_url
	 *
	 * @mbg.generated
	 */
	private String shareUrl;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.is_new
	 *
	 * @mbg.generated
	 */
	private Boolean isNew;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.is_hot
	 *
	 * @mbg.generated
	 */
	private Boolean isHot;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.unit
	 *
	 * @mbg.generated
	 */
	private String unit;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.counter_price
	 *
	 * @mbg.generated
	 */
	private BigDecimal counterPrice;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.retail_price
	 *
	 * @mbg.generated
	 */
	private BigDecimal retailPrice;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.add_time
	 *
	 * @mbg.generated
	 */
	private LocalDateTime addTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.update_time
	 *
	 * @mbg.generated
	 */
	private LocalDateTime updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.deleted
	 *
	 * @mbg.generated
	 */
	private Boolean deleted;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column litemall_goods.detail
	 *
	 * @mbg.generated
	 */
	private String detail;

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

	public String getGoodsSn() {
		return goodsSn;
	}

	public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
        this.name = name;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String[] getGallery() {
		return gallery;
	}

	public void setGallery(String[] gallery) {
		this.gallery = gallery;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
        this.keywords = keywords;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
        this.brief = brief;
    }

    public Boolean getOnSale() {
		return isOnSale;
	}

    public void setOnSale(Boolean onSale) {
        isOnSale = onSale;
	}

	public Short getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Boolean getNew() {
		return isNew;
	}

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public Boolean getHot() {
		return isHot;
	}

    public void setHot(Boolean hot) {
        isHot = hot;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
        this.unit = unit;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
        this.detail = detail;
	}

}
