package com.little.g.springcloud.mall.web.manager;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.DateTimeUtil;
import com.little.g.springcloud.common.utils.JacksonUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.MallErrorCodes;
import com.little.g.springcloud.mall.api.*;
import com.little.g.springcloud.mall.dto.*;
import com.little.g.springcloud.mall.enums.NotifyType;
import com.little.g.springcloud.mall.system.SystemConfig;
import com.little.g.springcloud.mall.util.CouponUserConstant;
import com.little.g.springcloud.mall.util.GrouponConstant;
import com.little.g.springcloud.mall.util.OrderHandleOption;
import com.little.g.springcloud.mall.util.OrderUtil;
import com.little.g.springcloud.mall.web.form.PrepayForm;
import com.little.g.springcloud.mall.web.task.OrderUnpaidTask;
import com.little.g.springcloud.mall.web.vo.*;
import com.little.g.springcloud.pay.api.LittlePayService;
import com.little.g.springcloud.pay.api.PreOrderService;
import com.little.g.springcloud.pay.dto.PreorderDTO;
import com.little.g.springcloud.pay.enums.FixAccount;
import com.little.g.springcloud.pay.enums.MerchantId;
import com.little.g.springcloud.pay.params.PreOrderParams;
import com.little.g.springcloud.user.api.UserService;
import com.little.g.springcloud.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.little.g.springcloud.mall.MallErrorCodes.*;

/**
 * 订单服务
 *
 * <p>
 * 订单状态： 101 订单生成，未支付；102，下单后未支付用户取消；103，下单后未支付超时系统自动取消 201
 * 支付完成，商家未发货；202，订单生产，已付款未发货，但是退款取消； 301 商家发货，用户未确认； 401 用户确认收货； 402
 * 用户没有确认收货超过一定时间，系统自动确认收货；
 *
 * <p>
 * 用户操作： 当101用户未付款时，此时用户可以进行的操作是取消订单，或者付款操作 当201支付完成而商家未发货时，此时用户可以取消订单并申请退款
 * 当301商家已发货时，此时用户可以有确认收货的操作 当401用户确认收货以后，此时用户可以进行的操作是删除订单，评价商品，申请售后，或者再次购买
 * 当402系统自动确认收货以后，此时用户可以删除订单，评价商品，申请售后，或者再次购买
 */
@Service
@Slf4j
public class OrderManager {

    @Reference
    private UserService userService;

    @Reference
    private LitemallOrderService orderService;

    @Reference
    private LitemallOrderGoodsService orderGoodsService;

    @Reference
    private LitemallAddressService addressService;

    @Reference
    private LitemallCartService cartService;

    @Reference
    private LitemallRegionService regionService;

    @Reference
    private LitemallGoodsProductService productService;

    @Reference
    private LittlePayService littlePayService;

    @Reference
    private PreOrderService preOrderService;

    @Reference
    private NotifyService notifyService;

    @Reference
    private LitemallGrouponRulesService grouponRulesService;

    @Reference
    private LitemallGrouponService grouponService;

    @Reference
    private QCodeService qCodeService;

    @Reference
    private ExpressService expressService;

    @Autowired
    private LitemallCommentService commentService;

    @Reference
    private LitemallCouponService couponService;

    @Reference
    private LitemallCouponUserService couponUserService;

    @Reference
    private CouponVerifyService couponVerifyService;

    @Autowired
    private TaskService taskService;

    @Reference
    private LitemallAftersaleService aftersaleService;

