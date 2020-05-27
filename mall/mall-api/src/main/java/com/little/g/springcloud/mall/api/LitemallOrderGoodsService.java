package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallOrderGoodsDTO;

import java.util.List;

public interface LitemallOrderGoodsService {
    int add(LitemallOrderGoodsDTO orderGoods);

    List<LitemallOrderGoodsDTO> queryByOid(Integer orderId);

    List<LitemallOrderGoodsDTO> findByOidAndGid(Integer orderId, Integer goodsId);

    LitemallOrderGoodsDTO findById(Integer id);

    void updateById(LitemallOrderGoodsDTO orderGoods);

    Short getComments(Integer orderId);

    boolean checkExist(Integer goodsId);

    void deleteByOrderId(Integer orderId);
}
