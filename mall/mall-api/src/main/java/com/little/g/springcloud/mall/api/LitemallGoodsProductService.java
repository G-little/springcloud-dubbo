package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallGoodsProductDTO;

import java.util.List;

public interface LitemallGoodsProductService {
    List<LitemallGoodsProductDTO> queryByGid(Integer gid);

    LitemallGoodsProductDTO findById(Integer id);

    void deleteById(Integer id);

    void add(LitemallGoodsProductDTO goodsProduct);

    int count();

    void deleteByGid(Integer gid);

    int addStock(Integer id, Short num);

    int reduceStock(Integer id, Short num);

    void updateById(LitemallGoodsProductDTO product);
}
