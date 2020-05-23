package com.little.g.springcloud.pay.mapper;

import com.little.g.springcloud.pay.model.FrozenRecord;
import com.little.g.springcloud.pay.model.FrozenRecordExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface FrozenRecordMapper {

	long countByExample(FrozenRecordExample example);

	int deleteByExample(FrozenRecordExample example);

	int deleteByPrimaryKey(String tradeNum);

	int insert(FrozenRecord record);

	int insertSelective(FrozenRecord record);

	List<FrozenRecord> selectByExampleWithRowbounds(FrozenRecordExample example,
			RowBounds rowBounds);

	List<FrozenRecord> selectByExample(FrozenRecordExample example);

	FrozenRecord selectByPrimaryKey(String tradeNum);

	int updateByExampleSelective(@Param("record") FrozenRecord record,
			@Param("example") FrozenRecordExample example);

	int updateByExample(@Param("record") FrozenRecord record,
			@Param("example") FrozenRecordExample example);

	int updateByPrimaryKeySelective(FrozenRecord record);

	int updateByPrimaryKey(FrozenRecord record);

}