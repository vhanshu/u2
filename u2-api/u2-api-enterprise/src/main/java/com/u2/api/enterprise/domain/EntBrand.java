package com.u2.api.enterprise.domain;

import com.u2.common.core.annotation.Excel;
import com.u2.common.core.annotation.Excels;
import com.u2.common.core.annotation.Xss;
import com.u2.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Size;

/**
 * 品牌汇总信息对象 ent_brand
 *
 * @author vhans
 * @date 2022-05-28
 */
public class EntBrand extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    @Excel(name = "品牌名称")
    private String brandName;

    /**
     * 品牌LOGO
     */
    @Excel(name = "品牌LOGO")
    private String logoUrl;

    /**
     * 品牌所属供应商
     */
    @Excel(name = "品牌所属供应商")
    private Long partnerId;

    /**
     * 品牌商品大类代表
     */
    @Excel(name = "品牌商品大类代表")
    private String category;

    /**
     * 是否代理
     */
    @Excel(name = "是否代理", readConverterExp = "0=是,1=否,2=未知")
    private String isAgent;

    /**
     * 是否原创
     */
    @Excel(name = "是否原创", readConverterExp = "0=是,1=否,2=未知")
    private String isOriginal;

    /**
     * 是否易购产品
     */
    @Excel(name = "是否优兔产品", readConverterExp = "0=是,1=否,2=未知")
    private String isEgo;

    /**
     * 企业对象
     */
    @Excels({
            @Excel(name = "企业名称", targetAttr = "partnerName", type = Excel.Type.EXPORT),
            @Excel(name = "企业负责人", targetAttr = "userName", type = Excel.Type.EXPORT),
            @Excel(name = "负责人电话", targetAttr = "phone", type = Excel.Type.EXPORT)
    })
    private EntPartner partner;

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandName(String bname) {
        this.brandName = bname;
    }

    @Xss(message = "品牌名称不能包含脚本字符")
    @Size(max = 30, message = "品牌名称长度不能超过30个字符")
    public String getBrandName() {
        return brandName;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setIsAgent(String isAgent) {
        this.isAgent = isAgent;
    }

    public String getIsAgent() {
        return isAgent;
    }

    public void setIsOriginal(String isOriginal) {
        this.isOriginal = isOriginal;
    }

    public String getIsOriginal() {
        return isOriginal;
    }

    public void setIsEgo(String isEgo) {
        this.isEgo = isEgo;
    }

    public String getIsEgo() {
        return isEgo;
    }

    public EntPartner getPartner() {
        return partner;
    }

    public void setPartner(EntPartner partner) {
        this.partner = partner;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("brandId", getBrandId())
                .append("brandName", getBrandName())
                .append("logoUrl", getLogoUrl())
                .append("partnerId", getPartnerId())
                .append("category", getCategory())
                .append("isAgent", getIsAgent())
                .append("isOriginal", getIsOriginal())
                .append("isEgo", getIsEgo())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .append("createBy", getCreateBy())
                .append("partner", getPartner())
                .toString();
    }
}
