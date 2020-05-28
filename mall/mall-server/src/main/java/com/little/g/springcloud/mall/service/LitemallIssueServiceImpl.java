package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallIssueService;
import com.little.g.springcloud.mall.dto.LitemallIssueDTO;
import com.little.g.springcloud.mall.mapper.LitemallIssueMapper;
import com.little.g.springcloud.mall.model.LitemallIssue;
import com.little.g.springcloud.mall.model.LitemallIssueExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service(protocol = "dubbo")
public class LitemallIssueServiceImpl implements LitemallIssueService {

    @Resource
    private LitemallIssueMapper issueMapper;

    @Override
    public void deleteById(Integer id) {
        issueMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void add(LitemallIssueDTO issue) {
        issue.setAddTime(LocalDateTime.now());
        issue.setUpdateTime(LocalDateTime.now());
        issueMapper.insertSelective(DTOUtil.convert2T(issue, LitemallIssue.class));
    }

    @Override
    public PageInfo<LitemallIssueDTO> querySelective(String question, Integer page,
                                                     Integer limit, String sort, String order) {
        LitemallIssueExample example = new LitemallIssueExample();
        LitemallIssueExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(question)) {
            criteria.andQuestionLike("%" + question + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return DTOUtil.convert2Page(issueMapper.selectByExample(example),
                LitemallIssueDTO.class);
    }

    @Override
    public int updateById(LitemallIssueDTO issue) {
        issue.setUpdateTime(LocalDateTime.now());
        return issueMapper.updateByPrimaryKeySelective(
                DTOUtil.convert2T(issue, LitemallIssue.class));
    }

    @Override
    public LitemallIssueDTO findById(Integer id) {
        return DTOUtil.convert2T(issueMapper.selectByPrimaryKey(id),
                LitemallIssueDTO.class);
    }

}
