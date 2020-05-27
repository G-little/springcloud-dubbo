package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallAftersaleService;
import com.little.g.springcloud.mall.dto.LitemallAftersaleDTO;
import com.little.g.springcloud.mall.mapper.LitemallAftersaleMapper;
import com.little.g.springcloud.mall.model.LitemallAftersale;
import com.little.g.springcloud.mall.model.LitemallAftersaleExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class LitemallAftersaleServiceImpl implements LitemallAftersaleService {

    @Resource
    private LitemallAftersaleMapper aftersaleMapper;

    @Override
    public LitemallAftersaleDTO findById(Integer userId, Integer id) {
        LitemallAftersaleExample example = new LitemallAftersaleExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return DTOUtil.convert2T(aftersaleMapper.selectOneByExample(example),
                LitemallAftersaleDTO.class);
    }

    @Override
    public PageInfo<LitemallAftersaleDTO> queryList(Integer userId, Short status,
                                                    Integer page, Integer limit, String sort, String order) {
        LitemallAftersaleExample example = new LitemallAftersaleExample();
        LitemallAftersaleExample.Criteria criteria = example.or();
        criteria.andUserIdEqualTo(userId);
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        criteria.andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        } else {
            example.setOrderByClause(LitemallAftersaleDTO.Column.addTime.desc());
        }

        PageHelper.startPage(page, limit);
        return DTOUtil.convert2Page(aftersaleMapper.selectByExample(example),
                LitemallAftersaleDTO.class);
    }

    @Override
    public List<LitemallAftersaleDTO> querySelective(Integer orderId, String aftersaleSn,
                                                     Short status, Integer page, Integer limit, String sort, String order) {
        LitemallAftersaleExample example = new LitemallAftersaleExample();
        LitemallAftersaleExample.Criteria criteria = example.or();
        if (orderId != null) {
            criteria.andOrderIdEqualTo(orderId);
        }
        if (!StringUtils.isEmpty(aftersaleSn)) {
            criteria.andAftersaleSnEqualTo(aftersaleSn);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        criteria.andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        } else {
            example.setOrderByClause(LitemallAftersaleDTO.Column.addTime.desc());
        }

        PageHelper.startPage(page, limit);
        return DTOUtil.convert2List(aftersaleMapper.selectByExample(example),
                LitemallAftersaleDTO.class);
    }

    private String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    @Override
    public int countByAftersaleSn(Integer userId, String aftersaleSn) {
        LitemallAftersaleExample example = new LitemallAftersaleExample();
        example.or().andUserIdEqualTo(userId).andAftersaleSnEqualTo(aftersaleSn)
                .andDeletedEqualTo(false);
        return (int) aftersaleMapper.countByExample(example);
    }

    // TODO 这里应该产生一个唯一的编号，但是实际上这里仍然存在两个售后编号相同的可能性
    @Override
    public String generateAftersaleSn(Integer userId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = df.format(LocalDate.now());
        String aftersaleSn = now + getRandomNum(6);
        while (countByAftersaleSn(userId, aftersaleSn) != 0) {
            aftersaleSn = now + getRandomNum(6);
        }
        return aftersaleSn;
    }

    @Override
    public void add(LitemallAftersaleDTO aftersale) {
        aftersale.setAddTime(LocalDateTime.now());
        aftersale.setUpdateTime(LocalDateTime.now());
        aftersaleMapper
                .insertSelective(DTOUtil.convert2T(aftersale, LitemallAftersale.class));
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        LitemallAftersaleExample example = new LitemallAftersaleExample();
        example.or().andIdIn(ids).andDeletedEqualTo(false);
        LitemallAftersale aftersale = new LitemallAftersale();
        aftersale.setUpdateTime(LocalDateTime.now());
        aftersale.setDeleted(true);
        aftersaleMapper.updateByExampleSelective(aftersale, example);
    }

    @Override
    public void deleteById(Integer id) {
        aftersaleMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void deleteByOrderId(Integer userId, Integer orderId) {
        LitemallAftersaleExample example = new LitemallAftersaleExample();
        example.or().andOrderIdEqualTo(orderId).andUserIdEqualTo(userId)
                .andDeletedEqualTo(false);
        LitemallAftersale aftersale = new LitemallAftersale();
        aftersale.setUpdateTime(LocalDateTime.now());
        aftersale.setDeleted(true);
        aftersaleMapper.updateByExampleSelective(aftersale, example);
    }

    @Override
    public void updateById(LitemallAftersaleDTO aftersale) {
        aftersale.setUpdateTime(LocalDateTime.now());
        aftersaleMapper.updateByPrimaryKeySelective(
                DTOUtil.convert2T(aftersale, LitemallAftersale.class));
    }

    @Override
    public LitemallAftersaleDTO findByOrderId(Integer userId, Integer orderId) {
        LitemallAftersaleExample example = new LitemallAftersaleExample();
        example.or().andOrderIdEqualTo(orderId).andUserIdEqualTo(userId)
                .andDeletedEqualTo(false);
        return DTOUtil.convert2T(aftersaleMapper.selectOneByExample(example),
                LitemallAftersaleDTO.class);
    }

}
