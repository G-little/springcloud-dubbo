package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallAddressService;
import com.little.g.springcloud.mall.dto.LitemallAddressDTO;
import com.little.g.springcloud.mall.mapper.LitemallAddressMapper;
import com.little.g.springcloud.mall.model.LitemallAddress;
import com.little.g.springcloud.mall.model.LitemallAddressExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service(protocol = "dubbo")
public class LitemallAddressServiceImpl implements LitemallAddressService {

	@Resource
	private LitemallAddressMapper addressMapper;

	@Override
	public List<LitemallAddressDTO> queryByUid(Integer uid) {
		LitemallAddressExample example = new LitemallAddressExample();
		example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
		return selectListByExample(example);
	}

	private List<LitemallAddressDTO> selectListByExample(LitemallAddressExample example) {
		return addressMapper.selectByExample(example).stream().map(add -> {
			LitemallAddressDTO dto = new LitemallAddressDTO();

			BeanUtils.copyProperties(add, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public LitemallAddressDTO query(Integer userId, Integer id) {
		LitemallAddressExample example = new LitemallAddressExample();
		example.or().andIdEqualTo(id).andUserIdEqualTo(userId).andDeletedEqualTo(false);
		return selectOneByExample(example);
	}

	private LitemallAddressDTO selectOneByExample(LitemallAddressExample example) {
		LitemallAddress litemallAddress = addressMapper.selectOneByExample(example);
		if (litemallAddress == null) {
			return null;
		}
		LitemallAddressDTO dto = new LitemallAddressDTO();
		BeanUtils.copyProperties(litemallAddress, dto);
		return dto;
	}

	@Override
	public int add(LitemallAddressDTO address) {
		address.setAddTime(LocalDateTime.now());
		address.setUpdateTime(LocalDateTime.now());
		LitemallAddress add = new LitemallAddress();
		BeanUtils.copyProperties(address, add);
		return addressMapper.insertSelective(add);
	}

	@Override
	public int update(LitemallAddressDTO address) {
		address.setUpdateTime(LocalDateTime.now());
		LitemallAddress add = new LitemallAddress();
		BeanUtils.copyProperties(address, add);
		return addressMapper.updateByPrimaryKeySelective(add);
	}

	@Override
	public void delete(Integer id) {
		addressMapper.logicalDeleteByPrimaryKey(id);
	}

	@Override
	public LitemallAddressDTO findDefault(Integer userId) {
		LitemallAddressExample example = new LitemallAddressExample();
		example.or().andUserIdEqualTo(userId).andIsDefaultEqualTo(true)
				.andDeletedEqualTo(false);
		return selectOneByExample(example);
	}

	@Override
	public void resetDefault(Integer userId) {
		LitemallAddress address = new LitemallAddress();
		address.setIsDefault(false);
		address.setUpdateTime(LocalDateTime.now());
		LitemallAddressExample example = new LitemallAddressExample();
		example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
		addressMapper.updateByExampleSelective(address, example);
	}

	@Override
	public PageInfo<LitemallAddressDTO> querySelective(Integer userId, String name,
			Integer page, Integer limit, String sort, String order) {
		LitemallAddressExample example = new LitemallAddressExample();
		LitemallAddressExample.Criteria criteria = example.createCriteria();

		if (userId != null) {
			criteria.andUserIdEqualTo(userId);
		}
		if (!StringUtils.isEmpty(name)) {
			criteria.andNameLike("%" + name + "%");
		}
		criteria.andDeletedEqualTo(false);

		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
			example.setOrderByClause(sort + " " + order);
		}

		PageHelper.startPage(page, limit);

		List<LitemallAddress> list = addressMapper.selectByExample(example);
		return DTOUtil.convert2Page(list, LitemallAddressDTO.class);

	}

}
