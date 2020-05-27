package com.little.g.springcloud.mall.service;

import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallGoodsProductService;
import com.little.g.springcloud.mall.dto.LitemallGoodsProductDTO;
import com.little.g.springcloud.mall.mapper.GoodsProductMapper;
import com.little.g.springcloud.mall.mapper.LitemallGoodsProductMapper;
import com.little.g.springcloud.mall.model.LitemallGoodsProduct;
import com.little.g.springcloud.mall.model.LitemallGoodsProductExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallGoodsProductServiceImpl implements LitemallGoodsProductService {
    @Resource
    private LitemallGoodsProductMapper litemallGoodsProductMapper;
    @Resource
    private GoodsProductMapper goodsProductMapper;

    @Override
    public List<LitemallGoodsProductDTO> queryByGid(Integer gid) {
        LitemallGoodsProductExample example = new LitemallGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid).andDeletedEqualTo(false);
        return DTOUtil.convert2List(litemallGoodsProductMapper.selectByExample(example), LitemallGoodsProductDTO.class);
    }

    @Override
    public LitemallGoodsProductDTO findById(Integer id) {
        return DTOUtil.convert2T(litemallGoodsProductMapper.selectByPrimaryKey(id), LitemallGoodsProductDTO.class);
    }

    @Override
    public void deleteById(Integer id) {
        litemallGoodsProductMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void add(LitemallGoodsProductDTO goodsProduct) {
        goodsProduct.setAddTime(LocalDateTime.now());
        goodsProduct.setUpdateTime(LocalDateTime.now());
        litemallGoodsProductMapper.insertSelective(DTOUtil.convert2T(goodsProduct, LitemallGoodsProduct.class));
    }

    @Override
    public int count() {
        LitemallGoodsProductExample example = new LitemallGoodsProductExample();
        example.or().andDeletedEqualTo(false);
        return (int) litemallGoodsProductMapper.countByExample(example);
    }

    @Override
    public void deleteByGid(Integer gid) {
        LitemallGoodsProductExample example = new LitemallGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid);
        litemallGoodsProductMapper.logicalDeleteByExample(example);
    }

    @Override
    public int addStock(Integer id, Short num) {
        return goodsProductMapper.addStock(id, num);
    }

    @Override
    public int reduceStock(Integer id, Short num) {
        return goodsProductMapper.reduceStock(id, num);
    }

    @Override
    public void updateById(LitemallGoodsProductDTO product) {
        product.setUpdateTime(LocalDateTime.now());
        litemallGoodsProductMapper.updateByPrimaryKeySelective(DTOUtil.convert2T(product, LitemallGoodsProduct.class));
    }
}
