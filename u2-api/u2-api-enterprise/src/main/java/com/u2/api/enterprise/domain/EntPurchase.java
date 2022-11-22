package com.u2.api.enterprise.domain;

import com.u2.api.system.domain.SysDept;
import com.u2.common.core.annotation.Excel;
import com.u2.common.core.annotation.Excels;
import com.u2.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 采购单信息对象 ent_purchase_all
 *
 * @author vhans
 * @date 2022-05-28
 */
public class EntPurchase extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 采购单ID
     */
    private Long purchaseId;

    /**
     * 采购单编号
     */
    @Excel(name = "采购单编号")
    private String sn;

    /**
     * 所属部门ID
     */
    private Long deptId;

    /**
     * 采购单仓储位置
     */
    @Excel(name = "采购单仓储位置")
    private String location;

    /**
     * 采购单总价
     */
    @Excel(name = "采购单总价")
    private BigDecimal money;

    /**
     * 商品件数
     */
    @Excel(name = "商品件数", cellType = Excel.ColumnType.NUMERIC)
    private Integer num;

    /**
     * 采购单状态
     */
    @Excel(name = "采购单状态", readConverterExp = "0=待审核,1=待采购,2=采购中,3=审核失败,4=已完成")
    private Integer status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 采购原因
     */
    @Excel(name = "采购原因")
    private String reason;

    /**
     * 部门对象
     */
    @Excels({
            @Excel(name = "部门名称", targetAttr = "dept_name", type = Excel.Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "leader", type = Excel.Type.EXPORT),
            @Excel(name = "负责人电话", targetAttr = "phone", type = Excel.Type.EXPORT),
    })
    private SysDept dept;

    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSn() {
        return sn;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setNum(Integer productNum) {
        this.num = productNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getPurchaseId())
                .append("sn", getSn())
                .append("deptId", getDeptId())
                .append("location", getLocation())
                .append("money", getMoney())
                .append("num", getNum())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("reason", getReason())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
