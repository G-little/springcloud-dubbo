package com.little.g.springcloud.mall.mapper;

import com.little.g.springcloud.mall.model.LitemallGoodsSpecification;
import com.little.g.springcloud.mall.model.LitemallGoodsSpecificationExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LitemallGoodsSpecificationMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	long countByExample(LitemallGoodsSpecificationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	int deleteByExample(LitemallGoodsSpecificationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	int insert(LitemallGoodsSpecification record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	int insertSelective(LitemallGoodsSpecification record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	LitemallGoodsSpecification selectOneByExample(
			LitemallGoodsSpecificationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	LitemallGoodsSpecification selectOneByExampleSelective(
			@Param("example") LitemallGoodsSpecificationExample example,
			@Param("selective") LitemallGoodsSpecification.Column... selective);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	List<LitemallGoodsSpecification> selectByExampleSelective(
			@Param("example") LitemallGoodsSpecificationExample example,
			@Param("selective") LitemallGoodsSpecification.Column... selective);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	List<LitemallGoodsSpecification> selectByExample(
			LitemallGoodsSpecificationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	LitemallGoodsSpecification selectByPrimaryKeySelective(@Param("id") Integer id,
			@Param("selective") LitemallGoodsSpecification.Column... selective);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	LitemallGoodsSpecification selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	LitemallGoodsSpecification selectByPrimaryKeyWithLogicalDelete(
			@Param("id") Integer id,
			@Param("andLogicalDeleted") boolean andLogicalDeleted);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") LitemallGoodsSpecification record,
			@Param("example") LitemallGoodsSpecificationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") LitemallGoodsSpecification record,
			@Param("example") LitemallGoodsSpecificationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(LitemallGoodsSpecification record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(LitemallGoodsSpecification record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	int logicalDeleteByExample(
			@Param("example") LitemallGoodsSpecificationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_goods_specification
	 *
	 * @mbg.generated
	 */
	int logicalDeleteByPrimaryKey(Integer id);

}
