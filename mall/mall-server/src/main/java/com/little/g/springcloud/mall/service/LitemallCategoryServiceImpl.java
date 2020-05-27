package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallCategoryService;
import com.little.g.springcloud.mall.dto.LitemallCategoryDTO;
import com.little.g.springcloud.mall.mapper.LitemallCategoryMapper;
import com.little.g.springcloud.mall.model.LitemallCategory;
import com.little.g.springcloud.mall.model.LitemallCategoryExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallCategoryServiceImpl implements LitemallCategoryService {
    @Resource
    private LitemallCategoryMapper categoryMapper;
    private LitemallCategory.Column[] CHANNEL = {LitemallCategory.Column.id, LitemallCategory.Column.name, LitemallCategory.Column.iconUrl};

    @Override
    public List<LitemallCategoryDTO> queryL1WithoutRecommend(int offset, int limit) {
        LitemallCategoryExample example = new LitemallCategoryExample();
        example.or().andLevelEqualTo("L1").andNameNotEqualTo("推荐").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return DTOUtil.convert2List(categoryMapper.selectByExample(example), LitemallCategoryDTO.class);
    }

    @Override
    public List<LitemallCategoryDTO> queryL1(int offset, int limit) {
        LitemallCategoryExample example = new LitemallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return DTOUtil.convert2List(categoryMapper.selectByExample(example), LitemallCategoryDTO.class);
    }

    @Override
    public List<LitemallCategoryDTO> queryL1() {
        LitemallCategoryExample example = new LitemallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return DTOUtil.convert2List(categoryMapper.selectByExample(example), LitemallCategoryDTO.class);
    }

    @Override
    public List<LitemallCategoryDTO> queryByPid(Integer pid) {
        LitemallCategoryExample example = new LitemallCategoryExample();
        example.or().andPidEqualTo(pid).andDeletedEqualTo(false);
        return DTOUtil.convert2List(categoryMapper.selectByExample(example), LitemallCategoryDTO.class);
    }

    @Override
    public List<LitemallCategoryDTO> queryL2ByIds(List<Integer> ids) {
        LitemallCategoryExample example = new LitemallCategoryExample();
        example.or().andIdIn(ids).andLevelEqualTo("L2").andDeletedEqualTo(false);
        return DTOUtil.convert2List(categoryMapper.selectByExample(example), LitemallCategoryDTO.class);
    }

    @Override
    public LitemallCategoryDTO findById(Integer id) {
        return DTOUtil.convert2T(categoryMapper.selectByPrimaryKey(id), LitemallCategoryDTO.class);
    }

    @Override
    public List<LitemallCategoryDTO> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        LitemallCategoryExample example = new LitemallCategoryExample();
        LitemallCategoryExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andIdEqualTo(Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return DTOUtil.convert2List(categoryMapper.selectByExample(example), LitemallCategoryDTO.class);
    }

    @Override
    public int updateById(LitemallCategoryDTO category) {
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.updateByPrimaryKeySelective(DTOUtil.convert2T(category, LitemallCategory.class));
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void add(LitemallCategoryDTO category) {
        category.setAddTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insertSelective(DTOUtil.convert2T(category, LitemallCategory.class));
    }

    @Override
    public List<LitemallCategoryDTO> queryChannel() {
        LitemallCategoryExample example = new LitemallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return DTOUtil.convert2List(categoryMapper.selectByExampleSelective(example, CHANNEL), LitemallCategoryDTO.class);
    }
}
