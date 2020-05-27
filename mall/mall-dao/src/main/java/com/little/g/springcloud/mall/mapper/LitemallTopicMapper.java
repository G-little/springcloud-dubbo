package com.little.g.springcloud.mall.mapper;

import com.little.g.springcloud.mall.model.LitemallTopic;
import com.little.g.springcloud.mall.model.LitemallTopicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LitemallTopicMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    long countByExample(LitemallTopicExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallTopicExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    int insert(LitemallTopic record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    int insertSelective(LitemallTopic record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    LitemallTopic selectOneByExample(LitemallTopicExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    LitemallTopic selectOneByExampleSelective(
            @Param("example") LitemallTopicExample example,
            @Param("selective") LitemallTopic.Column... selective);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    LitemallTopic selectOneByExampleWithBLOBs(LitemallTopicExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    List<LitemallTopic> selectByExampleSelective(
            @Param("example") LitemallTopicExample example,
            @Param("selective") LitemallTopic.Column... selective);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    List<LitemallTopic> selectByExampleWithBLOBs(LitemallTopicExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    List<LitemallTopic> selectByExample(LitemallTopicExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    LitemallTopic selectByPrimaryKeySelective(@Param("id") Integer id,
                                              @Param("selective") LitemallTopic.Column... selective);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    LitemallTopic selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    LitemallTopic selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id,
                                                      @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallTopic record,
                                 @Param("example") LitemallTopicExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") LitemallTopic record,
                                 @Param("example") LitemallTopicExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallTopic record,
                        @Param("example") LitemallTopicExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallTopic record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(LitemallTopic record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallTopic record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallTopicExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the
     * database table litemall_topic
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);

}