package com.little.g.springcloud.mall.web.controller;

import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.utils.JacksonUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.*;
import com.little.g.springcloud.mall.dto.*;
import com.little.g.springcloud.mall.system.SystemConfig;
import com.little.g.springcloud.mall.web.vo.CartCheckoutVo;
import com.little.g.springcloud.mall.web.vo.CartIndexVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.little.g.springcloud.mall.MallErrorCodes.GOODS_NO_STOCK;
import static com.little.g.springcloud.mall.MallErrorCodes.GOODS_UNSHELVE;

/**
 * 用户购物车服务
 */
@Api("购物车")
@RestController
@RequestMapping("/cart")
@Validated
@Slf4j
public class CartController {

    @Reference
    private LitemallCartService cartService;

    @Reference
    private LitemallGoodsService goodsService;

    @Reference
    private LitemallGoodsProductService productService;

    @Reference
    private LitemallAddressService addressService;

    @Reference
    private LitemallGrouponRulesService grouponRulesService;

    @Reference
    private LitemallCouponService couponService;

    @Reference
    private LitemallCouponUserService couponUserService;

    @Reference
    private CouponVerifyService couponVerifyService;

    /**
     * 用户购物车信息
     *
     * @param userId 用户ID
     * @return 用户购物车信息
     */
    @ApiOperation(value = "购物车信息", notes = "用户购物车信息")
    @GetMapping("index")
    public ResultJson<CartIndexVo> index(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallCartDTO> list = cartService.queryByUid(userId);
        List<LitemallCartDTO> cartList = new ArrayList<>();
        // TODO
        // 如果系统检查商品已删除或已下架，则系统自动删除。
        // 更好的效果应该是告知用户商品失效，允许用户点击按钮来清除失效商品。
        for (LitemallCartDTO cart : list) {
            LitemallGoodsDTO goods = goodsService.findById(cart.getGoodsId());
            if (goods == null || !goods.getOnSale()) {
                cartService.deleteById(cart.getId());
                log.debug("系统自动删除失效购物车商品 goodsId=" + cart.getGoodsId() + " productId="
                        + cart.getProductId());
            } else {
                cartList.add(cart);
            }
        }

        Integer goodsCount = 0;
        BigDecimal goodsAmount = new BigDecimal(0.00);
        Integer checkedGoodsCount = 0;
        BigDecimal checkedGoodsAmount = new BigDecimal(0.00);
        for (LitemallCartDTO cart : cartList) {
            goodsCount += cart.getNumber();
            goodsAmount = goodsAmount
                    .add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            if (cart.getChecked()) {
                checkedGoodsCount += cart.getNumber();
                checkedGoodsAmount = checkedGoodsAmount
                        .add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }

        CartIndexVo indexVo = new CartIndexVo();

        CartIndexVo.CartTotalVo cartTotalVo = new CartIndexVo.CartTotalVo();
        cartTotalVo.setGoodsCount(goodsCount);
        cartTotalVo.setGoodsAmount(goodsAmount);
        cartTotalVo.setCheckedGoodsCount(checkedGoodsCount);
        cartTotalVo.setCheckedGoodsAmount(checkedGoodsAmount);
        indexVo.setCartList(cartList);
        indexVo.setCartTotal(cartTotalVo);

        return ResponseUtil.ok(indexVo);
    }

    /**
     * 加入商品到购物车
     * <p>
     * 如果已经存在购物车货品，则增加数量； 否则添加新的购物车货品项。
     *
     * @param userId 用户ID
     * @param cart   购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 加入购物车操作结果
     */
    @ApiOperation(value = "加入商品到购物车", notes = "加入商品到购物车")
    @PostMapping("add")
    public ResultJson add(@LoginUser Integer userId, @RequestBody LitemallCartDTO cart) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (cart == null) {
            return ResponseUtil.badArgument();
        }

        Integer productId = cart.getProductId();
        Integer number = cart.getNumber().intValue();
        Integer goodsId = cart.getGoodsId();
        if (!ObjectUtils.allNotNull(productId, number, goodsId)) {
            return ResponseUtil.badArgument();
        }
        if (number <= 0) {
            return ResponseUtil.badArgument();
        }

        // 判断商品是否可以购买
        LitemallGoodsDTO goods = goodsService.findById(goodsId);
        if (goods == null || !goods.getOnSale()) {
            return ResponseUtil.fail(GOODS_UNSHELVE, "商品已下架");
        }

        LitemallGoodsProductDTO product = productService.findById(productId);
        // 判断购物车中是否存在此规格商品
        LitemallCartDTO existCart = cartService.queryExist(goodsId, productId, userId);
        if (existCart == null) {
            // 取得规格的信息,判断规格库存
            if (product == null || number > product.getNumber()) {
                return ResponseUtil.fail(GOODS_NO_STOCK, "库存不足");
            }

            cart.setId(null);
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName((goods.getName()));
            if (StringUtils.isEmpty(product.getUrl())) {
                cart.setPicUrl(goods.getPicUrl());
            } else {
                cart.setPicUrl(product.getUrl());
            }
            cart.setPrice(product.getPrice());
            cart.setSpecifications(product.getSpecifications());
            cart.setUserId(userId);
            cart.setChecked(true);
            cartService.add(cart);
        } else {
            // 取得规格的信息,判断规格库存
            int num = existCart.getNumber() + number;
            if (num > product.getNumber()) {
                return ResponseUtil.fail(GOODS_NO_STOCK, "库存不足");
            }
            existCart.setNumber((short) num);
            if (cartService.updateById(existCart) == 0) {
                return ResponseUtil.updatedDataFailed();
            }
        }

        return goodscount(userId);
    }

