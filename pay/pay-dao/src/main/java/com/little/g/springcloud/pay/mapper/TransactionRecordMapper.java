package com.little.g.springcloud.pay.mapper;

import com.little.g.springcloud.pay.model.TransactionRecord;
import com.little.g.springcloud.pay.model.TransactionRecordExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TransactionRecordMapper {

	long countByExample(TransactionRecordExample example);

	int deleteByExample(TransactionRecordExample example);

	int deleteByPrimaryKey(Long id);

	int insert(TransactionRecord record);

	int insertSelective(TransactionRecord record);

	List<TransactionRecord> selectByExampleWithRowbounds(TransactionRecordExample example,
			RowBounds rowBounds);

	List<TransactionRecord> selectByExample(TransactionRecordExample example);

	TransactionRecord selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") TransactionRecord record,
			@Param("example") TransactionRecordExample example);

	int updateByExample(@Param("record") TransactionRecord record,
			@Param("example") TransactionRecordExample example);

	int updateByPrimaryKeySelective(TransactionRecord record);

	int updateByPrimaryKey(TransactionRecord record);

}