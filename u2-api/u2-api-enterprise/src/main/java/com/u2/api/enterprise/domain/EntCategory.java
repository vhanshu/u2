package com.u2.api.enterprise.domain;

import com.u2.common.core.annotation.Excel;
import com.u2.common.core.web.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品分类对象 ent_category
 *
 * @author ego
 * @date 2022-04-12
 */
public class EntCategory extends TreeEntity<EntCategory> {
    private static final long serialVersionUID = 1L;

    /**
     * 商品分类状态（0正常 1停用）
     */
    @Excel(name = "商品分类状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("categoryId", getId())
                .append("categoryName", getName())
                .append("parentId", getParentId())
                .append("ancestors", getAncestors())
                .append("orderNum", getOrderNum())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