    /**
     * 订单列表
     *
     * @param userId   用户ID
     * @param showType 订单信息： 0，全部订单； 1，待付款； 2，待发货； 3，待收货； 4，待评价。
     * @param page     分页页数
     * @param limit    分页大小
     * @return 订单列表
     */
    public ResultJson<Page<OrderVo>> list(Integer userId, Integer showType, Integer page,
                                          Integer limit, String sort, String order) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        List<Short> orderStatus = OrderUtil.orderStatus(showType);
        PageInfo<LitemallOrderDTO> pageInfo = orderService.queryByOrderStatus(userId,
                orderStatus, page, limit, sort, order);
        List<LitemallOrderDTO> orderList = pageInfo.getList();
        List<OrderVo> orderVoList = new ArrayList<>(orderList.size());
        for (LitemallOrderDTO o : orderList) {
            OrderVo orderVo = new OrderVo();
            orderVo.setId(o.getId());
            orderVo.setOrderSn(o.getOrderSn());
            orderVo.setActualPrice(o.getActualPrice());
            orderVo.setOrderStatusText(OrderUtil.orderStatusText(o));
            orderVo.setHandleOption(OrderUtil.build(o));
            orderVo.setAftersaleStatus(o.getAftersaleStatus());

            LitemallGrouponDTO groupon = grouponService.queryByOrderId(o.getId());
            if (groupon != null) {
                orderVo.setGroupin(true);
            } else {
                orderVo.setGroupin(false);
            }

            List<LitemallOrderGoodsDTO> orderGoodsList = orderGoodsService
                    .queryByOid(o.getId());
            List<OrderGoodsVo> orderGoodsVoList = new ArrayList<>(orderGoodsList.size());
            for (LitemallOrderGoodsDTO orderGoods : orderGoodsList) {
                OrderGoodsVo orderGoodsVo = new OrderGoodsVo();
                orderGoodsVo.setId(orderGoods.getId());
                orderGoodsVo.setGoodsName(orderGoods.getGoodsName());
                orderGoodsVo.setNumber(orderGoods.getNumber());
                orderGoodsVo.setPicUrl(orderGoods.getPicUrl());
                orderGoodsVo.setSpecifications(orderGoods.getSpecifications());
                orderGoodsVo.setPrice(orderGoods.getPrice());
                orderGoodsVoList.add(orderGoodsVo);
            }
            orderVo.setGoodsList(orderGoodsVoList);

            orderVoList.add(orderVo);
        }

