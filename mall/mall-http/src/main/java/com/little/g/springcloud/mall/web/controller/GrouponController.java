package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.MallErrorCodes;
import com.little.g.springcloud.mall.api.*;
import com.little.g.springcloud.mall.dto.*;
import com.little.g.springcloud.mall.util.OrderUtil;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import com.little.g.springcloud.mall.vo.UserVo;
import com.little.g.springcloud.mall.web.manager.GrouponRuleManager;
import com.little.g.springcloud.mall.web.vo.GrouponRuleVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 团购服务
 * <p>
 * 需要注意这里团购规则和团购活动的关系和区别。
 */
@RestController
@RequestMapping("/groupon")
@Validated
@Slf4j
public class GrouponController {

    @Reference
    private LitemallGrouponRulesService rulesService;

    @Autowired
    private GrouponRuleManager grouponRuleManager;

    @Reference
    private LitemallGrouponService grouponService;

    @Reference
    private LitemallGoodsService goodsService;

    @Reference
    private LitemallOrderService orderService;

    @Reference
    private LitemallOrderGoodsService orderGoodsService;

    @Reference
    private LitemallUserService userService;

    @Reference
    private ExpressService expressService;

    @Reference
    private LitemallGrouponRulesService grouponRulesService;

    /**
     * 团购规则列表
     *
     * @param page  分页页数
     * @param limit 分页大小
     * @return 团购规则列表
     */
    @GetMapping("list")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        PageInfo<GrouponRuleVo> grouponRuleVoList = grouponRuleManager.queryList(page,
                limit, sort, order);
        return ResponseUtil.okPage(grouponRuleVoList);
    }

    /**
     * 团购活动详情
     *
     * @param userId    用户ID
     * @param grouponId 团购活动ID
     * @return 团购活动详情
     */
    @GetMapping("detail")
    public Object detail(@LoginUser Integer userId, @NotNull Integer grouponId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        LitemallGrouponDTO groupon = grouponService.queryById(userId, grouponId);
        if (groupon == null) {
            return ResponseUtil.badArgumentValue();
        }

        LitemallGrouponRulesDTO rules = rulesService.findById(groupon.getRulesId());
        if (rules == null) {
            return ResponseUtil.badArgumentValue();
        }

        // 订单信息
        LitemallOrderDTO order = orderService.findById(userId, groupon.getOrderId());
        if (null == order) {
            return ResponseUtil.fail(MallErrorCodes.ORDER_UNKNOWN, "订单不存在");
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.fail(MallErrorCodes.ORDER_INVALID, "不是当前用户的订单");
        }
        Map<String, Object> orderVo = new HashMap<>();
        orderVo.put("id", order.getId());
        orderVo.put("orderSn", order.getOrderSn());
        orderVo.put("addTime", order.getAddTime());
        orderVo.put("consignee", order.getConsignee());
        orderVo.put("mobile", order.getMobile());
        orderVo.put("address", order.getAddress());
        orderVo.put("goodsPrice", order.getGoodsPrice());
        orderVo.put("freightPrice", order.getFreightPrice());
        orderVo.put("actualPrice", order.getActualPrice());
        orderVo.put("orderStatusText", OrderUtil.orderStatusText(order));
        orderVo.put("handleOption", OrderUtil.build(order));
        orderVo.put("expCode", order.getShipChannel());
        orderVo.put("expNo", order.getShipSn());

        List<LitemallOrderGoodsDTO> orderGoodsList = orderGoodsService
                .queryByOid(order.getId());
        List<Map<String, Object>> orderGoodsVoList = new ArrayList<>(
                orderGoodsList.size());
        for (LitemallOrderGoodsDTO orderGoods : orderGoodsList) {
            Map<String, Object> orderGoodsVo = new HashMap<>();
            orderGoodsVo.put("id", orderGoods.getId());
            orderGoodsVo.put("orderId", orderGoods.getOrderId());
            orderGoodsVo.put("goodsId", orderGoods.getGoodsId());
            orderGoodsVo.put("goodsName", orderGoods.getGoodsName());
            orderGoodsVo.put("number", orderGoods.getNumber());
            orderGoodsVo.put("retailPrice", orderGoods.getPrice());
            orderGoodsVo.put("picUrl", orderGoods.getPicUrl());
            orderGoodsVo.put("goodsSpecificationValues", orderGoods.getSpecifications());
            orderGoodsVoList.add(orderGoodsVo);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("orderInfo", orderVo);
        result.put("orderGoods", orderGoodsVoList);

        // 订单状态为已发货且物流信息不为空
        // "YTO", "800669400640887922"
        if (order.getOrderStatus().equals(OrderUtil.STATUS_SHIP)) {
            ExpressInfoDTO ei = expressService.getExpressInfo(order.getShipChannel(),
                    order.getShipSn());
            result.put("expressInfo", ei);
        }

        UserVo creator = userService.findUserVoById(groupon.getCreatorUserId());
        List<UserVo> joiners = new ArrayList<>();
        joiners.add(creator);
        int linkGrouponId;
        // 这是一个团购发起记录
        if (groupon.getGrouponId() == 0) {
            linkGrouponId = groupon.getId();
        } else {
            linkGrouponId = groupon.getGrouponId();

        }
        List<LitemallGrouponDTO> groupons = grouponService.queryJoinRecord(linkGrouponId);

        UserVo joiner;
        for (LitemallGrouponDTO grouponItem : groupons) {
            joiner = userService.findUserVoById(grouponItem.getUserId());
            joiners.add(joiner);
        }

        result.put("linkGrouponId", linkGrouponId);
        result.put("creator", creator);
        result.put("joiners", joiners);
        result.put("groupon", groupon);
        result.put("rules", rules);
        return ResponseUtil.ok(result);
    }

    /**
     * 参加团购
     *
     * @param grouponId 团购活动ID
     * @return 操作结果
     */
    @GetMapping("join")
    public Object join(@NotNull Integer grouponId) {
        LitemallGrouponDTO groupon = grouponService.queryById(grouponId);
        if (groupon == null) {
            return ResponseUtil.badArgumentValue();
        }

        LitemallGrouponRulesDTO rules = rulesService.findById(groupon.getRulesId());
        if (rules == null) {
            return ResponseUtil.badArgumentValue();
        }

        LitemallGoodsDTO goods = goodsService.findById(rules.getGoodsId());
        if (goods == null) {
            return ResponseUtil.badArgumentValue();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("groupon", groupon);
        result.put("goods", goods);

        return ResponseUtil.ok(result);
    }

    /**
     * 用户开团或入团情况
     *
     * @param userId   用户ID
     * @param showType 显示类型，如果是0，则是当前用户开的团购；否则，则是当前用户参加的团购
     * @return 用户开团或入团情况
     */
    @GetMapping("my")
    public Object my(@LoginUser Integer userId,
                     @RequestParam(defaultValue = "0") Integer showType) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallGrouponDTO> myGroupons;
        if (showType == 0) {
            myGroupons = grouponService.queryMyGroupon(userId);
        } else {
            myGroupons = grouponService.queryMyJoinGroupon(userId);
        }

        List<Map<String, Object>> grouponVoList = new ArrayList<>(myGroupons.size());

        LitemallOrderDTO order;
        LitemallGrouponRulesDTO rules;
        LitemallUserDTO creator;
        for (LitemallGrouponDTO groupon : myGroupons) {
            order = orderService.findById(userId, groupon.getOrderId());
            rules = rulesService.findById(groupon.getRulesId());
            creator = userService.findById(groupon.getCreatorUserId());

            Map<String, Object> grouponVo = new HashMap<>();
            // 填充团购信息
            grouponVo.put("id", groupon.getId());
            grouponVo.put("groupon", groupon);
            grouponVo.put("rules", rules);
            grouponVo.put("creator", creator.getNickname());

            int linkGrouponId;
            // 这是一个团购发起记录
            if (groupon.getGrouponId() == 0) {
                linkGrouponId = groupon.getId();
                grouponVo.put("isCreator", creator.getId().equals(userId));
            } else {
                linkGrouponId = groupon.getGrouponId();
                grouponVo.put("isCreator", false);
            }
            int joinerCount = grouponService.countGroupon(linkGrouponId);
            grouponVo.put("joinerCount", joinerCount + 1);

            // 填充订单信息
            grouponVo.put("orderId", order.getId());
            grouponVo.put("orderSn", order.getOrderSn());
            grouponVo.put("actualPrice", order.getActualPrice());
            grouponVo.put("orderStatusText", OrderUtil.orderStatusText(order));

            List<LitemallOrderGoodsDTO> orderGoodsList = orderGoodsService
                    .queryByOid(order.getId());
            List<Map<String, Object>> orderGoodsVoList = new ArrayList<>(
                    orderGoodsList.size());
            for (LitemallOrderGoodsDTO orderGoods : orderGoodsList) {
                Map<String, Object> orderGoodsVo = new HashMap<>();
                orderGoodsVo.put("id", orderGoods.getId());
                orderGoodsVo.put("goodsName", orderGoods.getGoodsName());
                orderGoodsVo.put("number", orderGoods.getNumber());
                orderGoodsVo.put("picUrl", orderGoods.getPicUrl());
                orderGoodsVoList.add(orderGoodsVo);
            }
            grouponVo.put("goodsList", orderGoodsVoList);
            grouponVoList.add(grouponVo);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("total", grouponVoList.size());
        result.put("list", grouponVoList);

        return ResponseUtil.ok(result);
    }

}
