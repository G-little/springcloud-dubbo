package com.little.g.springcloud.mall.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallGrouponService;
import com.little.g.springcloud.mall.dto.LitemallGrouponDTO;
import com.little.g.springcloud.mall.mapper.LitemallGrouponMapper;
import com.little.g.springcloud.mall.model.LitemallGroupon;
import com.little.g.springcloud.mall.model.LitemallGrouponExample;
import com.little.g.springcloud.mall.util.GrouponConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallGrouponServiceImpl implements LitemallGrouponService {
    @Resource
    private LitemallGrouponMapper mapper;

    /**
     * 获取用户发起的团购记录
     *
     * @param userId
     * @return
     */
    @Override
    public List<LitemallGrouponDTO> queryMyGroupon(Integer userId) {
        LitemallGrouponExample example = new LitemallGrouponExample();
        example.or().andUserIdEqualTo(userId).andCreatorUserIdEqualTo(userId).andGrouponIdEqualTo(0).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        example.orderBy("add_time desc");
        return DTOUtil.convert2List(mapper.selectByExample(example), LitemallGrouponDTO.class);
    }

    /**
     * 获取用户参与的团购记录
     *
     * @param userId
     * @return
     */
    @Override
    public List<LitemallGrouponDTO> queryMyJoinGroupon(Integer userId) {
        LitemallGrouponExample example = new LitemallGrouponExample();
        example.or().andUserIdEqualTo(userId).andGrouponIdNotEqualTo(0).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        example.orderBy("add_time desc");
        return DTOUtil.convert2List(mapper.selectByExample(example), LitemallGrouponDTO.class);
    }

    /**
     * 根据OrderId查询团购记录
     *
     * @param orderId
     * @return
     */
    @Override
    public LitemallGrouponDTO queryByOrderId(Integer orderId) {
        LitemallGrouponExample example = new LitemallGrouponExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        return DTOUtil.convert2T(mapper.selectOneByExample(example), LitemallGrouponDTO.class);
    }

    /**
     * 获取某个团购活动参与的记录
     *
     * @param id
     * @return
     */
    @Override
    public List<LitemallGrouponDTO> queryJoinRecord(Integer id) {
        LitemallGrouponExample example = new LitemallGrouponExample();
        example.or().andGrouponIdEqualTo(id).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        example.orderBy("add_time desc");
        return DTOUtil.convert2List(mapper.selectByExample(example), LitemallGrouponDTO.class);
    }

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    @Override
    public LitemallGrouponDTO queryById(Integer id) {
        LitemallGrouponExample example = new LitemallGrouponExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return DTOUtil.convert2T(mapper.selectOneByExample(example), LitemallGrouponDTO.class);
    }

    /**
     * 根据ID查询记录
     *
     * @param userId
     * @param id
     * @return
     */
    @Override
    public LitemallGrouponDTO queryById(Integer userId, Integer id) {
        LitemallGrouponExample example = new LitemallGrouponExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return DTOUtil.convert2T(mapper.selectOneByExample(example), LitemallGrouponDTO.class);
    }

    /**
     * 返回某个发起的团购参与人数
     *
     * @param grouponId
     * @return
     */
    @Override
    public int countGroupon(Integer grouponId) {
        LitemallGrouponExample example = new LitemallGrouponExample();
        example.or().andGrouponIdEqualTo(grouponId).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        return (int) mapper.countByExample(example);
    }

    @Override
    public boolean hasJoin(Integer userId, Integer grouponId) {
        LitemallGrouponExample example = new LitemallGrouponExample();
        example.or().andUserIdEqualTo(userId).andGrouponIdEqualTo(grouponId).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        return mapper.countByExample(example) != 0;
    }

    @Override
    public int updateById(LitemallGrouponDTO groupon) {
        groupon.setUpdateTime(LocalDateTime.now());
        return mapper.updateByPrimaryKeySelective(DTOUtil.convert2T(groupon, LitemallGroupon.class));
    }

    /**
     * 创建或参与一个团购
     *
     * @param groupon
     * @return
     */
    @Override
    public int createGroupon(LitemallGrouponDTO groupon) {
        groupon.setAddTime(LocalDateTime.now());
        groupon.setUpdateTime(LocalDateTime.now());
        return mapper.insertSelective(DTOUtil.convert2T(groupon, LitemallGroupon.class));
    }


    /**
     * 查询所有发起的团购记录
     *
     * @param rulesId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<LitemallGrouponDTO> querySelective(String rulesId, Integer page, Integer size, String sort, String order) {
        LitemallGrouponExample example = new LitemallGrouponExample();
        LitemallGrouponExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(rulesId)) {
            criteria.andRulesIdEqualTo(Integer.parseInt(rulesId));
        }
        criteria.andDeletedEqualTo(false);
        criteria.andStatusNotEqualTo(GrouponConstant.STATUS_NONE);
        criteria.andGrouponIdEqualTo(0);

        PageHelper.startPage(page, size);
        return DTOUtil.convert2List(mapper.selectByExample(example), LitemallGrouponDTO.class);
    }

    @Override
    public List<LitemallGrouponDTO> queryByRuleId(int grouponRuleId) {
        LitemallGrouponExample example = new LitemallGrouponExample();
        example.or().andRulesIdEqualTo(grouponRuleId).andDeletedEqualTo(false);
        return DTOUtil.convert2List(mapper.selectByExample(example), LitemallGrouponDTO.class);
    }
}
