package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallAftersaleDTO;

import java.util.List;

public interface LitemallAftersaleService {

	LitemallAftersaleDTO findById(Integer id);

	LitemallAftersaleDTO findById(Integer userId, Integer id);

	PageInfo<LitemallAftersaleDTO> queryList(Integer userId, Short status, Integer page,
			Integer limit, String sort, String order);

	PageInfo<LitemallAftersaleDTO> querySelective(Integer orderId, String aftersaleSn,
			Short status, Integer page, Integer limit, String sort, String order);

	int countByAftersaleSn(Integer userId, String aftersaleSn);

	// TODO 这里应该产生一个唯一的编号，但是实际上这里仍然存在两个售后编号相同的可能性
	String generateAftersaleSn(Integer userId);

	void add(LitemallAftersaleDTO aftersale);

	void deleteByIds(List<Integer> ids);

	void deleteById(Integer id);

	void deleteByOrderId(Integer userId, Integer orderId);

	void updateById(LitemallAftersaleDTO aftersale);

	LitemallAftersaleDTO findByOrderId(Integer userId, Integer orderId);

}
