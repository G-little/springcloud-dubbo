package com.little.g.springcloud.admin.mapper;

import com.little.g.springcloud.admin.model.Resources;
import com.little.g.springcloud.admin.model.ResourcesExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ResourcesMapper {

	long countByExample(ResourcesExample example);

	int deleteByExample(ResourcesExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Resources record);

	int insertSelective(Resources record);

	List<Resources> selectByExampleWithRowbounds(ResourcesExample example,
			RowBounds rowBounds);

	List<Resources> selectByExample(ResourcesExample example);

	Resources selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Resources record,
			@Param("example") ResourcesExample example);

	int updateByExample(@Param("record") Resources record,
			@Param("example") ResourcesExample example);

	int updateByPrimaryKeySelective(Resources record);

	int updateByPrimaryKey(Resources record);

}