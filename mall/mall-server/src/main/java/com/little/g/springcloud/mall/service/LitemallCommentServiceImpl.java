package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallCommentService;
import com.little.g.springcloud.mall.dto.LitemallCommentDTO;
import com.little.g.springcloud.mall.mapper.LitemallCommentMapper;
import com.little.g.springcloud.mall.model.LitemallComment;
import com.little.g.springcloud.mall.model.LitemallCommentExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallCommentServiceImpl implements LitemallCommentService {

    @Resource
    private LitemallCommentMapper commentMapper;

    @Override
    public List<LitemallCommentDTO> queryGoodsByGid(Integer id, int offset, int limit) {
        LitemallCommentExample example = new LitemallCommentExample();
        example.setOrderByClause(LitemallCommentDTO.Column.addTime.desc());
        example.or().andValueIdEqualTo(id).andTypeEqualTo((byte) 0)
                .andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return DTOUtil.convert2List(commentMapper.selectByExample(example),
                LitemallCommentDTO.class);
    }

    @Override
    public List<LitemallCommentDTO> query(Byte type, Integer valueId, Integer showType,
                                          Integer offset, Integer limit) {
        LitemallCommentExample example = new LitemallCommentExample();
        example.setOrderByClause(LitemallCommentDTO.Column.addTime.desc());
        if (showType == 0) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type)
                    .andDeletedEqualTo(false);
        } else if (showType == 1) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type)
                    .andHasPictureEqualTo(true).andDeletedEqualTo(false);
        } else {
            throw new RuntimeException("showType不支持");
        }
        PageHelper.startPage(offset, limit);
        return DTOUtil.convert2List(commentMapper.selectByExample(example),
                LitemallCommentDTO.class);
    }

    @Override
    public int count(Byte type, Integer valueId, Integer showType) {
        LitemallCommentExample example = new LitemallCommentExample();
        if (showType == 0) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type)
                    .andDeletedEqualTo(false);
        } else if (showType == 1) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type)
                    .andHasPictureEqualTo(true).andDeletedEqualTo(false);
        } else {
            throw new RuntimeException("showType不支持");
        }
        return (int) commentMapper.countByExample(example);
    }

    @Override
    public int save(LitemallCommentDTO comment) {
        comment.setAddTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        return commentMapper
                .insertSelective(DTOUtil.convert2T(comment, LitemallComment.class));
    }

    @Override
    public List<LitemallCommentDTO> querySelective(String userId, String valueId,
                                                   Integer page, Integer size, String sort, String order) {
        LitemallCommentExample example = new LitemallCommentExample();
        LitemallCommentExample.Criteria criteria = example.createCriteria();

        // type=2 是订单商品回复，这里过滤
        criteria.andTypeNotEqualTo((byte) 2);

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(valueId)) {
            criteria.andValueIdEqualTo(Integer.valueOf(valueId)).andTypeEqualTo((byte) 0);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return DTOUtil.convert2List(commentMapper.selectByExample(example),
                LitemallCommentDTO.class);
    }

    @Override
    public void deleteById(Integer id) {
        commentMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public LitemallCommentDTO findById(Integer id) {
        return DTOUtil.convert2T(commentMapper.selectByPrimaryKey(id),
                LitemallCommentDTO.class);
    }

    @Override
    public int updateById(LitemallCommentDTO comment) {
        return commentMapper.updateByPrimaryKeySelective(
                DTOUtil.convert2T(comment, LitemallComment.class));
    }

}
