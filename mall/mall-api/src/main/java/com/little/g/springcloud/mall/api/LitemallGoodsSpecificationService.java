package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.GoodsSpecificationListDTO;
import com.little.g.springcloud.mall.dto.LitemallGoodsSpecificationDTO;

import java.util.List;

public interface LitemallGoodsSpecificationService {

	List<LitemallGoodsSpecificationDTO> queryByGid(Integer id);

	LitemallGoodsSpecificationDTO findById(Integer id);

	void deleteByGid(Integer gid);

	void add(LitemallGoodsSpecificationDTO goodsSpecification);

	List<GoodsSpecificationListDTO> getSpecificationVoList(Integer id);

	void updateById(LitemallGoodsSpecificationDTO specification);

}
