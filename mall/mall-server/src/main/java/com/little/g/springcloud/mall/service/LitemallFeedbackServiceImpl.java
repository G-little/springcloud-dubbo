package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallFeedbackService;
import com.little.g.springcloud.mall.dto.LitemallFeedbackDTO;
import com.little.g.springcloud.mall.mapper.LitemallFeedbackMapper;
import com.little.g.springcloud.mall.model.LitemallFeedback;
import com.little.g.springcloud.mall.model.LitemallFeedbackExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @author Yogeek
 * @date 2018/8/27 11:39
 */
@Service(protocol = "dubbo")
public class LitemallFeedbackServiceImpl implements LitemallFeedbackService {

	@Autowired
	private LitemallFeedbackMapper feedbackMapper;

	@Override
	public Integer add(LitemallFeedbackDTO feedback) {
		feedback.setAddTime(LocalDateTime.now());
		feedback.setUpdateTime(LocalDateTime.now());
		return feedbackMapper
				.insertSelective(DTOUtil.convert2T(feedback, LitemallFeedback.class));
	}

	@Override
	public PageInfo<LitemallFeedbackDTO> querySelective(Integer userId, String username,
			Integer page, Integer limit, String sort, String order) {
		LitemallFeedbackExample example = new LitemallFeedbackExample();
		LitemallFeedbackExample.Criteria criteria = example.createCriteria();

		if (userId != null) {
			criteria.andUserIdEqualTo(userId);
		}
		if (!StringUtils.isEmpty(username)) {
			criteria.andUsernameLike("%" + username + "%");
		}
		criteria.andDeletedEqualTo(false);

		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
			example.setOrderByClause(sort + " " + order);
		}

		PageHelper.startPage(page, limit);
		return DTOUtil.convert2Page(feedbackMapper.selectByExample(example),
				LitemallFeedbackDTO.class);
	}

}
