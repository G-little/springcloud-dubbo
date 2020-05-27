package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.little.g.springcloud.mall.api.LitemallAdService;
import com.little.g.springcloud.mall.dto.LitemallAdDTO;
import com.little.g.springcloud.mall.mapper.LitemallAdMapper;
import com.little.g.springcloud.mall.model.LitemallAd;
import com.little.g.springcloud.mall.model.LitemallAdExample;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LitemallAdServiceImpl implements LitemallAdService {

    @Resource
    private LitemallAdMapper adMapper;

    @Override
    public List<LitemallAdDTO> queryIndex() {
        LitemallAdExample example = new LitemallAdExample();
        example.or().andPositionEqualTo((byte) 1).andDeletedEqualTo(false)
                .andEnabledEqualTo(true);
        return selectListByExample(example);
    }

    private List<LitemallAdDTO> selectListByExample(LitemallAdExample example) {
        return adMapper.selectByExample(example).stream().map(a -> {
            LitemallAdDTO dto = new LitemallAdDTO();
            BeanUtils.copyProperties(a, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<LitemallAdDTO> querySelective(String name, String content, Integer page,
                                              Integer limit, String sort, String order) {
        LitemallAdExample example = new LitemallAdExample();
        LitemallAdExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(content)) {
            criteria.andContentLike("%" + content + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return selectListByExample(example);
    }

    @Override
    public int updateById(LitemallAdDTO ad) {
        ad.setUpdateTime(LocalDateTime.now());
        return adMapper.updateByPrimaryKeySelective(convertDTO2Pojo(ad));
    }

    LitemallAd convertDTO2Pojo(LitemallAdDTO ad) {
        if (ad == null) {
            return null;
        }
        LitemallAd l = new LitemallAd();
        BeanUtils.copyProperties(ad, l);
        return l;
    }

    LitemallAdDTO convertPojo2DTO(LitemallAd ad) {
        if (ad == null) {
            return null;
        }
        LitemallAdDTO l = new LitemallAdDTO();
        BeanUtils.copyProperties(ad, l);
        return l;
    }

    @Override
    public void deleteById(Integer id) {
        adMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void add(LitemallAdDTO ad) {
        ad.setAddTime(LocalDateTime.now());
        ad.setUpdateTime(LocalDateTime.now());
        adMapper.insertSelective(convertDTO2Pojo(ad));
    }

    @Override
    public LitemallAdDTO findById(Integer id) {
        LitemallAd add = adMapper.selectByPrimaryKey(id);
        if (add == null) {
            return null;
        }
        LitemallAdDTO dto = new LitemallAdDTO();
        BeanUtils.copyProperties(add, dto);
        return dto;
    }

}
