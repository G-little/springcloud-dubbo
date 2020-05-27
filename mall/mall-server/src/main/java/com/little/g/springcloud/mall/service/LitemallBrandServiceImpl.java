package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallBrandService;
import com.little.g.springcloud.mall.dto.LitemallBrandDTO;
import com.little.g.springcloud.mall.mapper.LitemallBrandMapper;
import com.little.g.springcloud.mall.model.LitemallBrand;
import com.little.g.springcloud.mall.model.LitemallBrand.Column;
import com.little.g.springcloud.mall.model.LitemallBrandExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallBrandServiceImpl implements LitemallBrandService {
    @Resource
    private LitemallBrandMapper brandMapper;
    private Column[] columns = new Column[]{Column.id, Column.name, Column.desc, Column.picUrl, Column.floorPrice};

    @Override
    public List<LitemallBrandDTO> query(Integer page, Integer limit, String sort, String order) {
        LitemallBrandExample example = new LitemallBrandExample();
        example.or().andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return DTOUtil.convert2List(brandMapper.selectByExampleSelective(example, columns), LitemallBrandDTO.class);
    }

    @Override
    public List<LitemallBrandDTO> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    @Override
    public LitemallBrandDTO findById(Integer id) {
        return DTOUtil.convert2T(brandMapper.selectByPrimaryKey(id), LitemallBrandDTO.class);
    }

    @Override
    public List<LitemallBrandDTO> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        LitemallBrandExample example = new LitemallBrandExample();
        LitemallBrandExample.Criteria criteria = example.createCriteria();

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
        return DTOUtil.convert2List(brandMapper.selectByExample(example), LitemallBrandDTO.class);
    }

    @Override
    public int updateById(LitemallBrandDTO brand) {
        brand.setUpdateTime(LocalDateTime.now());
        return brandMapper.updateByPrimaryKeySelective(DTOUtil.convert2T(brand, LitemallBrand.class));
    }

    @Override
    public void deleteById(Integer id) {
        brandMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void add(LitemallBrandDTO brand) {
        brand.setAddTime(LocalDateTime.now());
        brand.setUpdateTime(LocalDateTime.now());
        brandMapper.insertSelective(DTOUtil.convert2T(brand, LitemallBrand.class));
    }

    @Override
    public List<LitemallBrandDTO> all() {
        LitemallBrandExample example = new LitemallBrandExample();
        example.or().andDeletedEqualTo(false);
        return DTOUtil.convert2List(brandMapper.selectByExample(example), LitemallBrandDTO.class);
    }
}
