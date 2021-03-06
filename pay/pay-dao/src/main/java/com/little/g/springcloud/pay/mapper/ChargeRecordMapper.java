package com.little.g.springcloud.pay.mapper;

import com.little.g.springcloud.pay.model.ChargeRecord;
import com.little.g.springcloud.pay.model.ChargeRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ChargeRecordMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	long countByExample(ChargeRecordExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	int deleteByExample(ChargeRecordExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(String tranNum);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	int insert(ChargeRecord record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	int insertSelective(ChargeRecord record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	ChargeRecord selectOneByExample(ChargeRecordExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	ChargeRecord selectOneByExampleSelective(
			@Param("example") ChargeRecordExample example,
			@Param("selective") ChargeRecord.Column... selective);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	List<ChargeRecord> selectByExampleSelective(
			@Param("example") ChargeRecordExample example,
			@Param("selective") ChargeRecord.Column... selective);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	List<ChargeRecord> selectByExample(ChargeRecordExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	ChargeRecord selectByPrimaryKeySelective(@Param("tranNum") String tranNum,
			@Param("selective") ChargeRecord.Column... selective);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	ChargeRecord selectByPrimaryKey(String tranNum);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") ChargeRecord record,
			@Param("example") ChargeRecordExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") ChargeRecord record,
			@Param("example") ChargeRecordExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(ChargeRecord record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table charge_record
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(ChargeRecord record);

}
