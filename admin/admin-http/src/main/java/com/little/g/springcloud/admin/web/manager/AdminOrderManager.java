package com.little.g.springcloud.admin.web.manager;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.vo.AdminOrderDetailVo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.enums.PayType;
import com.little.g.springcloud.common.utils.JacksonUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.*;
import com.little.g.springcloud.mall.dto.LitemallCommentDTO;
import com.little.g.springcloud.mall.dto.LitemallOrderDTO;
import com.little.g.springcloud.mall.dto.LitemallOrderGoodsDTO;
import com.little.g.springcloud.mall.enums.NotifyType;
import com.little.g.springcloud.mall.util.OrderUtil;
import com.little.g.springcloud.pay.api.LittlePayService;
import com.little.g.springcloud.thirdpay.dto.PreRefundResult;
import com.little.g.springcloud.user.api.UserService;
import com.little.g.springcloud.user.dto.UserDTO;
import com.little.g.springcloud.user.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.little.g.springcloud.admin.AdminErrorCodes.*;

@Component
@Slf4j
public class AdminOrderManager {

    @Reference
    private LitemallOrderGoodsService orderGoodsService;

    @Reference
    private LitemallOrderService orderService;

    @Reference
    private LitemallGoodsProductService productService;

    @Reference
    private UserService userService;

    @Reference
    private LitemallCommentService commentService;

    @Reference
    private LittlePayService littlePayService;

    @Reference
    private NotifyService notifyService;

    @Autowired
    private LogHelper logHelper;

    public ResultJson<Page<LitemallOrderDTO>> list(Integer userId, String orderSn, LocalDateTime start,
                                                   LocalDateTime end, List<Short> orderStatusArray, Integer page, Integer limit,
                                                   String sort, String order) {
        PageInfo<LitemallOrderDTO> pageInfo = orderService.querySelective(userId, orderSn,
                start, end, orderStatusArray, page, limit, sort, order);
        return ResponseUtil.okPage(pageInfo);
    }

    public ResultJson<AdminOrderDetailVo> detail(Integer id) {
        LitemallOrderDTO order = orderService.findById(id);
        List<LitemallOrderGoodsDTO> orderGoods = orderGoodsService.queryByOid(id);
        UserDTO user = userService.getUserById(order.getUserId());
        UserVo vo = new UserVo();
        if (user != null) {
            vo.setAvatar(user.getAvatar());
            vo.setNickname(user.getName());
        }
        AdminOrderDetailVo data = new AdminOrderDetailVo();
        data.setOrder(order);
        data.setOrderGoods(orderGoods);
        data.setUser(user);

        return ResponseUtil.ok(data);
    }

    /**
     * 订单退款
     * <p>
     * 1. 检测当前订单是否能够退款; 2. 微信退款操作; 3. 设置订单退款确认状态； 4. 订单商品库存回库。
     * <p>
     * TODO 虽然接入了微信退款API，但是从安全角度考虑，建议开发者删除这里微信退款代码，采用以下两步走步骤： 1. 管理员登录微信官方支付平台点击退款操作进行退款
     * 2. 管理员登录litemall管理后台点击退款操作进行订单状态修改和商品库存回库
     *
     * @param body 订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @Transactional
    public ResultJson refund(String body) {
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        String refundMoney = JacksonUtil.parseString(body, "refundMoney");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(refundMoney)) {
            return ResponseUtil.badArgument();
        }

        LitemallOrderDTO order = orderService.findById(orderId);
        if (order == null) {
            return ResponseUtil.badArgument();
        }

        if (order.getActualPrice().compareTo(new BigDecimal(refundMoney)) != 0) {
            return ResponseUtil.badArgumentValue();
        }

        // 如果订单不是退款状态，则不能退款
        if (!order.getOrderStatus().equals(OrderUtil.STATUS_REFUND)) {
            return ResponseUtil.fail(ORDER_CONFIRM_NOT_ALLOWED, "订单不能确认收货");
        }

        // 微信退款

        // 元转成分
        Long totalFee = order.getActualPrice().multiply(new BigDecimal(100)).longValue();
        PreRefundResult refund = littlePayService.refund(order.getUserId(),
                PayType.WEXINPAY, order.getOrderSn(), totalFee, totalFee);
        if (!refund.getErrorCode().equals("SUCCESS")) {
            log.warn("refund fail: " + refund.getErrorMsg());
            return ResponseUtil.fail(ORDER_REFUND_FAILED, "订单退款失败");
        }

        LocalDateTime now = LocalDateTime.now();
        // 设置订单取消状态
        order.setOrderStatus(OrderUtil.STATUS_REFUND_CONFIRM);
        order.setEndTime(now);
        // 记录订单退款相关信息
        order.setRefundAmount(order.getActualPrice());
        order.setRefundType("微信退款接口");
        order.setRefundContent(refund.getRefundId());
        order.setRefundTime(now);
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

        // TODO 发送邮件和短信通知，这里采用异步发送
        // 退款成功通知用户, 例如“您申请的订单退款 [ 单号:{1} ] 已成功，请耐心等待到账。”
        // 注意订单号只发后6位
        notifyService.notifySmsTemplate(order.getMobile(), NotifyType.REFUND,
                new String[]{order.getOrderSn().substring(8, 14)});

        logHelper.logOrderSucceed("退款", "订单编号 " + order.getOrderSn());
        return ResponseUtil.ok();
    }

    /**
     * 发货 1. 检测当前订单是否能够发货 2. 设置订单发货状态
     *
     * @param body 订单信息，{ orderId：xxx, shipSn: xxx, shipChannel: xxx }
     * @return 订单操作结果 成功则 { errno: 0, errmsg: '成功' } 失败则 { errno: XXX, errmsg: XXX }
     */
    public ResultJson ship(String body) {
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        String shipSn = JacksonUtil.parseString(body, "shipSn");
        String shipChannel = JacksonUtil.parseString(body, "shipChannel");
        if (orderId == null || shipSn == null || shipChannel == null) {
            return ResponseUtil.badArgument();
        }

        LitemallOrderDTO order = orderService.findById(orderId);
        if (order == null) {
            return ResponseUtil.badArgument();
        }

        // 如果订单不是已付款状态，则不能发货
        if (!order.getOrderStatus().equals(OrderUtil.STATUS_PAY)) {
            return ResponseUtil.fail(ORDER_CONFIRM_NOT_ALLOWED, "订单不能确认收货");
        }

        order.setOrderStatus(OrderUtil.STATUS_SHIP);
        order.setShipSn(shipSn);
        order.setShipChannel(shipChannel);
        order.setShipTime(LocalDateTime.now());
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            return ResponseUtil.updatedDateExpired();
        }

