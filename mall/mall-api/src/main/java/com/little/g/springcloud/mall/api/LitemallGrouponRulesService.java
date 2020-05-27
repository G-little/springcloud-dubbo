package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallGrouponRulesDTO;

import java.util.List;

public interface LitemallGrouponRulesService {

    int createRules(LitemallGrouponRulesDTO rules);

    LitemallGrouponRulesDTO findById(Integer id);

    List<LitemallGrouponRulesDTO> queryByGoodsId(Integer goodsId);

    int countByGoodsId(Integer goodsId);

    List<LitemallGrouponRulesDTO> queryByStatus(Short status);

    List<LitemallGrouponRulesDTO> queryList(Integer page, Integer limit);

    List<LitemallGrouponRulesDTO> queryList(Integer page, Integer limit, String sort,
                                            String order);

    boolean isExpired(LitemallGrouponRulesDTO rules);

    List<LitemallGrouponRulesDTO> querySelective(String goodsId, Integer page,
                                                 Integer size, String sort, String order);

    void delete(Integer id);

    int updateById(LitemallGrouponRulesDTO grouponRules);

}