    /**
     * 立即购买
     * <p>
     * 和add方法的区别在于： 1. 如果购物车内已经存在购物车货品，前者的逻辑是数量添加，这里的逻辑是数量覆盖 2.
     * 添加成功以后，前者的逻辑是返回当前购物车商品数量，这里的逻辑是返回对应购物车项的ID
     *
     * @param userId 用户ID
     * @param cart   购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 立即购买操作结果
     */
    @ApiOperation(value = "立即购买",
            notes = "与加入商品到购物车区别  1. 如果购物车内已经存在购物车货品，前者的逻辑是数量添加，这里的逻辑是数量覆盖\n"
                    + "2. 添加成功以后，前者的逻辑是返回当前购物车商品数量，这里的逻辑是返回对应购物车项的ID ")
    @PostMapping("fastadd")
    public ResultJson<Integer> fastadd(@LoginUser Integer userId,
                                       @RequestBody LitemallCartDTO cart) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (cart == null) {
            return ResponseUtil.badArgument();
        }

        Integer productId = cart.getProductId();
        Integer number = cart.getNumber().intValue();
        Integer goodsId = cart.getGoodsId();
        if (!ObjectUtils.allNotNull(productId, number, goodsId)) {
            return ResponseUtil.badArgument();
        }
        if (number <= 0) {
            return ResponseUtil.badArgument();
        }

        // 判断商品是否可以购买
        LitemallGoodsDTO goods = goodsService.findById(goodsId);
        if (goods == null || !goods.getOnSale()) {
            return ResponseUtil.fail(GOODS_UNSHELVE, "商品已下架");
        }

        LitemallGoodsProductDTO product = productService.findById(productId);
        // 判断购物车中是否存在此规格商品
        LitemallCartDTO existCart = cartService.queryExist(goodsId, productId, userId);
        if (existCart == null) {
            // 取得规格的信息,判断规格库存
            if (product == null || number > product.getNumber()) {
                return ResponseUtil.fail(GOODS_NO_STOCK, "库存不足");
            }

            cart.setId(null);
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName((goods.getName()));
            if (StringUtils.isEmpty(product.getUrl())) {
                cart.setPicUrl(goods.getPicUrl());
            } else {
                cart.setPicUrl(product.getUrl());
            }
            cart.setPrice(product.getPrice());
            cart.setSpecifications(product.getSpecifications());
            cart.setUserId(userId);
            cart.setChecked(true);
            cartService.add(cart);
        } else {
            // 取得规格的信息,判断规格库存
            int num = number;
            if (num > product.getNumber()) {
                return ResponseUtil.fail(GOODS_NO_STOCK, "库存不足");
            }
            existCart.setNumber((short) num);
            if (cartService.updateById(existCart) == 0) {
                return ResponseUtil.updatedDataFailed();
            }
        }

