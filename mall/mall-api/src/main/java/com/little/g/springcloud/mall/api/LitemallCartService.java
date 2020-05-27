package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallCartDTO;

import java.math.BigDecimal;
import java.util.List;

public interface LitemallCartService {

    LitemallCartDTO queryExist(Integer goodsId, Integer productId, Integer userId);

    void add(LitemallCartDTO cart);

    int updateById(LitemallCartDTO cart);

    List<LitemallCartDTO> queryByUid(int userId);

    List<LitemallCartDTO> queryByUidAndChecked(Integer userId);

    int delete(List<Integer> productIdList, int userId);

    LitemallCartDTO findById(Integer id);

    LitemallCartDTO findById(Integer userId, Integer id);

    int updateCheck(Integer userId, List<Integer> idsList, Boolean checked);

    void clearGoods(Integer userId);

    List<LitemallCartDTO> querySelective(Integer userId, Integer goodsId, Integer page,
                                         Integer limit, String sort, String order);

    void deleteById(Integer id);

    boolean checkExist(Integer goodsId);

    void updateProduct(Integer id, String goodsSn, String goodsName, BigDecimal price,
                       String url);

}
