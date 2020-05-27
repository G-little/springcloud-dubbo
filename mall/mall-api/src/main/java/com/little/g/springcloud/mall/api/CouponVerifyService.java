package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallCouponDTO;

import java.math.BigDecimal;

public interface CouponVerifyService {

    LitemallCouponDTO checkCoupon(Integer userId, Integer couponId, Integer userCouponId,
                                  BigDecimal checkedGoodsPrice);

}
