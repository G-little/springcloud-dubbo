package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallFeedbackDTO;

public interface LitemallFeedbackService {

	Integer add(LitemallFeedbackDTO feedback);

	PageInfo<LitemallFeedbackDTO> querySelective(Integer userId, String username,
			Integer page, Integer limit, String sort, String order);

}
