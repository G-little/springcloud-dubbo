package com.little.g.springcloud.mall.mapper;

import com.little.g.springcloud.mall.model.LitemallGoods;
import com.little.g.springcloud.mall.model.LitemallGoodsExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LitemallGoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    long countByExample(LitemallGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    int insert(LitemallGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    int insertSelective(LitemallGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    LitemallGoods selectOneByExample(LitemallGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    LitemallGoods selectOneByExampleSelective(@Param("example") LitemallGoodsExample example, @Param("selective") LitemallGoods.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    LitemallGoods selectOneByExampleWithBLOBs(LitemallGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    List<LitemallGoods> selectByExampleSelective(@Param("example") LitemallGoodsExample example, @Param("selective") LitemallGoods.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    List<LitemallGoods> selectByExampleWithBLOBs(LitemallGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    List<LitemallGoods> selectByExample(LitemallGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    LitemallGoods selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallGoods.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    LitemallGoods selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    LitemallGoods selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallGoods record, @Param("example") LitemallGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") LitemallGoods record, @Param("example") LitemallGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallGoods record, @Param("example") LitemallGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(LitemallGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_goods
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}
