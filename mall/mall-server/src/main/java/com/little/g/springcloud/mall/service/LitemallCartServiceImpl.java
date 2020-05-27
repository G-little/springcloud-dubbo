package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallCartService;
import com.little.g.springcloud.mall.dto.LitemallCartDTO;
import com.little.g.springcloud.mall.mapper.LitemallCartMapper;
import com.little.g.springcloud.mall.model.LitemallCart;
import com.little.g.springcloud.mall.model.LitemallCartExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service(protocol = "dubbo")
public class LitemallCartServiceImpl implements LitemallCartService {

    @Resource
    private LitemallCartMapper cartMapper;

    @Override
    public LitemallCartDTO queryExist(Integer goodsId, Integer productId,
                                      Integer userId) {
        LitemallCartExample example = new LitemallCartExample();
        example.or().andGoodsIdEqualTo(goodsId).andProductIdEqualTo(productId)
                .andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return DTOUtil.convert2T(cartMapper.selectOneByExample(example),
                LitemallCartDTO.class);
    }

    @Override
    public void add(LitemallCartDTO cart) {
        cart.setAddTime(LocalDateTime.now());
        cart.setUpdateTime(LocalDateTime.now());
        cartMapper.insertSelective(DTOUtil.convert2T(cart, LitemallCart.class));
    }

    @Override
    public int updateById(LitemallCartDTO cart) {
        cart.setUpdateTime(LocalDateTime.now());
        return cartMapper
                .updateByPrimaryKeySelective(DTOUtil.convert2T(cart, LitemallCart.class));
    }

    @Override
    public List<LitemallCartDTO> queryByUid(int userId) {
        LitemallCartExample example = new LitemallCartExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return DTOUtil.convert2List(cartMapper.selectByExample(example),
                LitemallCartDTO.class);
    }

    @Override
    public List<LitemallCartDTO> queryByUidAndChecked(Integer userId) {
        LitemallCartExample example = new LitemallCartExample();
        example.or().andUserIdEqualTo(userId).andCheckedEqualTo(true)
                .andDeletedEqualTo(false);
        return DTOUtil.convert2List(cartMapper.selectByExample(example),
                LitemallCartDTO.class);
    }

    @Override
    public int delete(List<Integer> productIdList, int userId) {
        LitemallCartExample example = new LitemallCartExample();
        example.or().andUserIdEqualTo(userId).andProductIdIn(productIdList);
        return cartMapper.logicalDeleteByExample(example);
    }

    @Override
    public LitemallCartDTO findById(Integer id) {
        return DTOUtil.convert2T(cartMapper.selectByPrimaryKey(id),
                LitemallCartDTO.class);
    }

    @Override
    public LitemallCartDTO findById(Integer userId, Integer id) {
        LitemallCartExample example = new LitemallCartExample();
        example.or().andUserIdEqualTo(userId).andIdEqualTo(id).andDeletedEqualTo(false);
        return DTOUtil.convert2T(cartMapper.selectOneByExample(example),
                LitemallCartDTO.class);
    }

    @Override
    public int updateCheck(Integer userId, List<Integer> idsList, Boolean checked) {
        LitemallCartExample example = new LitemallCartExample();
        example.or().andUserIdEqualTo(userId).andProductIdIn(idsList)
                .andDeletedEqualTo(false);
        LitemallCart cart = new LitemallCart();
        cart.setChecked(checked);
        cart.setUpdateTime(LocalDateTime.now());
        return cartMapper.updateByExampleSelective(cart, example);
    }

    @Override
    public void clearGoods(Integer userId) {
        LitemallCartExample example = new LitemallCartExample();
        example.or().andUserIdEqualTo(userId).andCheckedEqualTo(true);
        LitemallCart cart = new LitemallCart();
        cart.setDeleted(true);
        cartMapper.updateByExampleSelective(cart, example);
    }

    @Override
    public PageInfo<LitemallCartDTO> querySelective(Integer userId, Integer goodsId,
                                                    Integer page, Integer limit, String sort, String order) {
        LitemallCartExample example = new LitemallCartExample();
        LitemallCartExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(goodsId);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return DTOUtil.convert2Page(cartMapper.selectByExample(example),
                LitemallCartDTO.class);
    }

    @Override
    public void deleteById(Integer id) {
        cartMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public boolean checkExist(Integer goodsId) {
        LitemallCartExample example = new LitemallCartExample();
        example.or().andGoodsIdEqualTo(goodsId).andCheckedEqualTo(true)
                .andDeletedEqualTo(false);
        return cartMapper.countByExample(example) != 0;
    }

    @Override
    public void updateProduct(Integer id, String goodsSn, String goodsName,
                              BigDecimal price, String url) {
        LitemallCart cart = new LitemallCart();
        cart.setPrice(price);
        cart.setPicUrl(url);
        cart.setGoodsSn(goodsSn);
        cart.setGoodsName(goodsName);
        LitemallCartExample example = new LitemallCartExample();
        example.or().andProductIdEqualTo(id);
        cartMapper.updateByExampleSelective(cart, example);
    }

}
