package com.little.g.springcloud.mall.web.vo;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("商品详情Vo")
@Data
public class GoodsDetailVo {

	@ApiModelProperty("商品基本信息表")
	private LitemallGoodsDTO info;

	@ApiModelProperty("是否已收藏")
	private int userHasCollect;

	@ApiModelProperty("常见问题")
	private PageInfo<LitemallIssueDTO> issue;

	@ApiModelProperty("评论列表")
	private GoodsDetailCommentVo comment;

	@ApiModelProperty("商品规格")
	private List<GoodsSpecificationListDTO> specificationList;

	@ApiModelProperty("货品信息")
	private List<LitemallGoodsProductDTO> productList;

	@ApiModelProperty("属性列表")
	private List<LitemallGoodsAttributeDTO> attribute;

	@ApiModelProperty("属性列表")
	private LitemallBrandDTO brand;

	@ApiModelProperty("团购规则表")
	private List<LitemallGrouponRulesDTO> groupon;

	@ApiModelProperty("是否自动创建分享图片")
	private boolean share;

	@ApiModelProperty("分享图片地址")
	private String shareImage;

}
