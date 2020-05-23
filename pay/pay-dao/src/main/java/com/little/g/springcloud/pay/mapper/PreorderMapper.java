package com.little.g.springcloud.pay.mapper;

import com.little.g.springcloud.pay.model.Preorder;
import com.little.g.springcloud.pay.model.PreorderExample;
import com.little.g.springcloud.pay.model.PreorderKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface PreorderMapper {

	long countByExample(PreorderExample example);

	int deleteByExample(PreorderExample example);

	int deleteByPrimaryKey(PreorderKey key);

	int insert(Preorder record);

	int insertSelective(Preorder record);

	List<Preorder> selectByExampleWithRowbounds(PreorderExample example,
			RowBounds rowBounds);

	List<Preorder> selectByExample(PreorderExample example);

	Preorder selectByPrimaryKey(PreorderKey key);

	int updateByExampleSelective(@Param("record") Preorder record,
			@Param("example") PreorderExample example);

	int updateByExample(@Param("record") Preorder record,
			@Param("example") PreorderExample example);

	int updateByPrimaryKeySelective(Preorder record);

	int updateByPrimaryKey(Preorder record);

}