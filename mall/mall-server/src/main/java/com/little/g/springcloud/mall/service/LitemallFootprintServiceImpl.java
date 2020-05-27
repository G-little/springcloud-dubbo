package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallFootprintService;
import com.little.g.springcloud.mall.dto.LitemallFootprintDTO;
import com.little.g.springcloud.mall.mapper.LitemallFootprintMapper;
import com.little.g.springcloud.mall.model.LitemallFootprint;
import com.little.g.springcloud.mall.model.LitemallFootprintExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallFootprintServiceImpl implements LitemallFootprintService {
    @Resource
    private LitemallFootprintMapper footprintMapper;

    @Override
    public List<LitemallFootprintDTO> queryByAddTime(Integer userId, Integer page, Integer size) {
        LitemallFootprintExample example = new LitemallFootprintExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        example.setOrderByClause(LitemallFootprintDTO.Column.addTime.desc());
        PageHelper.startPage(page, size);
        return DTOUtil.convert2List(footprintMapper.selectByExample(example), LitemallFootprintDTO.class);
    }

    @Override
    public LitemallFootprintDTO findById(Integer id) {
        return DTOUtil.convert2T(footprintMapper.selectByPrimaryKey(id), LitemallFootprintDTO.class);
    }

    @Override
    public LitemallFootprintDTO findById(Integer userId, Integer id) {
        LitemallFootprintExample example = new LitemallFootprintExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return DTOUtil.convert2T(footprintMapper.selectOneByExample(example), LitemallFootprintDTO.class);
    }

    @Override
    public void deleteById(Integer id) {
        footprintMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void add(LitemallFootprintDTO footprint) {
        footprint.setAddTime(LocalDateTime.now());
        footprint.setUpdateTime(LocalDateTime.now());
        footprintMapper.insertSelective(DTOUtil.convert2T(footprint, LitemallFootprint.class));
    }

    @Override
    public List<LitemallFootprintDTO> querySelective(String userId, String goodsId, Integer page, Integer size, String sort, String order) {
        LitemallFootprintExample example = new LitemallFootprintExample();
        LitemallFootprintExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.valueOf(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return DTOUtil.convert2List(footprintMapper.selectByExample(example), LitemallFootprintDTO.class);
    }
}
