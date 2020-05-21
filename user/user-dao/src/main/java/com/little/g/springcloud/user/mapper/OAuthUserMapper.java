package com.little.g.springcloud.user.mapper;

import com.little.g.springcloud.user.model.OAuthUser;
import com.little.g.springcloud.user.model.OAuthUserExample;
import com.little.g.springcloud.user.model.OAuthUserKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OAuthUserMapper {

	long countByExample(OAuthUserExample example);

	int deleteByExample(OAuthUserExample example);

	int deleteByPrimaryKey(OAuthUserKey key);

	int insert(OAuthUser record);

	int insertSelective(OAuthUser record);

	List<OAuthUser> selectByExample(OAuthUserExample example);

	OAuthUser selectByPrimaryKey(OAuthUserKey key);

	int updateByExampleSelective(@Param("record") OAuthUser record,
			@Param("example") OAuthUserExample example);

	int updateByExample(@Param("record") OAuthUser record,
			@Param("example") OAuthUserExample example);

	int updateByPrimaryKeySelective(OAuthUser record);

	int updateByPrimaryKey(OAuthUser record);

}
