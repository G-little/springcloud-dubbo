package com.little.g.springcloud.sample.mapper;

import com.little.g.springcloud.sample.model.AiAssistant;
import com.little.g.springcloud.sample.model.AiAssistantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AiAssistantMapper {

	long countByExample(AiAssistantExample example);

	int deleteByExample(AiAssistantExample example);

	int deleteByPrimaryKey(Long id);

	int insert(AiAssistant record);

	int insertSelective(AiAssistant record);

	List<AiAssistant> selectByExampleWithRowbounds(AiAssistantExample example,
			RowBounds rowBounds);

	List<AiAssistant> selectByExample(AiAssistantExample example);

	AiAssistant selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") AiAssistant record,
			@Param("example") AiAssistantExample example);

	int updateByExample(@Param("record") AiAssistant record,
			@Param("example") AiAssistantExample example);

	int updateByPrimaryKeySelective(AiAssistant record);

	int updateByPrimaryKey(AiAssistant record);

}