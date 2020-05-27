package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallKeywordDTO;

import java.util.List;

public interface LitemallKeywordService {

    LitemallKeywordDTO queryDefault();

    List<LitemallKeywordDTO> queryHots();

    List<LitemallKeywordDTO> queryByKeyword(String keyword, Integer page, Integer limit);

    List<LitemallKeywordDTO> querySelective(String keyword, String url, Integer page,
                                            Integer limit, String sort, String order);

    void add(LitemallKeywordDTO keywords);

    LitemallKeywordDTO findById(Integer id);

    int updateById(LitemallKeywordDTO keywords);

    void deleteById(Integer id);

}
