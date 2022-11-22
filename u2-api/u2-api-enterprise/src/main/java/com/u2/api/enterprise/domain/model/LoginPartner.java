package com.u2.api.enterprise.domain.model;

import com.u2.api.enterprise.domain.EntPartner;

import java.io.Serializable;

/**
 * 合作者登录信息
 *
 * @author vhans
 */
public class LoginPartner implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 合作者唯一标识
     */
    private String token;

    /**
     * 合作者id
     */
    private Long partnerId;

    /**
     * 企业名称
     */
    private String partnerName;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 合作者信息
     */
    private EntPartner entPartner;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getPartnerid() {
        return partnerId;
    }

    public void setPartnerid(Long partnerid) {
        this.partnerId = partnerid;
    }

    public String getPartnername() {
        return partnerName;
    }

    public void setPartnername(String partnerName) {
        this.partnerName = partnerName;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public EntPartner getEntPartner() {
        return entPartner;
    }

    public void setEntPartner(EntPartner entPartner) {
        this.entPartner = entPartner;
    }
}
