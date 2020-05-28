package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallKeywordService;
import com.little.g.springcloud.mall.dto.LitemallKeywordDTO;
import com.little.g.springcloud.mall.mapper.LitemallKeywordMapper;
import com.little.g.springcloud.mall.model.LitemallKeyword;
import com.little.g.springcloud.mall.model.LitemallKeywordExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallKeywordServiceImpl implements LitemallKeywordService {

    @Resource
    private LitemallKeywordMapper keywordsMapper;

    @Override
    public LitemallKeywordDTO queryDefault() {
        LitemallKeywordExample example = new LitemallKeywordExample();
        example.or().andIsDefaultEqualTo(true).andDeletedEqualTo(false);
        return DTOUtil.convert2T(keywordsMapper.selectOneByExample(example),
                LitemallKeywordDTO.class);
    }

    @Override
    public List<LitemallKeywordDTO> queryHots() {
        LitemallKeywordExample example = new LitemallKeywordExample();
        example.or().andIsHotEqualTo(true).andDeletedEqualTo(false);
        return DTOUtil.convert2List(keywordsMapper.selectByExample(example),
                LitemallKeywordDTO.class);
    }

    @Override
    public PageInfo<LitemallKeywordDTO> queryByKeyword(String keyword, Integer page,
                                                       Integer limit) {
        LitemallKeywordExample example = new LitemallKeywordExample();
        example.setDistinct(true);
        example.or().andKeywordLike("%" + keyword + "%").andDeletedEqualTo(false);
        PageHelper.startPage(page, limit);
        return DTOUtil.convert2Page(keywordsMapper.selectByExampleSelective(example,
                LitemallKeyword.Column.keyword), LitemallKeywordDTO.class);
    }

    @Override
    public PageInfo<LitemallKeywordDTO> querySelective(String keyword, String url,
                                                       Integer page, Integer limit, String sort, String order) {
        LitemallKeywordExample example = new LitemallKeywordExample();
        LitemallKeywordExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        if (!StringUtils.isEmpty(url)) {
            criteria.andUrlLike("%" + url + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return DTOUtil.convert2Page(keywordsMapper.selectByExample(example),
                LitemallKeywordDTO.class);
    }

    @Override
    public void add(LitemallKeywordDTO keywords) {
        keywords.setAddTime(LocalDateTime.now());
        keywords.setUpdateTime(LocalDateTime.now());
        keywordsMapper
                .insertSelective(DTOUtil.convert2T(keywords, LitemallKeyword.class));
    }

    @Override
    public LitemallKeywordDTO findById(Integer id) {
        return DTOUtil.convert2T(keywordsMapper.selectByPrimaryKey(id),
                LitemallKeywordDTO.class);
    }

    @Override
    public int updateById(LitemallKeywordDTO keywords) {
        keywords.setUpdateTime(LocalDateTime.now());
        return keywordsMapper.updateByPrimaryKeySelective(
                DTOUtil.convert2T(keywords, LitemallKeyword.class));
    }

    @Override
    public void deleteById(Integer id) {
        keywordsMapper.logicalDeleteByPrimaryKey(id);
    }

}
