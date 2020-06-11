package com.little.g.springcloud.admin.web.vo;

import com.little.g.springcloud.mall.dto.LitemallOrderDTO;
import com.little.g.springcloud.mall.dto.LitemallOrderGoodsDTO;
import com.little.g.springcloud.user.dto.UserDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("管理员订单详情")
@Data
public class AdminOrderDetailVo {

	@ApiModelProperty("订单信息")
	private LitemallOrderDTO order;

	@ApiModelProperty("商品信息")
	private List<LitemallOrderGoodsDTO> orderGoods;

	@ApiModelProperty("用户信息")
	private UserDTO user;

}
