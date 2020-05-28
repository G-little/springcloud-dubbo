package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallLogService;
import com.little.g.springcloud.mall.dto.LitemallLogDTO;
import com.little.g.springcloud.mall.mapper.LitemallLogMapper;
import com.little.g.springcloud.mall.model.LitemallLog;
import com.little.g.springcloud.mall.model.LitemallLogExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class LitemallLogServiceImpl implements LitemallLogService {

    @Resource
    private LitemallLogMapper logMapper;

    @Override
    public void deleteById(Integer id) {
        logMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void add(LitemallLogDTO log) {
        log.setAddTime(LocalDateTime.now());
        log.setUpdateTime(LocalDateTime.now());
        logMapper.insertSelective(DTOUtil.convert2T(log, LitemallLog.class));
    }

    @Override
    public PageInfo<LitemallLogDTO> querySelective(String name, Integer page,
                                                   Integer size, String sort, String order) {
        LitemallLogExample example = new LitemallLogExample();
        LitemallLogExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andAdminLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return DTOUtil.convert2Page(logMapper.selectByExample(example),
                LitemallLogDTO.class);
    }

}
