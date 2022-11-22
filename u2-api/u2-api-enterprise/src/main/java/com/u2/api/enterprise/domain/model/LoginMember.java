package com.u2.api.enterprise.domain.model;

import com.u2.api.enterprise.domain.EntMember;

import java.io.Serializable;
import java.util.Set;

/**
 * 会员登录信息
 *
 * @author vhans
 */
public class LoginMember implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 会员唯一标识
     */
    private String token;

    /**
     * 会员名id
     */
    private Long memberId;

    /**
     * 会员名
     */
    private String memberName;

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
     * 优惠信息列表
     */
    private Set<String> vips;

    /**
     * 会员信息
     */
    private EntMember entMember;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getMemberid() {
        return memberId;
    }

    public void setMemberid(Long memberid) {
        this.memberId = memberid;
    }

    public String getMembername() {
        return memberName;
    }

    public void setMembername(String memberName) {
        this.memberName = memberName;
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

    public Set<String> getVips() {
        return vips;
    }

    public void setVips(Set<String> vips) {
        this.vips = vips;
    }

    public EntMember getEntMember() {
        return entMember;
    }

    public void setEntMember(EntMember entMember) {
        this.entMember = entMember;
    }
}
