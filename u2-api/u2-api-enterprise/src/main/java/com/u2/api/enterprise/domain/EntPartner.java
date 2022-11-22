package com.u2.api.enterprise.domain;

import com.u2.common.core.annotation.Excel;
import com.u2.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 合作方信息对象 ent_partner
 *
 * @author vhans
 * @date 2022-05-28
 */
public class EntPartner extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 合作方ID
     */
    private Long partnerId;

    /**
     * 合作方名称
     */
    @Excel(name = "合作方名称")
    private String partnerName;

    /**
     * 负责人姓名
     */
    @Excel(name = "负责人姓名")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 负责人电话
     */
    @Excel(name = "负责人电话")
    private String phone;

    /**
     * 合作方公司地址
     */
    @Excel(name = "合作方公司地址")
    private String address;

    /**
     * 合作方公司性质
     */
    @Excel(name = "合作方公司性质")
    private String nature;

    /**
     * 合作方主打品牌
     */
    @Excel(name = "合作方主打品牌")
    private String mainBrand;

    /**
     * 合作状态（0正常 1失效）
     */
    @Excel(name = "合作状态", readConverterExp = "0=正常,1=失效")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 合作内容
     */
    @Excel(name = "合作内容")
    private String content;

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getNature() {
        return nature;
    }

    public void setMainBrand(String mainBrand) {
        this.mainBrand = mainBrand;
    }

    public String getMainBrand() {
        return mainBrand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getPartnerId())
                .append("name", getPartnerName())
                .append("userName", getUserName())
                .append("password", getPassword())
                .append("phone", getPhone())
                .append("address", getAddress())
                .append("nature", getNature())
                .append("mainBrand", getMainBrand())
                .append("content", getContent())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
