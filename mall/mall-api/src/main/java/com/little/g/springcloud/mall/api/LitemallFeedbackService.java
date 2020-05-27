package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallFeedbackDTO;

import java.util.List;

public interface LitemallFeedbackService {
    Integer add(LitemallFeedbackDTO feedback);

    List<LitemallFeedbackDTO> querySelective(Integer userId, String username, Integer page, Integer limit, String sort, String order);
}
