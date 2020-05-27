package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallStorageService;
import com.little.g.springcloud.mall.dto.LitemallStorageDTO;
import com.little.g.springcloud.mall.mapper.LitemallStorageMapper;
import com.little.g.springcloud.mall.model.LitemallStorage;
import com.little.g.springcloud.mall.model.LitemallStorageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallStorageServiceImpl implements LitemallStorageService {

    @Autowired
    private LitemallStorageMapper storageMapper;

    @Override
    public void deleteByKey(String key) {
        LitemallStorageExample example = new LitemallStorageExample();
        example.or().andKeyEqualTo(key);
        storageMapper.logicalDeleteByExample(example);
    }

    @Override
    public void add(LitemallStorageDTO storageInfo) {
        storageInfo.setAddTime(LocalDateTime.now());
        storageInfo.setUpdateTime(LocalDateTime.now());
        storageMapper
                .insertSelective(DTOUtil.convert2T(storageInfo, LitemallStorage.class));
    }

    @Override
    public LitemallStorageDTO findByKey(String key) {
        LitemallStorageExample example = new LitemallStorageExample();
        example.or().andKeyEqualTo(key).andDeletedEqualTo(false);
        return DTOUtil.convert2T(storageMapper.selectOneByExample(example),
                LitemallStorageDTO.class);
    }

    @Override
    public int update(LitemallStorageDTO storageInfo) {
        storageInfo.setUpdateTime(LocalDateTime.now());
        return storageMapper.updateByPrimaryKeySelective(
                DTOUtil.convert2T(storageInfo, LitemallStorage.class));
    }

    @Override
    public LitemallStorageDTO findById(Integer id) {
        return DTOUtil.convert2T(storageMapper.selectByPrimaryKey(id),
                LitemallStorageDTO.class);
    }

    @Override
    public List<LitemallStorageDTO> querySelective(String key, String name, Integer page,
                                                   Integer limit, String sort, String order) {
        LitemallStorageExample example = new LitemallStorageExample();
        LitemallStorageExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andKeyEqualTo(key);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return DTOUtil.convert2List(storageMapper.selectByExample(example),
                LitemallStorageDTO.class);
    }

}