        return ResponseUtil.ok(existCart != null ? existCart.getId() : cart.getId());
    }

    /**
     * 修改购物车商品货品数量
     *
     * @param userId 用户ID
     * @param cart   购物车商品信息， { id: xxx, goodsId: xxx, productId: xxx, number: xxx }
     * @return 修改结果
     */
    @ApiOperation(value = "修改购物车商品货品数量",
            notes = "购物车商品信息 { id: xxx, goodsId: xxx, productId: xxx, number: xxx }")
    @PostMapping("update")
    public ResultJson update(@LoginUser Integer userId,
                             @RequestBody LitemallCartDTO cart) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer productId = cart.getProductId();
        Integer number = cart.getNumber().intValue();
        Integer goodsId = cart.getGoodsId();
        Integer id = cart.getId();
        if (!ObjectUtils.allNotNull(id, productId, number, goodsId)) {
            return ResponseUtil.badArgument();
        }
        if (number <= 0) {
            return ResponseUtil.badArgument();
        }

        // 判断是否存在该订单
        // 如果不存在，直接返回错误
        LitemallCartDTO existCart = cartService.findById(userId, id);
        if (existCart == null) {
            return ResponseUtil.badArgumentValue();
        }

        // 判断goodsId和productId是否与当前cart里的值一致
        if (!existCart.getGoodsId().equals(goodsId)) {
            return ResponseUtil.badArgumentValue();
        }
        if (!existCart.getProductId().equals(productId)) {
            return ResponseUtil.badArgumentValue();
        }

        // 判断商品是否可以购买
        LitemallGoodsDTO goods = goodsService.findById(goodsId);
        if (goods == null || !goods.getOnSale()) {
            return ResponseUtil.fail(GOODS_UNSHELVE, "商品已下架");
        }

        // 取得规格的信息,判断规格库存
        LitemallGoodsProductDTO product = productService.findById(productId);
        if (product == null || product.getNumber() < number) {
            return ResponseUtil.fail(GOODS_UNSHELVE, "库存不足");
        }

        existCart.setNumber(number.shortValue());
        if (cartService.updateById(existCart) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok();
    }

    /**
     * 购物车商品货品勾选状态
     * <p>
     * 如果原来没有勾选，则设置勾选状态；如果商品已经勾选，则设置非勾选状态。
     *
     * @param userId 用户ID
     * @param body   购物车商品信息， { productIds: xxx, isChecked: 1/0 }
     * @return 购物车信息
     */
    @ApiOperation(value = "购物车商品货品勾选状态", notes = "如果原来没有勾选，则设置勾选状态；如果商品已经勾选，则设置非勾选状态。")
    @ApiImplicitParam("{ productIds: xxx, isChecked: 1/0 }")
    @PostMapping("checked")
    public ResultJson<CartIndexVo> checked(@LoginUser Integer userId,
                                           @RequestBody String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (body == null) {
            return ResponseUtil.badArgument();
        }

        List<Integer> productIds = JacksonUtil.parseIntegerList(body, "productIds");
        if (productIds == null) {
            return ResponseUtil.badArgument();
        }

        Integer checkValue = JacksonUtil.parseInteger(body, "isChecked");
        if (checkValue == null) {
            return ResponseUtil.badArgument();
        }
        Boolean isChecked = (checkValue == 1);

        cartService.updateCheck(userId, productIds, isChecked);
        return index(userId);
    }

    /**
     * 购物车商品删除
     *
     * @param userId 用户ID
     * @param body   购物车商品信息， { productIds: xxx }
     * @return 购物车信息 成功则 { errno: 0, errmsg: '成功', data: xxx } 失败则 { errno: XXX, errmsg:
     * XXX }
     */
    @ApiOperation(value = "购物车商品删除")
    @ApiImplicitParam("购物车商品信息 { productIds: xxx }")
    @PostMapping("delete")
    public ResultJson<CartIndexVo> delete(@LoginUser Integer userId,
                                          @RequestBody String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (body == null) {
            return ResponseUtil.badArgument();
        }

        List<Integer> productIds = JacksonUtil.parseIntegerList(body, "productIds");

        if (productIds == null || productIds.size() == 0) {
            return ResponseUtil.badArgument();
        }

        cartService.delete(productIds, userId);
        return index(userId);
    }

    /**
     * 购物车商品货品数量
     * <p>
     * 如果用户没有登录，则返回空数据。
     *
     * @param userId 用户ID
     * @return 购物车商品货品数量
     */
    @ApiOperation(value = "购物车货品数量")
    @GetMapping("goodscount")
    public ResultJson<Integer> goodscount(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.ok(0);
        }

        int goodsCount = 0;
        List<LitemallCartDTO> cartList = cartService.queryByUid(userId);
        for (LitemallCartDTO cart : cartList) {
            goodsCount += cart.getNumber();
        }

        return ResponseUtil.ok(goodsCount);
    }

    /**
     * 购物车下单
     *
     * @param userId    用户ID
     * @param cartId    购物车商品ID： 如果购物车商品ID是空，则下单当前用户所有购物车商品； 如果购物车商品ID非空，则只下单当前购物车商品。
     * @param addressId 收货地址ID： 如果收货地址ID是空，则查询当前用户的默认地址。
     * @param couponId  优惠券ID： 如果优惠券ID是空，则自动选择合适的优惠券。
     * @return 购物车操作结果
     */
    @ApiOperation(value = "购物车下单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cartId", value = "购物车商品ID： 如果购物车商品ID是空，则下单当前用户所有购物车商品； 如果购物车商品ID非空，则只下单当前购物车商品。"),
            @ApiImplicitParam(name = "addressId", value = "如果收货地址ID是空，则查询当前用户的默认地址。"),
            @ApiImplicitParam(name = "couponId", value = "优惠券ID： 如果优惠券ID是空，则自动选择合适的优惠券。"),
            @ApiImplicitParam(name = "userCouponId", value = "用户选择的优惠券ID"),
            @ApiImplicitParam(name = "grouponRulesId", value = "团购规则ID")
    })
    @GetMapping("checkout")
    public Object checkout(@LoginUser Integer userId, @RequestParam(required = false) Integer cartId, @RequestParam(required = false) Integer addressId,
                           @RequestParam(required = false) Integer couponId, @RequestParam(required = false) Integer userCouponId, @RequestParam(required = false) Integer grouponRulesId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        // 收货地址
        LitemallAddressDTO checkedAddress = null;
        if (addressId == null || addressId.equals(0)) {
            checkedAddress = addressService.findDefault(userId);
            // 如果仍然没有地址，则是没有收货地址
            // 返回一个空的地址id=0，这样前端则会提醒添加地址
            if (checkedAddress == null) {
                checkedAddress = new LitemallAddressDTO();
                checkedAddress.setId(0);
                addressId = 0;
            } else {
                addressId = checkedAddress.getId();
            }

        } else {
            checkedAddress = addressService.query(userId, addressId);
            // 如果null, 则报错
            if (checkedAddress == null) {
                return ResponseUtil.badArgumentValue();
            }
        }

        // 团购优惠
        BigDecimal grouponPrice = new BigDecimal(0.00);
        LitemallGrouponRulesDTO grouponRules = null;
        if (grouponRulesId != null) {
            grouponRules = grouponRulesService
                    .findById(grouponRulesId);
            if (grouponRules != null) {
                grouponPrice = grouponRules.getDiscount();
            }
        }

        // 商品价格
        List<LitemallCartDTO> checkedGoodsList = null;
        if (cartId == null || cartId.equals(0)) {
            checkedGoodsList = cartService.queryByUidAndChecked(userId);
        } else {
            LitemallCartDTO cart = cartService.findById(userId, cartId);
            if (cart == null) {
                return ResponseUtil.badArgumentValue();
            }
            checkedGoodsList = new ArrayList<>(1);
            checkedGoodsList.add(cart);
        }
        BigDecimal checkedGoodsPrice = new BigDecimal(0.00);
        for (LitemallCartDTO cart : checkedGoodsList) {
            // 只有当团购规格商品ID符合才进行团购优惠
            if (grouponRules != null
                    && grouponRules.getGoodsId().equals(cart.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice
                        .add(cart.getPrice().subtract(grouponPrice)
                                .multiply(new BigDecimal(cart.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice
                        .add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }

        // 计算优惠券可用情况
        BigDecimal tmpCouponPrice = new BigDecimal(0.00);
        Integer tmpCouponId = 0;
        Integer tmpUserCouponId = 0;
        int tmpCouponLength = 0;
        List<LitemallCouponUserDTO> couponUserList = couponUserService.queryAll(userId);
        for (LitemallCouponUserDTO couponUser : couponUserList) {
            LitemallCouponDTO coupon = couponVerifyService.checkCoupon(userId,
                    couponUser.getCouponId(), couponUser.getId(), checkedGoodsPrice);
            if (coupon == null) {
                continue;
            }

            tmpCouponLength++;
            if (tmpCouponPrice.compareTo(coupon.getDiscount()) == -1) {
                tmpCouponPrice = coupon.getDiscount();
                tmpCouponId = coupon.getId();
                tmpUserCouponId = couponUser.getId();
            }
        }
        // 获取优惠券减免金额，优惠券可用数量
        int availableCouponLength = tmpCouponLength;
        BigDecimal couponPrice = new BigDecimal(0);
        // 这里存在三种情况
        // 1. 用户不想使用优惠券，则不处理
        // 2. 用户想自动使用优惠券，则选择合适优惠券
        // 3. 用户已选择优惠券，则测试优惠券是否合适
        if (couponId == null || couponId.equals(-1)) {
            couponId = -1;
            userCouponId = -1;
        } else if (couponId.equals(0)) {
            couponPrice = tmpCouponPrice;
            couponId = tmpCouponId;
            userCouponId = tmpUserCouponId;
        } else {
            LitemallCouponDTO coupon = couponVerifyService.checkCoupon(userId, couponId,
                    userCouponId, checkedGoodsPrice);
            // 用户选择的优惠券有问题，则选择合适优惠券，否则使用用户选择的优惠券
            if (coupon == null) {
                couponPrice = tmpCouponPrice;
                couponId = tmpCouponId;
                userCouponId = tmpUserCouponId;
            } else {
                couponPrice = coupon.getDiscount();
            }
        }

        // 根据订单商品总价计算运费，满88则免运费，否则8元；
        BigDecimal freightPrice = new BigDecimal(0.00);
        if (checkedGoodsPrice.compareTo(SystemConfig.getFreightLimit()) < 0) {
            freightPrice = SystemConfig.getFreight();
        }

        // 可以使用的其他钱，例如用户积分
        BigDecimal integralPrice = new BigDecimal(0.00);

        // 订单费用
        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice)
                .subtract(couponPrice).max(new BigDecimal(0.00));

        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);

        CartCheckoutVo vo = new CartCheckoutVo();
        vo.setAddressId(addressId);
        vo.setCouponId(couponId);
        vo.setUserCouponId(userCouponId);
        vo.setCartId(cartId);
        vo.setGrouponRulesId(grouponRulesId);
        vo.setGrouponPrice(grouponPrice);
        vo.setCheckedAddress(checkedAddress);
        vo.setAvailableCouponLength(availableCouponLength);
        vo.setGoodsTotalPrice(checkedGoodsPrice);
        vo.setFreightPrice(freightPrice);
        vo.setCouponPrice(couponPrice);
        vo.setOrderTotalPrice(orderTotalPrice);
        vo.setActualPrice(actualPrice);
        vo.setCheckedGoodsList(checkedGoodsList);
        return ResponseUtil.ok(vo);
    }

}
