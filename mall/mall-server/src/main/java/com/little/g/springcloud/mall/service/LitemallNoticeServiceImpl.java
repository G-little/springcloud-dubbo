package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallNoticeService;
import com.little.g.springcloud.mall.dto.LitemallNoticeDTO;
import com.little.g.springcloud.mall.mapper.LitemallNoticeMapper;
import com.little.g.springcloud.mall.model.LitemallNotice;
import com.little.g.springcloud.mall.model.LitemallNoticeExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallNoticeServiceImpl implements LitemallNoticeService {

    @Resource
    private LitemallNoticeMapper noticeMapper;

    @Override
    public PageInfo<LitemallNoticeDTO> querySelective(String title, String content,
													  Integer page, Integer limit, String sort, String order) {
        LitemallNoticeExample example = new LitemallNoticeExample();
        LitemallNoticeExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (!StringUtils.isEmpty(content)) {
            criteria.andContentLike("%" + content + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return DTOUtil.convert2Page(noticeMapper.selectByExample(example),
                LitemallNoticeDTO.class);
    }

    @Override
    public int updateById(LitemallNoticeDTO notice) {
        notice.setUpdateTime(LocalDateTime.now());
        return noticeMapper.updateByPrimaryKeySelective(
                DTOUtil.convert2T(notice, LitemallNotice.class));
    }

    @Override
    public void deleteById(Integer id) {
        noticeMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void add(LitemallNoticeDTO notice) {
        notice.setAddTime(LocalDateTime.now());
        notice.setUpdateTime(LocalDateTime.now());
        noticeMapper.insertSelective(DTOUtil.convert2T(notice, LitemallNotice.class));
    }

    @Override
    public LitemallNoticeDTO findById(Integer id) {
        return DTOUtil.convert2T(noticeMapper.selectByPrimaryKey(id),
                LitemallNoticeDTO.class);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        LitemallNoticeExample example = new LitemallNoticeExample();
        example.or().andIdIn(ids).andDeletedEqualTo(false);
        LitemallNotice notice = new LitemallNotice();
        notice.setUpdateTime(LocalDateTime.now());
        notice.setDeleted(true);
        noticeMapper.updateByExampleSelective(notice, example);
    }

}
