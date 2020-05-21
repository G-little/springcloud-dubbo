package com.little.g.springcloud.user.mapper;

import com.little.g.springcloud.user.model.UserDeviceToken;
import com.little.g.springcloud.user.model.UserDeviceTokenExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDeviceTokenMapper {

	long countByExample(UserDeviceTokenExample example);

	int deleteByExample(UserDeviceTokenExample example);

	int deleteByPrimaryKey(Long id);

	int insert(UserDeviceToken record);

	int insertSelective(UserDeviceToken record);

	List<UserDeviceToken> selectByExample(UserDeviceTokenExample example);

	UserDeviceToken selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") UserDeviceToken record,
			@Param("example") UserDeviceTokenExample example);

	int updateByExample(@Param("record") UserDeviceToken record,
			@Param("example") UserDeviceTokenExample example);

	int updateByPrimaryKeySelective(UserDeviceToken record);

	int updateByPrimaryKey(UserDeviceToken record);

}