        return ResponseUtil.okList(orderVoList, pageInfo);
    }

    /**
     * 订单详情
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @return 订单详情
     */
    public ResultJson<OrderDetailVo> detail(Integer userId, Integer orderId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        // 订单信息
        LitemallOrderDTO order = orderService.findById(userId, orderId);
        if (null == order) {
            return ResponseUtil.fail(MallErrorCodes.ORDER_UNKNOWN, "订单不存在");
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.fail(MallErrorCodes.ORDER_INVALID, "不是当前用户的订单");
        }
        OrderDetailInfoVo orderVo = new OrderDetailInfoVo();
        orderVo.setId(order.getId());
        orderVo.setOrderSn(order.getOrderSn());
        orderVo.setMessage(order.getMessage());
        orderVo.setAddTime(order.getAddTime());
        orderVo.setConsignee(order.getConsignee());
        orderVo.setMobile(order.getMobile());
        orderVo.setAddress(order.getAddress());
        orderVo.setGoodsPrice(order.getGoodsPrice());
        orderVo.setCouponPrice(order.getCouponPrice());
        orderVo.setFreightPrice(order.getFreightPrice());
        orderVo.setActualPrice(order.getActualPrice());
        orderVo.setOrderStatusText(OrderUtil.orderStatusText(order));
        orderVo.setHandleOption(OrderUtil.build(order));
        orderVo.setAftersaleStatus(order.getAftersaleStatus());
        orderVo.setExpCode(order.getShipChannel());
        orderVo.setExpName(expressService.getVendorName(order.getShipChannel()));
        orderVo.setExpNo(order.getShipSn());

        List<LitemallOrderGoodsDTO> orderGoodsList = orderGoodsService
                .queryByOid(order.getId());

        OrderDetailVo result = new OrderDetailVo();
        result.setOrderInfo(orderVo);
        result.setOrderGoods(orderGoodsList);

        // 订单状态为已发货且物流信息不为空
        // "YTO", "800669400640887922"
        if (order.getOrderStatus().equals(OrderUtil.STATUS_SHIP)) {
            ExpressInfoDTO ei = expressService.getExpressInfo(order.getShipChannel(),
                    order.getShipSn());
            if (ei == null) {
                result.setExpressInfo(null);
            } else {
                result.setExpressInfo(ei);
            }
        } else {
            result.setExpressInfo(null);
        }

        return ResponseUtil.ok(result);

    }

    /**
     * 提交订单
     * <p>
     * 1. 创建订单表项和订单商品表项; 2. 购物车清空; 3. 优惠券设置已用; 4. 商品货品库存减少; 5. 如果是团购商品，则创建团购活动表项。
     *
     * @param userId 用户ID
     * @param body   订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx,
     *               grouponRulesId: xxx, grouponLinkId: xxx}
     * @return 提交订单操作结果
     */
    @Transactional
    public ResultJson submit(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (body == null) {
            return ResponseUtil.badArgument();
        }
        Integer cartId = JacksonUtil.parseInteger(body, "cartId");
        Integer addressId = JacksonUtil.parseInteger(body, "addressId");
        Integer couponId = JacksonUtil.parseInteger(body, "couponId");
        Integer userCouponId = JacksonUtil.parseInteger(body, "userCouponId");
        String message = JacksonUtil.parseString(body, "message");
        Integer grouponRulesId = JacksonUtil.parseInteger(body, "grouponRulesId");
        Integer grouponLinkId = JacksonUtil.parseInteger(body, "grouponLinkId");

        // 如果是团购项目,验证活动是否有效
        if (grouponRulesId != null && grouponRulesId > 0) {
            LitemallGrouponRulesDTO rules = grouponRulesService.findById(grouponRulesId);
            // 找不到记录
            if (rules == null) {
                return ResponseUtil.badArgument();
            }
            // 团购规则已经过期
            if (rules.getStatus().equals(GrouponConstant.RULE_STATUS_DOWN_EXPIRE)) {
                return ResponseUtil.fail(GROUPON_EXPIRED, "团购已过期!");
            }
            // 团购规则已经下线
            if (rules.getStatus().equals(GrouponConstant.RULE_STATUS_DOWN_ADMIN)) {
                return ResponseUtil.fail(GROUPON_OFFLINE, "团购已下线!");
            }

            if (grouponLinkId != null && grouponLinkId > 0) {
                // 团购人数已满
                if (grouponService
                        .countGroupon(grouponLinkId) >= (rules.getDiscountMember() - 1)) {
                    return ResponseUtil.fail(GROUPON_FULL, "团购活动人数已满!");
                }
                // NOTE
                // 这里业务方面允许用户多次开团，以及多次参团，
                // 但是会限制以下两点：
                // （1）不允许参加已经加入的团购
                if (grouponService.hasJoin(userId, grouponLinkId)) {
                    return ResponseUtil.fail(GROUPON_JOIN, "团购活动已经参加!");
                }
                // （2）不允许参加自己开团的团购
                LitemallGrouponDTO groupon = grouponService.queryById(userId,
                        grouponLinkId);
                if (groupon.getCreatorUserId().equals(userId)) {
                    return ResponseUtil.fail(GROUPON_JOIN, "团购活动已经参加!");
                }
            }
        }

        if (cartId == null || addressId == null || couponId == null) {
            return ResponseUtil.badArgument();
        }

        // 收货地址
        LitemallAddressDTO checkedAddress = addressService.query(userId, addressId);
        if (checkedAddress == null) {
            return ResponseUtil.badArgument();
        }

        // 团购优惠
        BigDecimal grouponPrice = new BigDecimal(0);
        LitemallGrouponRulesDTO grouponRules = grouponRulesService
                .findById(grouponRulesId);
        if (grouponRules != null) {
            grouponPrice = grouponRules.getDiscount();
        }

        // 货品价格
        List<LitemallCartDTO> checkedGoodsList = null;
        if (cartId.equals(0)) {
            checkedGoodsList = cartService.queryByUidAndChecked(userId);
        } else {
            LitemallCartDTO cart = cartService.findById(cartId);
            checkedGoodsList = new ArrayList<>(1);
            checkedGoodsList.add(cart);
        }
        if (checkedGoodsList.size() == 0) {
            return ResponseUtil.badArgumentValue();
        }
        BigDecimal checkedGoodsPrice = new BigDecimal(0);
        for (LitemallCartDTO checkGoods : checkedGoodsList) {
            // 只有当团购规格商品ID符合才进行团购优惠
            if (grouponRules != null
                    && grouponRules.getGoodsId().equals(checkGoods.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice
                        .add(checkGoods.getPrice().subtract(grouponPrice)
                                .multiply(new BigDecimal(checkGoods.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice.add(checkGoods.getPrice()
                        .multiply(new BigDecimal(checkGoods.getNumber())));
            }
        }

        // 获取可用的优惠券信息
        // 使用优惠券减免的金额
        BigDecimal couponPrice = new BigDecimal(0);
        // 如果couponId=0则没有优惠券，couponId=-1则不使用优惠券
        if (couponId != 0 && couponId != -1) {
            LitemallCouponDTO coupon = couponVerifyService.checkCoupon(userId, couponId,
                    userCouponId, checkedGoodsPrice);
            if (coupon == null) {
                return ResponseUtil.badArgumentValue();
            }
            couponPrice = coupon.getDiscount();
        }

        // 根据订单商品总价计算运费，满足条件（例如88元）则免运费，否则需要支付运费（例如8元）；
        BigDecimal freightPrice = new BigDecimal(0);
        if (checkedGoodsPrice.compareTo(SystemConfig.getFreightLimit()) < 0) {
            freightPrice = SystemConfig.getFreight();
        }

        // 可以使用的其他钱，例如用户积分
        BigDecimal integralPrice = new BigDecimal(0);

        // 订单费用
        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice)
                .subtract(couponPrice).max(new BigDecimal(0));
        // 最终支付费用
        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);

        Integer orderId = null;
        LitemallOrderDTO order = null;
        // 订单
        order = new LitemallOrderDTO();
        order.setUserId(userId);
        order.setOrderSn(orderService.generateOrderSn(userId));
        order.setOrderStatus(OrderUtil.STATUS_CREATE);
        order.setConsignee(checkedAddress.getName());
        order.setMobile(checkedAddress.getTel());
        order.setMessage(message);
        String detailedAddress = checkedAddress.getProvince() + checkedAddress.getCity()
                + checkedAddress.getCounty() + " " + checkedAddress.getAddressDetail();
        order.setAddress(detailedAddress);
        order.setGoodsPrice(checkedGoodsPrice);
        order.setFreightPrice(freightPrice);
        order.setCouponPrice(couponPrice);
        order.setIntegralPrice(integralPrice);
        order.setOrderPrice(orderTotalPrice);
        order.setActualPrice(actualPrice);

        // 有团购
        if (grouponRules != null) {
            order.setGrouponPrice(grouponPrice); // 团购价格
        } else {
            order.setGrouponPrice(new BigDecimal(0)); // 团购价格
        }

        // 添加订单表项
        orderService.add(order);
        orderId = order.getId();

        // 添加订单商品表项
        for (LitemallCartDTO cartGoods : checkedGoodsList) {
            // 订单商品
            LitemallOrderGoodsDTO orderGoods = new LitemallOrderGoodsDTO();
            orderGoods.setOrderId(order.getId());
            orderGoods.setGoodsId(cartGoods.getGoodsId());
            orderGoods.setGoodsSn(cartGoods.getGoodsSn());
            orderGoods.setProductId(cartGoods.getProductId());
            orderGoods.setGoodsName(cartGoods.getGoodsName());
            orderGoods.setPicUrl(cartGoods.getPicUrl());
            orderGoods.setPrice(cartGoods.getPrice());
            orderGoods.setNumber(cartGoods.getNumber());
            orderGoods.setSpecifications(cartGoods.getSpecifications());
            orderGoods.setAddTime(LocalDateTime.now());

            orderGoodsService.add(orderGoods);
        }

        // 删除购物车里面的商品信息
        cartService.clearGoods(userId);

        // 商品货品数量减少
        for (LitemallCartDTO checkGoods : checkedGoodsList) {
            Integer productId = checkGoods.getProductId();
            LitemallGoodsProductDTO product = productService.findById(productId);

            int remainNumber = product.getNumber() - checkGoods.getNumber();
            if (remainNumber < 0) {
                throw new RuntimeException("下单的商品货品数量大于库存量");
            }
            if (productService.reduceStock(productId, checkGoods.getNumber()) == 0) {
                throw new RuntimeException("商品货品库存减少失败");
            }
        }

        // 如果使用了优惠券，设置优惠券使用状态
        if (couponId != 0 && couponId != -1) {
            LitemallCouponUserDTO couponUser = couponUserService.findById(userCouponId);
            couponUser.setStatus(CouponUserConstant.STATUS_USED);
            couponUser.setUsedTime(LocalDateTime.now());
            couponUser.setOrderId(orderId);
            couponUserService.update(couponUser);
        }

        // 如果是团购项目，添加团购信息
        if (grouponRulesId != null && grouponRulesId > 0) {
            LitemallGrouponDTO groupon = new LitemallGrouponDTO();
            groupon.setOrderId(orderId);
            groupon.setStatus(GrouponConstant.STATUS_NONE);
            groupon.setUserId(userId);
            groupon.setRulesId(grouponRulesId);

            // 参与者
            if (grouponLinkId != null && grouponLinkId > 0) {
                // 参与的团购记录
                LitemallGrouponDTO baseGroupon = grouponService.queryById(grouponLinkId);
                groupon.setCreatorUserId(baseGroupon.getCreatorUserId());
                groupon.setGrouponId(grouponLinkId);
                groupon.setShareUrl(baseGroupon.getShareUrl());
                grouponService.createGroupon(groupon);
            } else {
                groupon.setCreatorUserId(userId);
                groupon.setCreatorUserTime(LocalDateTime.now());
                groupon.setGrouponId(0);
                grouponService.createGroupon(groupon);
                grouponLinkId = groupon.getId();
            }
        }

        // 订单支付超期任务
        taskService.addTask(new OrderUnpaidTask(orderId));

        OrderSubmitVo data = new OrderSubmitVo();
        data.setOrderId(orderId);
        if (grouponRulesId != null && grouponRulesId > 0) {
            data.setGrouponLinkId(grouponLinkId);
        } else {
            data.setGrouponLinkId(0);
        }
        return ResponseUtil.ok(data);
    }

    /**
     * 取消订单
     * <p>
     * 1. 检测当前订单是否能够取消； 2. 设置订单取消状态； 3. 商品货品库存恢复； 4. TODO 优惠券；
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
    @Transactional
    public ResultJson cancel(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }

        LitemallOrderDTO order = orderService.findById(userId, orderId);
        if (order == null) {
            return ResponseUtil.badArgumentValue();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }

        LocalDateTime preUpdateTime = order.getUpdateTime();

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isCancel()) {
            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能取消");
        }

        // 设置订单已取消状态
        order.setOrderStatus(OrderUtil.STATUS_CANCEL);
        order.setEndTime(LocalDateTime.now());
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            throw new RuntimeException("更新数据已失效");
        }

        // 商品货品数量增加
        List<LitemallOrderGoodsDTO> orderGoodsList = orderGoodsService
                .queryByOid(orderId);
        for (LitemallOrderGoodsDTO orderGoods : orderGoodsList) {
            Integer productId = orderGoods.getProductId();
            Short number = orderGoods.getNumber();
            if (productService.addStock(productId, number) == 0) {
                throw new RuntimeException("商品货品库存增加失败");
            }
        }

        return ResponseUtil.ok();
    }

    /**
     * 付款订单的预支付会话标识
     * <p>
     * 1. 检测当前订单是否能够付款 2. 微信商户平台返回支付订单ID 3. 设置订单付款状态
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 支付订单ID
     */
    @Transactional
    public ResultJson prepay(Integer userId, PrepayForm body, HttpServletRequest request) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        LitemallOrderDTO order = orderService.findById(userId, body.getOrderId());
        if (order == null) {
            return ResponseUtil.badArgumentValue();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isPay()) {
            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能支付");
        }

        UserDTO user = userService.getUserById(userId);
        String openid = null;
        if (openid == null) {
            return ResponseUtil.fail(AUTH_OPENID_UNACCESS, "订单不能支付");
        }
        ResultJson result = createPayParams(userId, body.getPayType(), order);

        if (orderService.updateWithOptimisticLocker(order) == 0) {
            return ResponseUtil.updatedDateExpired();
        }

        return result;
    }

    private ResultJson createPayParams(Integer userId, String payType, LitemallOrderDTO order) {
        PreOrderParams params = new PreOrderParams();
        params.setAccountId(userId);
        params.setMchId(MerchantId.LittelG.getValue());
        params.setComment("支付订单");
        params.setOppositAccount(FixAccount.LITTLE_G.getValue());

        long fee = 0;
        BigDecimal actualPrice = order.getActualPrice();
        fee = actualPrice.multiply(new BigDecimal(100)).longValue();
        params.setTotalFee(fee);
        params.setOutTradeNo(order.getOrderSn());
        PreorderDTO preorderDTO = preOrderService.create(params);

        return littlePayService.prePay(userId, payType,
                preorderDTO.getPreOrderNo());
    }

    /**
     * 微信H5支付
     *
     * @param userId
     * @param body
     * @param request
     * @return
     */
    @Transactional
    public ResultJson h5pay(Integer userId, PrepayForm body, HttpServletRequest request) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }


        LitemallOrderDTO order = orderService.findById(userId, body.getOrderId());
        if (order == null) {
            return ResponseUtil.badArgumentValue();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isPay()) {
            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能支付");
        }

        ResultJson result = createPayParams(userId, body.getPayType(), order);

        return result;
    }

    /**
     * 微信付款成功或失败回调接口
     * <p>
     * 1. 检测当前订单是否是付款状态; 2. 设置订单付款成功状态相关信息; 3. 响应微信商户平台.
     *
     * @param request  请求内容
     * @param response 响应内容
     * @return 操作结果
     */
    @Transactional
    public Object payNotify(HttpServletRequest request, HttpServletResponse response) {
        String xmlResult = null;
        try {
            xmlResult = IOUtils.toString(request.getInputStream(),
                    request.getCharacterEncoding());
        } catch (IOException e) {
            e.printStackTrace();
            return WxPayNotifyResponse.fail(e.getMessage());
        }

        WxPayOrderNotifyResult result = null;
        try {
            // TODO: 支付参数
            // result = wxPayService.parseOrderNotifyResult(xmlResult);

            if (!WxPayConstants.ResultCode.SUCCESS.equals(result.getResultCode())) {
                log.error(xmlResult);
                throw new WxPayException("微信通知支付失败！");
            }
            if (!WxPayConstants.ResultCode.SUCCESS.equals(result.getReturnCode())) {
                log.error(xmlResult);
                throw new WxPayException("微信通知支付失败！");
            }
        } catch (WxPayException e) {
            e.printStackTrace();
            return WxPayNotifyResponse.fail(e.getMessage());
        }

        log.info("处理腾讯支付平台的订单支付");
        log.info(String.valueOf(result));

        String orderSn = result.getOutTradeNo();
        String payId = result.getTransactionId();

        // 分转化成元
        String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());
        LitemallOrderDTO order = orderService.findBySn(orderSn);
        if (order == null) {
            return WxPayNotifyResponse.fail("订单不存在 sn=" + orderSn);
        }

        // 检查这个订单是否已经处理过
        if (OrderUtil.hasPayed(order)) {
            return WxPayNotifyResponse.success("订单已经处理成功!");
        }

        // 检查支付订单金额
        if (!totalFee.equals(order.getActualPrice().toString())) {
            return WxPayNotifyResponse
                    .fail(order.getOrderSn() + " : 支付金额不符合 totalFee=" + totalFee);
        }

        order.setPayId(payId);
        order.setPayTime(LocalDateTime.now());
        order.setOrderStatus(OrderUtil.STATUS_PAY);
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            return WxPayNotifyResponse.fail("更新数据已失效");
        }

        // 支付成功，有团购信息，更新团购信息
        LitemallGrouponDTO groupon = grouponService.queryByOrderId(order.getId());
        if (groupon != null) {
            LitemallGrouponRulesDTO grouponRules = grouponRulesService
                    .findById(groupon.getRulesId());

            // 仅当发起者才创建分享图片
            if (groupon.getGrouponId() == 0) {
                String url = qCodeService.createGrouponShareImage(
                        grouponRules.getGoodsName(), grouponRules.getPicUrl(), groupon);
                groupon.setShareUrl(url);
            }
            groupon.setStatus(GrouponConstant.STATUS_ON);
            if (grouponService.updateById(groupon) == 0) {
                return WxPayNotifyResponse.fail("更新数据已失效");
            }

            List<LitemallGrouponDTO> grouponList = grouponService
                    .queryJoinRecord(groupon.getGrouponId());
            if (groupon.getGrouponId() != 0
                    && (grouponList.size() >= grouponRules.getDiscountMember() - 1)) {
                for (LitemallGrouponDTO grouponActivity : grouponList) {
                    grouponActivity.setStatus(GrouponConstant.STATUS_SUCCEED);
                    grouponService.updateById(grouponActivity);
                }

                LitemallGrouponDTO grouponSource = grouponService
                        .queryById(groupon.getGrouponId());
                grouponSource.setStatus(GrouponConstant.STATUS_SUCCEED);
                grouponService.updateById(grouponSource);
            }
        }

        // TODO 发送邮件和短信通知，这里采用异步发送
        // 订单支付成功以后，会发送短信给用户，以及发送邮件给管理员
        notifyService.notifyMail("新订单通知", order.toString());
        // 这里微信的短信平台对参数长度有限制，所以将订单号只截取后6位
        notifyService.notifySmsTemplateSync(order.getMobile(), NotifyType.PAY_SUCCEED,
                new String[]{orderSn.substring(8, 14)});

        // 请依据自己的模版消息配置更改参数
        String[] parms = new String[]{order.getOrderSn(),
                order.getOrderPrice().toString(),
                DateTimeUtil.getDateTimeDisplayString(order.getAddTime()),
                order.getConsignee(), order.getMobile(), order.getAddress()};

        // 取消订单超时未支付任务
        taskService.removeTask(new OrderUnpaidTask(order.getId()));

        return WxPayNotifyResponse.success("处理成功!");
    }

    /**
     * 订单申请退款
     * <p>
     * 1. 检测当前订单是否能够退款； 2. 设置订单申请退款状态。
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    public Object refund(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }

        LitemallOrderDTO order = orderService.findById(userId, orderId);
        if (order == null) {
            return ResponseUtil.badArgument();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }

        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isRefund()) {
            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能取消");
        }

        // 设置订单申请退款状态
        order.setOrderStatus(OrderUtil.STATUS_REFUND);
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            return ResponseUtil.updatedDateExpired();
        }

        // TODO 发送邮件和短信通知，这里采用异步发送
        // 有用户申请退款，邮件通知运营人员
        notifyService.notifyMail("退款申请", order.toString());

        return ResponseUtil.ok();
    }

    /**
     * 确认收货
     * <p>
     * 1. 检测当前订单是否能够确认收货； 2. 设置订单确认收货状态。
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    public Object confirm(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }

        LitemallOrderDTO order = orderService.findById(userId, orderId);
        if (order == null) {
            return ResponseUtil.badArgument();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }

        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isConfirm()) {
            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能确认收货");
        }

        Short comments = orderGoodsService.getComments(orderId);
        order.setComments(comments);

        order.setOrderStatus(OrderUtil.STATUS_CONFIRM);
        order.setConfirmTime(LocalDateTime.now());
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            return ResponseUtil.updatedDateExpired();
        }
        return ResponseUtil.ok();
    }

    /**
     * 删除订单
     * <p>
     * 1. 检测当前订单是否可以删除； 2. 删除订单。
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    public Object delete(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }

        LitemallOrderDTO order = orderService.findById(userId, orderId);
        if (order == null) {
            return ResponseUtil.badArgument();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }

        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isDelete()) {
            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能删除");
        }

        // 订单order_status没有字段用于标识删除
        // 而是存在专门的delete字段表示是否删除
        orderService.deleteById(orderId);
        // 售后也同时删除
        aftersaleService.deleteByOrderId(userId, orderId);

        return ResponseUtil.ok();
    }

    /**
     * 待评价订单商品信息
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @param goodsId 商品ID
     * @return 待评价订单商品信息
     */
    public Object goods(Integer userId, Integer orderId, Integer goodsId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        LitemallOrderDTO order = orderService.findById(userId, orderId);
        if (order == null) {
            return ResponseUtil.badArgument();
        }

        List<LitemallOrderGoodsDTO> orderGoodsList = orderGoodsService
                .findByOidAndGid(orderId, goodsId);
        int size = orderGoodsList.size();

        Assert.state(size < 2, "存在多个符合条件的订单商品");

        if (size == 0) {
            return ResponseUtil.badArgumentValue();
        }

        LitemallOrderGoodsDTO orderGoods = orderGoodsList.get(0);
        return ResponseUtil.ok(orderGoods);
    }

    /**
     * 评价订单商品
     * <p>
     * 确认商品收货或者系统自动确认商品收货后7天内可以评价，过期不能评价。
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    public Object comment(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        Integer orderGoodsId = JacksonUtil.parseInteger(body, "orderGoodsId");
        if (orderGoodsId == null) {
            return ResponseUtil.badArgument();
        }
        LitemallOrderGoodsDTO orderGoods = orderGoodsService.findById(orderGoodsId);
        if (orderGoods == null) {
            return ResponseUtil.badArgumentValue();
        }
        Integer orderId = orderGoods.getOrderId();
        LitemallOrderDTO order = orderService.findById(userId, orderId);
        if (order == null) {
            return ResponseUtil.badArgumentValue();
        }
        Short orderStatus = order.getOrderStatus();
        if (!OrderUtil.isConfirmStatus(order) && !OrderUtil.isAutoConfirmStatus(order)) {
            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "当前商品不能评价");
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.fail(ORDER_INVALID, "当前商品不属于用户");
        }
        Integer commentId = orderGoods.getComment();
        if (commentId == -1) {
            return ResponseUtil.fail(ORDER_COMMENT_EXPIRED, "当前商品评价时间已经过期");
        }
        if (commentId != 0) {
            return ResponseUtil.fail(ORDER_COMMENTED, "订单商品已评价");
        }

        String content = JacksonUtil.parseString(body, "content");
        Integer star = JacksonUtil.parseInteger(body, "star");
        if (star == null || star < 0 || star > 5) {
            return ResponseUtil.badArgumentValue();
        }
        Boolean hasPicture = JacksonUtil.parseBoolean(body, "hasPicture");
        List<String> picUrls = JacksonUtil.parseStringList(body, "picUrls");
        if (hasPicture == null || !hasPicture) {
            picUrls = new ArrayList<>(0);
        }

        // 1. 创建评价
        LitemallCommentDTO comment = new LitemallCommentDTO();
        comment.setUserId(userId);
        comment.setType((byte) 0);
        comment.setValueId(orderGoods.getGoodsId());
        comment.setStar(star.shortValue());
        comment.setContent(content);
        comment.setHasPicture(hasPicture);
        comment.setPicUrls(picUrls.toArray(new String[]{}));
        commentService.save(comment);

        // 2. 更新订单商品的评价列表
        orderGoods.setComment(comment.getId());
        orderGoodsService.updateById(orderGoods);

        // 3. 更新订单中未评价的订单商品可评价数量
        Short commentCount = order.getComments();
        if (commentCount > 0) {
            commentCount--;
        }
        order.setComments(commentCount);
        orderService.updateWithOptimisticLocker(order);

        return ResponseUtil.ok();
    }

}
