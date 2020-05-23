package com.little.g.springcloud.pay.mapper;

import com.little.g.springcloud.pay.model.BalanceCheckpoint;
import com.little.g.springcloud.pay.model.BalanceCheckpointExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BalanceCheckpointMapper {

	long countByExample(BalanceCheckpointExample example);

	int deleteByExample(BalanceCheckpointExample example);

	int deleteByPrimaryKey(Long id);

	int insert(BalanceCheckpoint record);

	int insertSelective(BalanceCheckpoint record);

	List<BalanceCheckpoint> selectByExampleWithRowbounds(BalanceCheckpointExample example,
			RowBounds rowBounds);

	List<BalanceCheckpoint> selectByExample(BalanceCheckpointExample example);

	BalanceCheckpoint selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") BalanceCheckpoint record,
			@Param("example") BalanceCheckpointExample example);

	int updateByExample(@Param("record") BalanceCheckpoint record,
			@Param("example") BalanceCheckpointExample example);

	int updateByPrimaryKeySelective(BalanceCheckpoint record);

	int updateByPrimaryKey(BalanceCheckpoint record);

}