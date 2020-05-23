package com.little.g.springcloud.admin.mapper;

import com.little.g.springcloud.admin.model.AdminUser;
import com.little.g.springcloud.admin.model.AdminUserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface AdminUserMapper {

	long countByExample(AdminUserExample example);

	int deleteByExample(AdminUserExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(AdminUser record);

	int insertSelective(AdminUser record);

	List<AdminUser> selectByExampleWithRowbounds(AdminUserExample example,
			RowBounds rowBounds);

	List<AdminUser> selectByExample(AdminUserExample example);

	AdminUser selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") AdminUser record,
			@Param("example") AdminUserExample example);

	int updateByExample(@Param("record") AdminUser record,
			@Param("example") AdminUserExample example);

	int updateByPrimaryKeySelective(AdminUser record);

	int updateByPrimaryKey(AdminUser record);

}