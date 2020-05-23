package com.little.g.springcloud.pay.mapper;

import com.little.g.springcloud.pay.model.UserAccount;
import com.little.g.springcloud.pay.model.UserAccountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAccountMapper {

	long countByExample(UserAccountExample example);

	int deleteByExample(UserAccountExample example);

	int deleteByPrimaryKey(Long uid);

	int insert(UserAccount record);

	int insertSelective(UserAccount record);

	List<UserAccount> selectByExample(UserAccountExample example);

	UserAccount selectByPrimaryKey(Long uid);

	int updateByExampleSelective(@Param("record") UserAccount record,
			@Param("example") UserAccountExample example);

	int updateByExample(@Param("record") UserAccount record,
			@Param("example") UserAccountExample example);

	int updateByPrimaryKeySelective(UserAccount record);

	int updateByPrimaryKey(UserAccount record);

}