package com.little.g.springcloud.mall.web.manager;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.api.LitemallGoodsService;
import com.little.g.springcloud.mall.api.LitemallGrouponRulesService;
import com.little.g.springcloud.mall.api.LitemallGrouponService;
import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.dto.LitemallGrouponRulesDTO;
import com.little.g.springcloud.mall.web.vo.GrouponRuleVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GrouponRuleManager {

    @Reference
    private LitemallGrouponRulesService grouponRulesService;

    @Reference
    private LitemallGrouponService grouponService;

    @Reference
    private LitemallGoodsService goodsService;

    public PageInfo<GrouponRuleVo> queryList(Integer page, Integer size) {
        return queryList(page, size, "add_time", "desc");
    }

    public PageInfo<GrouponRuleVo> queryList(Integer page, Integer size, String sort,
                                             String order) {
        PageInfo<LitemallGrouponRulesDTO> pageInfo = grouponRulesService.queryList(page,
                size, sort, order);
        if (pageInfo == null) {
            return new PageInfo<>();
        }

        List<LitemallGrouponRulesDTO> grouponRulesList = pageInfo.getList();
        PageInfo<GrouponRuleVo> grouponPage = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, grouponPage, "list");
        List<GrouponRuleVo> grouponList = new ArrayList<>();
        grouponPage.setList(grouponList);
        BeanUtils.copyProperties(pageInfo, grouponPage, "");
        for (LitemallGrouponRulesDTO rule : grouponRulesList) {
            Integer goodsId = rule.getGoodsId();
            LitemallGoodsDTO goods = goodsService.findById(goodsId);
            if (goods == null) {
                continue;
            }

            GrouponRuleVo grouponRuleVo = new GrouponRuleVo();
            grouponRuleVo.setId(goods.getId());
            grouponRuleVo.setName(goods.getName());
            grouponRuleVo.setBrief(goods.getBrief());
            grouponRuleVo.setPicUrl(goods.getPicUrl());
            grouponRuleVo.setCounterPrice(goods.getCounterPrice());
            grouponRuleVo.setRetailPrice(goods.getRetailPrice());
            grouponRuleVo
                    .setGrouponPrice(goods.getRetailPrice().subtract(rule.getDiscount()));
            grouponRuleVo.setGrouponDiscount(rule.getDiscount());
            grouponRuleVo.setGrouponMember(rule.getDiscountMember());
            grouponRuleVo.setExpireTime(rule.getExpireTime());
            grouponList.add(grouponRuleVo);
        }

        return grouponPage;
    }

}
