package com.u2.api.enterprise.domain;

import com.u2.common.core.annotation.Excel;
import com.u2.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 购物车信息 ent_cart
 *
 * @author vhans
 */
public class EntCart extends BaseEntity {
    /**
     * 购物车ID
     */
    private Long cartId;

    /**
     * 所属会员ID
     */
    private Long memberId;

    /**
     * 加入商品ID
     */
    private Long productId;

    /**
     * 商品数量
     */
    @Excel(name = "商品数量")
    private Integer num;

    /**
     * 商品总价
     */
    @Excel(name = "商品总价")
    private BigDecimal money;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getCartId())
                .append("deptId", getMemberId())
                .append("deptId", getProductId())
                .append("num", getNum())
                .append("money", getMoney())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
