package com.little.g.springcloud.mall.mapper;

import com.little.g.springcloud.mall.model.LitemallNoticeAdmin;
import com.little.g.springcloud.mall.model.LitemallNoticeAdminExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LitemallNoticeAdminMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    long countByExample(LitemallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    int insert(LitemallNoticeAdmin record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    int insertSelective(LitemallNoticeAdmin record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    LitemallNoticeAdmin selectOneByExample(LitemallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    LitemallNoticeAdmin selectOneByExampleSelective(
            @Param("example") LitemallNoticeAdminExample example,
            @Param("selective") LitemallNoticeAdmin.Column... selective);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    List<LitemallNoticeAdmin> selectByExampleSelective(
            @Param("example") LitemallNoticeAdminExample example,
            @Param("selective") LitemallNoticeAdmin.Column... selective);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    List<LitemallNoticeAdmin> selectByExample(LitemallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    LitemallNoticeAdmin selectByPrimaryKeySelective(@Param("id") Integer id,
                                                    @Param("selective") LitemallNoticeAdmin.Column... selective);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    LitemallNoticeAdmin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    LitemallNoticeAdmin selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id,
                                                            @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallNoticeAdmin record,
                                 @Param("example") LitemallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallNoticeAdmin record,
                        @Param("example") LitemallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallNoticeAdmin record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallNoticeAdmin record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_notice_admin
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);

}
