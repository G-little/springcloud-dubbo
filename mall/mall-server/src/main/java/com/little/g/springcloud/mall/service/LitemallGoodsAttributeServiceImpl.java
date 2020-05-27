package com.little.g.springcloud.mall.service;

import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallGoodsAttributeService;
import com.little.g.springcloud.mall.dto.LitemallGoodsAttributeDTO;
import com.little.g.springcloud.mall.mapper.LitemallGoodsAttributeMapper;
import com.little.g.springcloud.mall.model.LitemallGoodsAttribute;
import com.little.g.springcloud.mall.model.LitemallGoodsAttributeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallGoodsAttributeServiceImpl implements LitemallGoodsAttributeService {

	@Resource
	private LitemallGoodsAttributeMapper goodsAttributeMapper;

	@Override
	public List<LitemallGoodsAttributeDTO> queryByGid(Integer goodsId) {
		LitemallGoodsAttributeExample example = new LitemallGoodsAttributeExample();
		example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
		return DTOUtil.convert2List(goodsAttributeMapper.selectByExample(example),
				LitemallGoodsAttributeDTO.class);
	}

	@Override
	public void add(LitemallGoodsAttributeDTO goodsAttribute) {
		goodsAttribute.setAddTime(LocalDateTime.now());
		goodsAttribute.setUpdateTime(LocalDateTime.now());
		goodsAttributeMapper.insertSelective(
				DTOUtil.convert2T(goodsAttribute, LitemallGoodsAttribute.class));
	}

	@Override
	public LitemallGoodsAttributeDTO findById(Integer id) {
		return DTOUtil.convert2T(goodsAttributeMapper.selectByPrimaryKey(id),
				LitemallGoodsAttributeDTO.class);
	}

	@Override
	public void deleteByGid(Integer gid) {
		LitemallGoodsAttributeExample example = new LitemallGoodsAttributeExample();
		example.or().andGoodsIdEqualTo(gid);
		goodsAttributeMapper.logicalDeleteByExample(example);
	}

	@Override
	public void deleteById(Integer id) {
		goodsAttributeMapper.logicalDeleteByPrimaryKey(id);
	}

	@Override
	public void updateById(LitemallGoodsAttributeDTO attribute) {
		attribute.setUpdateTime(LocalDateTime.now());
		goodsAttributeMapper.updateByPrimaryKeySelective(
				DTOUtil.convert2T(attribute, LitemallGoodsAttribute.class));
	}

}
