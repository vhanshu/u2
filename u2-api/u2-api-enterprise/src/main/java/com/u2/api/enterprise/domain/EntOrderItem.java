package com.u2.api.enterprise.domain;

import com.u2.common.core.annotation.Excel;
import com.u2.common.core.annotation.Excels;
import com.u2.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 订单子项信息对象 ent_order_item
 *
 * @author vhans
 * @date 2022-05-26
 */
public class EntOrderItem extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单子项ID
     */
    private Long id;

    /**
     * 包含商品
     */
    private Long productId;

    /**
     * 所属订单
     */
    private Long orderId;

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

    /**
     * 订单对象
     */
    @Excels({
            @Excel(name = "订单编号", targetAttr = "sn", type = Excel.Type.EXPORT),
            @Excel(name = "订单地址", targetAttr = "address", type = Excel.Type.EXPORT)
    })
    private EntOrder order;

    /**
     * 商品对象
     */
    @Excels({
            @Excel(name = "商品名称", targetAttr = "productName", type = Excel.Type.EXPORT),
            @Excel(name = "商品主图", targetAttr = "imageUrl", type = Excel.Type.EXPORT),
            @Excel(name = "商品价格", targetAttr = "price", type = Excel.Type.EXPORT)
    })
    private EntProduct product;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public EntOrder getOrder() {
        return order;
    }

    public void setOrder(EntOrder order) {
        this.order = order;
    }

    public EntProduct getProduct() {
        return product;
    }

    public void setProduct(EntProduct product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("productId", getProductId())
                .append("orderId", getOrderId())
                .append("num", getNum())
                .append("money", getMoney())
                .append("order", getOrder())
                .append("product", getProduct())
                .toString();
    }
}