        // TODO 发送邮件和短信通知，这里采用异步发送
        // 发货会发送通知短信给用户: *
        // "您的订单已经发货，快递公司 {1}，快递单 {2} ，请注意查收"
        notifyService.notifySmsTemplate(order.getMobile(), NotifyType.SHIP,
                new String[]{shipChannel, shipSn});

        logHelper.logOrderSucceed("发货", "订单编号 " + order.getOrderSn());
        return ResponseUtil.ok();
    }

    /**
     * 删除订单 1. 检测当前订单是否能够删除 2. 删除订单
     *
     * @param body 订单信息，{ orderId：xxx }
     * @return 订单操作结果 成功则 { errno: 0, errmsg: '成功' } 失败则 { errno: XXX, errmsg: XXX }
     */
    public ResultJson delete(String body) {
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        LitemallOrderDTO order = orderService.findById(orderId);
        if (order == null) {
            return ResponseUtil.badArgument();
        }

        // 如果订单不是关闭状态(已取消、系统取消、已退款、用户已确认、系统已确认)，则不能删除
        Short status = order.getOrderStatus();
        if (!status.equals(OrderUtil.STATUS_CANCEL)
                && !status.equals(OrderUtil.STATUS_AUTO_CANCEL)
                && !status.equals(OrderUtil.STATUS_CONFIRM)
                && !status.equals(OrderUtil.STATUS_AUTO_CONFIRM)
                && !status.equals(OrderUtil.STATUS_REFUND_CONFIRM)) {
            return ResponseUtil.fail(ORDER_DELETE_FAILED, "订单不能删除");
        }
        // 删除订单
        orderService.deleteById(orderId);
        // 删除订单商品
        orderGoodsService.deleteByOrderId(orderId);
        logHelper.logOrderSucceed("删除", "订单编号 " + order.getOrderSn());
        return ResponseUtil.ok();
    }

    /**
     * 回复订单商品
     *
     * @param body 订单信息，{ orderId：xxx }
     * @return 订单操作结果 成功则 { errno: 0, errmsg: '成功' } 失败则 { errno: XXX, errmsg: XXX }
     */
    public ResultJson reply(String body) {
        Integer commentId = JacksonUtil.parseInteger(body, "commentId");
        if (commentId == null || commentId == 0) {
            return ResponseUtil.badArgument();
        }
        // 目前只支持回复一次
        LitemallCommentDTO comment = commentService.findById(commentId);
        if (comment == null) {
            return ResponseUtil.badArgument();
        }
        if (!StringUtils.isEmpty(comment.getAdminContent())) {
            return ResponseUtil.fail(ORDER_REPLY_EXIST, "订单商品已回复！");
        }
        String content = JacksonUtil.parseString(body, "content");
        if (StringUtils.isEmpty(content)) {
            return ResponseUtil.badArgument();
        }
        // 更新评价回复
        comment.setAdminContent(content);
        commentService.updateById(comment);

        return ResponseUtil.ok();
    }

}
