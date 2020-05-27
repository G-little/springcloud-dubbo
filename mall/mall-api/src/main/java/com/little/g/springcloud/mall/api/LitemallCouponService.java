package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallCouponDTO;

import java.util.List;

public interface LitemallCouponService {
    List<LitemallCouponDTO> queryList(int offset, int limit, String sort, String order);

    List<LitemallCouponDTO> queryAvailableList(Integer userId, int offset, int limit);

    List<LitemallCouponDTO> queryList(int offset, int limit);

    LitemallCouponDTO findById(Integer id);

    LitemallCouponDTO findByCode(String code);

    List<LitemallCouponDTO> queryRegister();

    List<LitemallCouponDTO> querySelective(String name, Short type, Short status, Integer page, Integer limit, String sort, String order);

    void add(LitemallCouponDTO coupon);

    int updateById(LitemallCouponDTO coupon);

    void deleteById(Integer id);

    String generateCode();
}
