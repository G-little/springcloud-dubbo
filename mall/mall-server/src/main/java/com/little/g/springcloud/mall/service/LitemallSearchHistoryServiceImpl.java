package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallSearchHistoryService;
import com.little.g.springcloud.mall.dto.LitemallSearchHistoryDTO;
import com.little.g.springcloud.mall.mapper.LitemallSearchHistoryMapper;
import com.little.g.springcloud.mall.model.LitemallSearchHistory;
import com.little.g.springcloud.mall.model.LitemallSearchHistoryExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service(protocol = "dubbo")
public class LitemallSearchHistoryServiceImpl implements LitemallSearchHistoryService {

    @Resource
    private LitemallSearchHistoryMapper searchHistoryMapper;

    @Override
    public void save(LitemallSearchHistoryDTO searchHistory) {
        searchHistory.setAddTime(LocalDateTime.now());
        searchHistory.setUpdateTime(LocalDateTime.now());
        searchHistoryMapper.insertSelective(
                DTOUtil.convert2T(searchHistory, LitemallSearchHistory.class));
    }

    @Override
    public List<LitemallSearchHistoryDTO> queryByUid(int uid) {
        LitemallSearchHistoryExample example = new LitemallSearchHistoryExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        example.setDistinct(true);
        return DTOUtil.convert2List(
                searchHistoryMapper.selectByExampleSelective(example,
                        LitemallSearchHistory.Column.keyword),
                LitemallSearchHistoryDTO.class);
    }

    @Override
    public void deleteByUid(int uid) {
        LitemallSearchHistoryExample example = new LitemallSearchHistoryExample();
        example.or().andUserIdEqualTo(uid);
        searchHistoryMapper.logicalDeleteByExample(example);
    }

    @Override
    public PageInfo<LitemallSearchHistoryDTO> querySelective(String userId,
                                                             String keyword, Integer page, Integer size, String sort, String order) {
        LitemallSearchHistoryExample example = new LitemallSearchHistoryExample();
        LitemallSearchHistoryExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return DTOUtil.convert2Page(searchHistoryMapper.selectByExample(example),
                LitemallSearchHistoryDTO.class);
    }

}
