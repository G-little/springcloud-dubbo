package com.little.g.springcloud.mall.service;

import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallOrderGoodsService;
import com.little.g.springcloud.mall.dto.LitemallOrderGoodsDTO;
import com.little.g.springcloud.mall.mapper.LitemallOrderGoodsMapper;
import com.little.g.springcloud.mall.model.LitemallOrderGoods;
import com.little.g.springcloud.mall.model.LitemallOrderGoodsExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallOrderGoodsServiceImpl implements LitemallOrderGoodsService {

    @Resource
    private LitemallOrderGoodsMapper orderGoodsMapper;

    @Override
    public int add(LitemallOrderGoodsDTO orderGoods) {
        orderGoods.setAddTime(LocalDateTime.now());
        orderGoods.setUpdateTime(LocalDateTime.now());
        return orderGoodsMapper
                .insertSelective(DTOUtil.convert2T(orderGoods, LitemallOrderGoods.class));
    }

    @Override
    public List<LitemallOrderGoodsDTO> queryByOid(Integer orderId) {
        LitemallOrderGoodsExample example = new LitemallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        return DTOUtil.convert2List(orderGoodsMapper.selectByExample(example),
                LitemallOrderGoodsDTO.class);
    }

    @Override
    public List<LitemallOrderGoodsDTO> findByOidAndGid(Integer orderId, Integer goodsId) {
        LitemallOrderGoodsExample example = new LitemallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodsId)
                .andDeletedEqualTo(false);
        return DTOUtil.convert2List(orderGoodsMapper.selectByExample(example),
                LitemallOrderGoodsDTO.class);
    }

    @Override
    public LitemallOrderGoodsDTO findById(Integer id) {
        return DTOUtil.convert2T(orderGoodsMapper.selectByPrimaryKey(id),
                LitemallOrderGoodsDTO.class);
    }

    @Override
    public void updateById(LitemallOrderGoodsDTO orderGoods) {
        orderGoods.setUpdateTime(LocalDateTime.now());
        orderGoodsMapper.updateByPrimaryKeySelective(
                DTOUtil.convert2T(orderGoods, LitemallOrderGoods.class));
    }

    @Override
    public Short getComments(Integer orderId) {
        LitemallOrderGoodsExample example = new LitemallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        long count = orderGoodsMapper.countByExample(example);
        return (short) count;
    }

    @Override
    public boolean checkExist(Integer goodsId) {
        LitemallOrderGoodsExample example = new LitemallOrderGoodsExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return orderGoodsMapper.countByExample(example) != 0;
    }

    @Override
    public void deleteByOrderId(Integer orderId) {
        LitemallOrderGoodsExample example = new LitemallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        orderGoodsMapper.logicalDeleteByExample(example);
    }

}
