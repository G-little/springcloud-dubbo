package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallGoodsAttributeDTO;

import java.util.List;

public interface LitemallGoodsAttributeService {

    List<LitemallGoodsAttributeDTO> queryByGid(Integer goodsId);

    void add(LitemallGoodsAttributeDTO goodsAttribute);

    LitemallGoodsAttributeDTO findById(Integer id);

    void deleteByGid(Integer gid);

    void deleteById(Integer id);

    void updateById(LitemallGoodsAttributeDTO attribute);

}
