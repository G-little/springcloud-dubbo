package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallNoticeDTO;

import java.util.List;

public interface LitemallNoticeService {

    List<LitemallNoticeDTO> querySelective(String title, String content, Integer page,
                                           Integer limit, String sort, String order);

    int updateById(LitemallNoticeDTO notice);

    void deleteById(Integer id);

    void add(LitemallNoticeDTO notice);

    LitemallNoticeDTO findById(Integer id);

    void deleteByIds(List<Integer> ids);

}
