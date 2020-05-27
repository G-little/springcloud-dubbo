package com.little.g.springcloud.mall.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallGrouponRulesService;
import com.little.g.springcloud.mall.dto.LitemallGrouponRulesDTO;
import com.little.g.springcloud.mall.mapper.LitemallGoodsMapper;
import com.little.g.springcloud.mall.mapper.LitemallGrouponRulesMapper;
import com.little.g.springcloud.mall.model.LitemallGoods;
import com.little.g.springcloud.mall.model.LitemallGrouponRules;
import com.little.g.springcloud.mall.model.LitemallGrouponRulesExample;
import com.little.g.springcloud.mall.util.GrouponConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallGrouponRulesServiceImpl implements LitemallGrouponRulesService {
    @Resource
    private LitemallGrouponRulesMapper mapper;
    @Resource
    private LitemallGoodsMapper goodsMapper;

    private LitemallGoods.Column[] goodsColumns = new LitemallGoods.Column[]{LitemallGoods.Column.id, LitemallGoods.Column.name, LitemallGoods.Column.brief, LitemallGoods.Column.picUrl, LitemallGoods.Column.counterPrice, LitemallGoods.Column.retailPrice};

    @Override
    public int createRules(LitemallGrouponRulesDTO rules) {
        rules.setAddTime(LocalDateTime.now());
        rules.setUpdateTime(LocalDateTime.now());
        return mapper.insertSelective(DTOUtil.convert2T(rules, LitemallGrouponRules.class));
    }

    /**
     * 根据ID查找对应团购项
     *
     * @param id
     * @return
     */
    @Override
    public LitemallGrouponRulesDTO findById(Integer id) {
        return DTOUtil.convert2T(mapper.selectByPrimaryKey(id), LitemallGrouponRulesDTO.class);
    }

    /**
     * 查询某个商品关联的团购规则
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<LitemallGrouponRulesDTO> queryByGoodsId(Integer goodsId) {
        LitemallGrouponRulesExample example = new LitemallGrouponRulesExample();
        example.or().andGoodsIdEqualTo(goodsId).andStatusEqualTo(GrouponConstant.RULE_STATUS_ON).andDeletedEqualTo(false);
        return DTOUtil.convert2List(mapper.selectByExample(example), LitemallGrouponRulesDTO.class);
    }

    @Override
    public int countByGoodsId(Integer goodsId) {
        LitemallGrouponRulesExample example = new LitemallGrouponRulesExample();
        example.or().andGoodsIdEqualTo(goodsId).andStatusEqualTo(GrouponConstant.RULE_STATUS_ON).andDeletedEqualTo(false);
        return (int) mapper.countByExample(example);
    }

    @Override
    public List<LitemallGrouponRulesDTO> queryByStatus(Short status) {
        LitemallGrouponRulesExample example = new LitemallGrouponRulesExample();
        example.or().andStatusEqualTo(status).andDeletedEqualTo(false);
        return DTOUtil.convert2List(mapper.selectByExample(example), LitemallGrouponRulesDTO.class);
    }

    /**
     * 获取首页团购规则列表
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<LitemallGrouponRulesDTO> queryList(Integer page, Integer limit) {
        return queryList(page, limit, "add_time", "desc");
    }

    @Override
    public List<LitemallGrouponRulesDTO> queryList(Integer page, Integer limit, String sort, String order) {
        LitemallGrouponRulesExample example = new LitemallGrouponRulesExample();
        example.or().andStatusEqualTo(GrouponConstant.RULE_STATUS_ON).andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(page, limit);
        return DTOUtil.convert2List(mapper.selectByExample(example), LitemallGrouponRulesDTO.class);
    }

    /**
     * 判断某个团购规则是否已经过期
     *
     * @return
     */
    @Override
    public boolean isExpired(LitemallGrouponRulesDTO rules) {
        return (rules == null || rules.getExpireTime().isBefore(LocalDateTime.now()));
    }

    /**
     * 获取团购规则列表
     *
     * @param goodsId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<LitemallGrouponRulesDTO> querySelective(String goodsId, Integer page, Integer size, String sort, String order) {
        LitemallGrouponRulesExample example = new LitemallGrouponRulesExample();
        example.setOrderByClause(sort + " " + order);

        LitemallGrouponRulesExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.parseInt(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, size);
        return DTOUtil.convert2List(mapper.selectByExample(example), LitemallGrouponRulesDTO.class);
    }

    @Override
    public void delete(Integer id) {
        mapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public int updateById(LitemallGrouponRulesDTO grouponRules) {
        grouponRules.setUpdateTime(LocalDateTime.now());
        return mapper.updateByPrimaryKeySelective(DTOUtil.convert2T(grouponRules, LitemallGrouponRules.class));
    }
}
