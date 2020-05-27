package com.little.g.springcloud.mall.service;

import com.little.g.springcloud.mall.api.CouponAssignService;
import com.little.g.springcloud.mall.api.LitemallCouponService;
import com.little.g.springcloud.mall.api.LitemallCouponUserService;
import com.little.g.springcloud.mall.dto.LitemallCouponDTO;
import com.little.g.springcloud.mall.dto.LitemallCouponUserDTO;
import com.little.g.springcloud.mall.util.CouponConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CouponAssignServiceImpl implements CouponAssignService {

    @Autowired
    private LitemallCouponUserService couponUserService;
    @Autowired
    private LitemallCouponService couponService;

    /**
     * 分发注册优惠券
     *
     * @param userId
     * @return
     */
    @Override
    public void assignForRegister(Integer userId) {
        List<LitemallCouponDTO> couponList = couponService.queryRegister();
        for (LitemallCouponDTO coupon : couponList) {
            Integer couponId = coupon.getId();

            Integer count = couponUserService.countUserAndCoupon(userId, couponId);
            if (count > 0) {
                continue;
            }

            Short limit = coupon.getLimit();
            while (limit > 0) {
                LitemallCouponUserDTO couponUser = new LitemallCouponUserDTO();
                couponUser.setCouponId(couponId);
                couponUser.setUserId(userId);
                Short timeType = coupon.getTimeType();
                if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
                    couponUser.setStartTime(coupon.getStartTime());
                    couponUser.setEndTime(coupon.getEndTime());
                } else {
                    LocalDateTime now = LocalDateTime.now();
                    couponUser.setStartTime(now);
                    couponUser.setEndTime(now.plusDays(coupon.getDays()));
                }
                couponUserService.add(couponUser);

                limit--;
            }
        }

    }

}
