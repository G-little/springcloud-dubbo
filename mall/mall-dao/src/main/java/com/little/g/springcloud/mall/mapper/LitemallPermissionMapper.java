package com.little.g.springcloud.mall.mapper;

import com.little.g.springcloud.mall.model.LitemallPermission;
import com.little.g.springcloud.mall.model.LitemallPermissionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LitemallPermissionMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	long countByExample(LitemallPermissionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	int deleteByExample(LitemallPermissionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	int insert(LitemallPermission record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	int insertSelective(LitemallPermission record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	LitemallPermission selectOneByExample(LitemallPermissionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	LitemallPermission selectOneByExampleSelective(
			@Param("example") LitemallPermissionExample example,
			@Param("selective") LitemallPermission.Column... selective);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	List<LitemallPermission> selectByExampleSelective(
			@Param("example") LitemallPermissionExample example,
			@Param("selective") LitemallPermission.Column... selective);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	List<LitemallPermission> selectByExample(LitemallPermissionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	LitemallPermission selectByPrimaryKeySelective(@Param("id") Integer id,
			@Param("selective") LitemallPermission.Column... selective);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	LitemallPermission selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	LitemallPermission selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id,
			@Param("andLogicalDeleted") boolean andLogicalDeleted);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") LitemallPermission record,
			@Param("example") LitemallPermissionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") LitemallPermission record,
			@Param("example") LitemallPermissionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(LitemallPermission record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(LitemallPermission record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	int logicalDeleteByExample(@Param("example") LitemallPermissionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table litemall_permission
	 *
	 * @mbg.generated
	 */
	int logicalDeleteByPrimaryKey(Integer id);

}
