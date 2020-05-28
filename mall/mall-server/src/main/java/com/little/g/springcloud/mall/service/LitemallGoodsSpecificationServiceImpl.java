package com.little.g.springcloud.mall.service;

import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallGoodsSpecificationService;
import com.little.g.springcloud.mall.dto.LitemallGoodsSpecificationDTO;
import com.little.g.springcloud.mall.mapper.LitemallGoodsSpecificationMapper;
import com.little.g.springcloud.mall.model.LitemallGoodsSpecification;
import com.little.g.springcloud.mall.model.LitemallGoodsSpecificationExample;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(protocol = "dubbo")
public class LitemallGoodsSpecificationServiceImpl
		implements LitemallGoodsSpecificationService {

	@Resource
	private LitemallGoodsSpecificationMapper goodsSpecificationMapper;

	@Override
	public List<LitemallGoodsSpecificationDTO> queryByGid(Integer id) {
		LitemallGoodsSpecificationExample example = new LitemallGoodsSpecificationExample();
		example.or().andGoodsIdEqualTo(id).andDeletedEqualTo(false);
		return DTOUtil.convert2List(goodsSpecificationMapper.selectByExample(example),
				LitemallGoodsSpecificationDTO.class);
	}

	@Override
	public LitemallGoodsSpecificationDTO findById(Integer id) {
		return DTOUtil.convert2T(goodsSpecificationMapper.selectByPrimaryKey(id),
				LitemallGoodsSpecificationDTO.class);
	}

	@Override
	public void deleteByGid(Integer gid) {
		LitemallGoodsSpecificationExample example = new LitemallGoodsSpecificationExample();
		example.or().andGoodsIdEqualTo(gid);
		goodsSpecificationMapper.logicalDeleteByExample(example);
	}

	@Override
	public void add(LitemallGoodsSpecificationDTO goodsSpecification) {
		goodsSpecification.setAddTime(LocalDateTime.now());
		goodsSpecification.setUpdateTime(LocalDateTime.now());
		goodsSpecificationMapper.insertSelective(
				DTOUtil.convert2T(goodsSpecification, LitemallGoodsSpecification.class));
	}

	/**
	 * [ { name: '', valueList: [ {}, {}] }, { name: '', valueList: [ {}, {}] } ]
	 * @param id
	 * @return
	 */
	@Override
	public Object getSpecificationVoList(Integer id) {
		List<LitemallGoodsSpecificationDTO> goodsSpecificationList = queryByGid(id);

		Map<String, VO> map = new HashMap<>();
		List<VO> specificationVoList = new ArrayList<>();

		for (LitemallGoodsSpecificationDTO goodsSpecification : goodsSpecificationList) {
			String specification = goodsSpecification.getSpecification();
			VO goodsSpecificationVo = map.get(specification);
			if (goodsSpecificationVo == null) {
				goodsSpecificationVo = new VO();
				goodsSpecificationVo.setName(specification);
				List<LitemallGoodsSpecificationDTO> valueList = new ArrayList<>();
				valueList.add(goodsSpecification);
				goodsSpecificationVo.setValueList(valueList);
				map.put(specification, goodsSpecificationVo);
				specificationVoList.add(goodsSpecificationVo);
			}
			else {
				List<LitemallGoodsSpecificationDTO> valueList = goodsSpecificationVo
						.getValueList();
				valueList.add(goodsSpecification);
			}
		}

		return specificationVoList;
	}

	@Override
	public void updateById(LitemallGoodsSpecificationDTO specification) {
		specification.setUpdateTime(LocalDateTime.now());
		goodsSpecificationMapper.updateByPrimaryKeySelective(
				DTOUtil.convert2T(specification, LitemallGoodsSpecification.class));
	}

	private class VO {

		private String name;

		private List<LitemallGoodsSpecificationDTO> valueList;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<LitemallGoodsSpecificationDTO> getValueList() {
			return valueList;
		}

		public void setValueList(List<LitemallGoodsSpecificationDTO> valueList) {
			this.valueList = valueList;
		}

	}

}
