package com.little.g.springcloud.mall.service;

import com.little.g.springcloud.mall.api.CouponVerifyService;
import com.little.g.springcloud.mall.api.LitemallCouponService;
import com.little.g.springcloud.mall.api.LitemallCouponUserService;
import com.little.g.springcloud.mall.dto.LitemallCouponDTO;
import com.little.g.springcloud.mall.dto.LitemallCouponUserDTO;
import com.little.g.springcloud.mall.util.CouponConstant;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service(protocol = "dubbo")
public class CouponVerifyServiceImpl implements CouponVerifyService {

	@Autowired
	private LitemallCouponUserService couponUserService;

	@Autowired
	private LitemallCouponService couponService;

	/**
	 * 检测优惠券是否适合
	 * @param userId
	 * @param couponId
	 * @param checkedGoodsPrice
	 * @return
	 */
	@Override
	public LitemallCouponDTO checkCoupon(Integer userId, Integer couponId,
			Integer userCouponId, BigDecimal checkedGoodsPrice) {
		LitemallCouponDTO coupon = couponService.findById(couponId);
		if (coupon == null) {
			return null;
		}

		LitemallCouponUserDTO couponUser = couponUserService.findById(userCouponId);
		if (couponUser == null) {
			couponUser = couponUserService.queryOne(userId, couponId);
		}
		else if (!couponId.equals(couponUser.getCouponId())) {
			return null;
		}

		if (couponUser == null) {
			return null;
		}

		// 检查是否超期
		Short timeType = coupon.getTimeType();
		Short days = coupon.getDays();
		LocalDateTime now = LocalDateTime.now();
		if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
			if (now.isBefore(coupon.getStartTime()) || now.isAfter(coupon.getEndTime())) {
				return null;
			}
		}
		else if (timeType.equals(CouponConstant.TIME_TYPE_DAYS)) {
			LocalDateTime expired = couponUser.getAddTime().plusDays(days);
			if (now.isAfter(expired)) {
				return null;
			}
		}
		else {
			return null;
		}

		// 检测商品是否符合
		// TODO 目前仅支持全平台商品，所以不需要检测
		Short goodType = coupon.getGoodsType();
		if (!goodType.equals(CouponConstant.GOODS_TYPE_ALL)) {
			return null;
		}

		// 检测订单状态
		Short status = coupon.getStatus();
		if (!status.equals(CouponConstant.STATUS_NORMAL)) {
			return null;
		}
		// 检测是否满足最低消费
		if (checkedGoodsPrice.compareTo(coupon.getMin()) == -1) {
			return null;
		}

		return coupon;
	}

}
