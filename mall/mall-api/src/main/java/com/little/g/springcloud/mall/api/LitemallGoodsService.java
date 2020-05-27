package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;

import java.util.List;

public interface LitemallGoodsService {
    List<LitemallGoodsDTO> queryByHot(int offset, int limit);

    List<LitemallGoodsDTO> queryByNew(int offset, int limit);

    List<LitemallGoodsDTO> queryByCategory(List<Integer> catList, int offset, int limit);

    List<LitemallGoodsDTO> queryByCategory(Integer catId, int offset, int limit);

    List<LitemallGoodsDTO> querySelective(Integer catId, Integer brandId, String keywords, Boolean isHot, Boolean isNew, Integer offset, Integer limit, String sort, String order);

    List<LitemallGoodsDTO> querySelective(Integer goodsId, String goodsSn, String name, Integer page, Integer size, String sort, String order);

    LitemallGoodsDTO findById(Integer id);

    LitemallGoodsDTO findByIdVO(Integer id);

    Integer queryOnSale();

    int updateById(LitemallGoodsDTO goods);

    void deleteById(Integer id);

    void add(LitemallGoodsDTO goods);

    int count();

    List<Integer> getCatIds(Integer brandId, String keywords, Boolean isHot, Boolean isNew);

    boolean checkExistByName(String name);

    List<LitemallGoodsDTO> queryByIds(Integer[] ids);
}
