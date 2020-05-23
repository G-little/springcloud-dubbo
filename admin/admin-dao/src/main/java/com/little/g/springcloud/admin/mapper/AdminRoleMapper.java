package com.little.g.springcloud.admin.mapper;

import com.little.g.springcloud.admin.model.AdminRole;
import com.little.g.springcloud.admin.model.AdminRoleExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface AdminRoleMapper {

	long countByExample(AdminRoleExample example);

	int deleteByExample(AdminRoleExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(AdminRole record);

	int insertSelective(AdminRole record);

	List<AdminRole> selectByExampleWithRowbounds(AdminRoleExample example,
			RowBounds rowBounds);

	List<AdminRole> selectByExample(AdminRoleExample example);

	AdminRole selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") AdminRole record,
			@Param("example") AdminRoleExample example);

	int updateByExample(@Param("record") AdminRole record,
			@Param("example") AdminRoleExample example);

	int updateByPrimaryKeySelective(AdminRole record);

	int updateByPrimaryKey(AdminRole record);

}