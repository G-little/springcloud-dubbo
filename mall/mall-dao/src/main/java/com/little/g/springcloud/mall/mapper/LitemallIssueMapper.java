package com.little.g.springcloud.mall.mapper;

import com.little.g.springcloud.mall.model.LitemallIssue;
import com.little.g.springcloud.mall.model.LitemallIssueExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LitemallIssueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    long countByExample(LitemallIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    int insert(LitemallIssue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    int insertSelective(LitemallIssue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    LitemallIssue selectOneByExample(LitemallIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    LitemallIssue selectOneByExampleSelective(@Param("example") LitemallIssueExample example, @Param("selective") LitemallIssue.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    List<LitemallIssue> selectByExampleSelective(@Param("example") LitemallIssueExample example, @Param("selective") LitemallIssue.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    List<LitemallIssue> selectByExample(LitemallIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    LitemallIssue selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallIssue.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    LitemallIssue selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    LitemallIssue selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallIssue record, @Param("example") LitemallIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallIssue record, @Param("example") LitemallIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallIssue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallIssue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_issue
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}
