package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.LitemallCartDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel("购物车首页")
public class CartIndexVo {

    @ApiModelProperty("商品列表")
    private List<LitemallCartDTO> cartList;
    @ApiModelProperty("统计信息")
    private CartTotalVo cartTotal;

    @ApiModel("购物车统计信息")
    public static class CartTotalVo {
        /**
         * 产品总数
         */
        @ApiModelProperty("产品总数")
        private int goodsCount;
        /**
         * 产品总价格
         */
        @ApiModelProperty("产品总价格")
        private BigDecimal goodsAmount;
        /**
         * 选中产品总数
         */
        @ApiModelProperty("选中产品总数")
        private int checkedGoodsCount;
        /**
         * 选种产品总价格
         */
        @ApiModelProperty("选种产品总价格")
        private BigDecimal checkedGoodsAmount;

        public int getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
        }

        public BigDecimal getGoodsAmount() {
            return goodsAmount;
        }

        public void setGoodsAmount(BigDecimal goodsAmount) {
            this.goodsAmount = goodsAmount;
        }

        public int getCheckedGoodsCount() {
            return checkedGoodsCount;
        }

        public void setCheckedGoodsCount(int checkedGoodsCount) {
            this.checkedGoodsCount = checkedGoodsCount;
        }

        public BigDecimal getCheckedGoodsAmount() {
            return checkedGoodsAmount;
        }

        public void setCheckedGoodsAmount(BigDecimal checkedGoodsAmount) {
            this.checkedGoodsAmount = checkedGoodsAmount;
        }


    }


    public List<LitemallCartDTO> getCartList() {
        return cartList;
    }

    public void setCartList(List<LitemallCartDTO> cartList) {
        this.cartList = cartList;
    }

    public CartTotalVo getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(CartTotalVo cartTotal) {
        this.cartTotal = cartTotal;
    }
}
