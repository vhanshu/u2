package com.u2.api.enterprise.domain;

import com.u2.common.core.annotation.Excel;
import com.u2.common.core.annotation.Excels;
import com.u2.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 订单信息对象 ent_order_all
 *
 * @author vhans
 * @date 2022-05-26
 */
public class EntOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号
     */
    @Excel(name = "订单编号")
    private String sn;

    /**
     * 所属会员
     */
    @Excel(name = "所属会员")
    private Long memberId;

    /**
     * 订单地址
     */
    @Excel(name = "订单地址")
    private String address;

    /**
     * 订单总价
     */
    @Excel(name = "订单总价")
    private BigDecimal money;

    /**
     * 商品件数
     */
    @Excel(name = "商品件数", cellType = Excel.ColumnType.NUMERIC)
    private Integer num;

    /**
     * 订单状态
     */
    @Excel(name = "订单状态", readConverterExp = "0=待发货,1=运输中,2=派送中,3=待取货，4=待评价,5=已完成")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 会员对象
     */
    @Excels({
            @Excel(name = "会员昵称", targetAttr = "memberName", type = Excel.Type.EXPORT),
            @Excel(name = "会员电话", targetAttr = "phoneNumber", type = Excel.Type.EXPORT),
    })
    private EntMember member;

    private String memberName;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSn() {
        return sn;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setAddress(String address) {
        this.address = address; }

    public String getAddress() {
        return address;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public EntMember getMember() {
        return member;
    }

    public void setMember(EntMember member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("orderId", getOrderId())
                .append("sn", getSn())
                .append("memberId", getMemberId())
                .append("address", getAddress())
                .append("money", getMoney())
                .append("num", getNum())
                .append("status", getStatus())
                .append("delflag", getDelFlag())
                .append("remark", getRemark())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("orderItem", getMember())
                .toString();
    }
}
