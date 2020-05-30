package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallTopicService;
import com.little.g.springcloud.mall.dto.LitemallTopicDTO;
import com.little.g.springcloud.mall.mapper.LitemallTopicMapper;
import com.little.g.springcloud.mall.model.LitemallTopic;
import com.little.g.springcloud.mall.model.LitemallTopic.Column;
import com.little.g.springcloud.mall.model.LitemallTopicExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service(protocol = "dubbo")
public class LitemallTopicServiceImpl implements LitemallTopicService {

	@Resource
	private LitemallTopicMapper topicMapper;

	private Column[] columns = new Column[] { Column.id, Column.title, Column.subtitle,
			Column.price, Column.picUrl, Column.readCount };

	@Override
	public List<LitemallTopicDTO> queryList(int offset, int limit) {
		return queryList(offset, limit, "add_time", "desc");
	}

	@Override
	public List<LitemallTopicDTO> queryList(int offset, int limit, String sort,
			String order) {
		LitemallTopicExample example = new LitemallTopicExample();
		example.or().andDeletedEqualTo(false);
		example.setOrderByClause(sort + " " + order);
		PageHelper.startPage(offset, limit);
		return DTOUtil.convert2List(
				topicMapper.selectByExampleSelective(example, columns),
				LitemallTopicDTO.class);
	}

	@Override
	public int queryTotal() {
		LitemallTopicExample example = new LitemallTopicExample();
		example.or().andDeletedEqualTo(false);
		return (int) topicMapper.countByExample(example);
	}

	@Override
	public LitemallTopicDTO findById(Integer id) {
		LitemallTopicExample example = new LitemallTopicExample();
		example.or().andIdEqualTo(id).andDeletedEqualTo(false);
		return DTOUtil.convert2T(topicMapper.selectOneByExampleWithBLOBs(example),
				LitemallTopicDTO.class);
	}

	@Override
	public List<LitemallTopicDTO> queryRelatedList(Integer id, int offset, int limit) {
		LitemallTopicExample example = new LitemallTopicExample();
		example.or().andIdEqualTo(id).andDeletedEqualTo(false);
		List<LitemallTopicDTO> topics = DTOUtil.convert2List(
				topicMapper.selectByExample(example), LitemallTopicDTO.class);
		if (topics.size() == 0) {
			return queryList(offset, limit, "add_time", "desc");
		}
		LitemallTopicDTO topic = topics.get(0);

		example = new LitemallTopicExample();
		example.or().andIdNotEqualTo(topic.getId()).andDeletedEqualTo(false);
		PageHelper.startPage(offset, limit);
		List<LitemallTopicDTO> relateds = DTOUtil.convert2List(
				topicMapper.selectByExampleWithBLOBs(example), LitemallTopicDTO.class);
		if (relateds.size() != 0) {
			return relateds;
		}

		return queryList(offset, limit, "add_time", "desc");
	}

	@Override
	public PageInfo<LitemallTopicDTO> querySelective(String title, String subtitle,
			Integer page, Integer limit, String sort, String order) {
		LitemallTopicExample example = new LitemallTopicExample();
		LitemallTopicExample.Criteria criteria = example.createCriteria();

		if (!StringUtils.isEmpty(title)) {
			criteria.andTitleLike("%" + title + "%");
		}
		if (!StringUtils.isEmpty(subtitle)) {
			criteria.andSubtitleLike("%" + subtitle + "%");
		}
		criteria.andDeletedEqualTo(false);

		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
			example.setOrderByClause(sort + " " + order);
		}

		PageHelper.startPage(page, limit);
		return DTOUtil.convert2Page(topicMapper.selectByExampleWithBLOBs(example),
				LitemallTopicDTO.class);
	}

	@Override
	public int updateById(LitemallTopicDTO topic) {
		topic.setUpdateTime(LocalDateTime.now());
		LitemallTopicExample example = new LitemallTopicExample();
		example.or().andIdEqualTo(topic.getId());
		return topicMapper.updateByExampleSelective(
				DTOUtil.convert2T(topic, LitemallTopic.class), example);
	}

	@Override
	public void deleteById(Integer id) {
		topicMapper.logicalDeleteByPrimaryKey(id);
	}

	@Override
	public void add(LitemallTopicDTO topic) {
		topic.setAddTime(LocalDateTime.now());
		topic.setUpdateTime(LocalDateTime.now());
		topicMapper.insertSelective(DTOUtil.convert2T(topic, LitemallTopic.class));
	}

	@Override
	public void deleteByIds(List<Integer> ids) {
		LitemallTopicExample example = new LitemallTopicExample();
		example.or().andIdIn(ids).andDeletedEqualTo(false);
		LitemallTopic topic = new LitemallTopic();
		topic.setUpdateTime(LocalDateTime.now());
		topic.setDeleted(true);
		topicMapper.updateByExampleSelective(topic, example);
	}

}
