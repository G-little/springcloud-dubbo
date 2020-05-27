package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallRegionService;
import com.little.g.springcloud.mall.dto.LitemallRegionDTO;
import com.little.g.springcloud.mall.mapper.LitemallRegionMapper;
import com.little.g.springcloud.mall.model.LitemallRegionExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service(protocol = "dubbo")
public class LitemallRegionServiceImpl implements LitemallRegionService {

    @Resource
    private LitemallRegionMapper regionMapper;

    @Override
    public List<LitemallRegionDTO> getAll() {
        LitemallRegionExample example = new LitemallRegionExample();
        byte b = 4;
        example.or().andTypeNotEqualTo(b);
        return DTOUtil.convert2List(regionMapper.selectByExample(example),
                LitemallRegionDTO.class);
    }

    @Override
    public List<LitemallRegionDTO> queryByPid(Integer parentId) {
        LitemallRegionExample example = new LitemallRegionExample();
        example.or().andPidEqualTo(parentId);
        return DTOUtil.convert2List(regionMapper.selectByExample(example),
                LitemallRegionDTO.class);
    }

    @Override
    public LitemallRegionDTO findById(Integer id) {
        return DTOUtil.convert2T(regionMapper.selectByPrimaryKey(id),
                LitemallRegionDTO.class);
    }

    @Override
    public List<LitemallRegionDTO> querySelective(String name, Integer code, Integer page,
                                                  Integer size, String sort, String order) {
        LitemallRegionExample example = new LitemallRegionExample();
        LitemallRegionExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(code)) {
            criteria.andCodeEqualTo(code);
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return DTOUtil.convert2List(regionMapper.selectByExample(example),
                LitemallRegionDTO.class);
    }

}
