package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallIssueDTO;

import java.util.List;

public interface LitemallIssueService {
    void deleteById(Integer id);

    void add(LitemallIssueDTO issue);

    List<LitemallIssueDTO> querySelective(String question, Integer page, Integer limit, String sort, String order);

    int updateById(LitemallIssueDTO issue);

    LitemallIssueDTO findById(Integer id);
}
