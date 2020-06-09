package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("首页数据")
@Data
public class IndexDataVo {

	@ApiModelProperty("广告")
	private List<LitemallAdDTO> banner;

	@ApiModelProperty("类目")
	private List<LitemallCategoryDTO> channel;

	@ApiModelProperty("优惠券")
	private List<LitemallCouponDTO> couponList;

	@ApiModelProperty("最新商品")
	private List<LitemallGoodsDTO> newGoodsList;

	@ApiModelProperty("热门商品")
	private List<LitemallGoodsDTO> hotGoodsList;

	@ApiModelProperty("品牌列表")
	private List<LitemallBrandDTO> brandList;

	@ApiModelProperty("专题列表")
	private List<LitemallTopicDTO> topicList;

	@ApiModelProperty("团购规则")
	private List<GrouponRuleVo> grouponList;

	@ApiModelProperty("首页产品分类信息列表")
	private List<IndexCategoryVo> floorGoodsList;

}
