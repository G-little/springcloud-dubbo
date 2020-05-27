package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallCouponUserDTO;

import java.util.List;

public interface LitemallCouponUserService {

	Integer countCoupon(Integer couponId);

	Integer countUserAndCoupon(Integer userId, Integer couponId);

	void add(LitemallCouponUserDTO couponUser);

	PageInfo<LitemallCouponUserDTO> queryList(Integer userId, Integer couponId, Short status,
                                              Integer page, Integer size, String sort, String order);

	List<LitemallCouponUserDTO> queryAll(Integer userId, Integer couponId);

	List<LitemallCouponUserDTO> queryAll(Integer userId);

	LitemallCouponUserDTO queryOne(Integer userId, Integer couponId);

	LitemallCouponUserDTO findById(Integer id);

	int update(LitemallCouponUserDTO couponUser);

	List<LitemallCouponUserDTO> queryExpired();

}
