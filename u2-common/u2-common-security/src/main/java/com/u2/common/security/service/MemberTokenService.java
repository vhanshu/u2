package com.u2.common.security.service;

import com.u2.api.enterprise.domain.model.LoginMember;
import com.u2.common.core.constant.CacheConstants;
import com.u2.common.core.constant.SecurityConstants;
import com.u2.common.core.utils.JwtUtils;
import com.u2.common.core.utils.ServletUtils;
import com.u2.common.core.utils.StringUtils;
import com.u2.common.core.utils.ip.IpUtils;
import com.u2.common.core.utils.uuid.IdUtils;
import com.u2.common.redis.service.RedisService;
import com.u2.common.security.utils.SecurityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 会员 token验证处理
 *
 * @author vhans
 */
@Component
public class MemberTokenService {
    @Resource
    private RedisService redisService;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private final static long expireTime = CacheConstants.EXPIRATION;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    private final static Long MILLIS_MINUTE_TEN = CacheConstants.REFRESH_TIME * MILLIS_MINUTE;

    /**
     * 创建令牌
     */
    public Map<String, Object> createToken(LoginMember loginMember) {
        String token = IdUtils.fastUUID();
        Long memberId = loginMember.getEntMember().getMemberId();
        String memberName = loginMember.getEntMember().getMemberName();
        loginMember.setToken(token);
        loginMember.setMemberid(memberId);
        loginMember.setMembername(memberName);
        loginMember.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        refreshToken(loginMember);

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(SecurityConstants.MEMBER_KEY, token);
        claimsMap.put(SecurityConstants.DETAILS_MEMBER_ID, memberId);
        claimsMap.put(SecurityConstants.DETAILS_MEMBER_NAME, memberName);

        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<>();
        rspMap.put("access_token", JwtUtils.createToken(claimsMap));
        rspMap.put("expires_in", expireTime);
        return rspMap;
    }

    /**
     * 获取会员身份信息
     *
     * @return 会员信息
     */
    public LoginMember getLoginMember() {
        return getLoginMember(ServletUtils.getRequest());
    }

    /**
     * 获取会员身份信息
     *
     * @return 会员信息
     */
    public LoginMember getLoginMember(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = SecurityUtils.getToken(request);
        return getLoginMember(token);
    }

    /**
     * 获取会员身份信息
     *
     * @return 会员信息
     */
    public LoginMember getLoginMember(String token) {
        LoginMember member = null;
        try {
            if (StringUtils.isNotEmpty(token)) {
                String memberKey = JwtUtils.getPeopleKey(token, SecurityConstants.MEMBER_KEY);
                member = redisService.getCacheObject(getTokenKey(memberKey));
                return member;
            }
        } catch (Exception e) {
            System.out.println("MemberTokenService.getLoginMember error:" + e.getMessage());
        }
        return member;
    }

    /**
     * 设置会员身份信息
     */
    public void setLoginMember(LoginMember loginMember) {
        if (StringUtils.isNotNull(loginMember) && StringUtils.isNotEmpty(loginMember.getToken())) {
            refreshToken(loginMember);
        }
    }

    /**
     * 删除会员缓存信息
     */
    public void delLoginMember(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String memberKey = JwtUtils.getPeopleKey(token, SecurityConstants.MEMBER_KEY);
            redisService.deleteObject(getTokenKey(memberKey));
        }
    }

    /**
     * 验证令牌有效期，相差不足120分钟，自动刷新缓存
     */
    public void verifyToken(LoginMember loginMember) {
        long expireTime = loginMember.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginMember);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginMember 登录信息
     */
    public void refreshToken(LoginMember loginMember) {
        loginMember.setLoginTime(System.currentTimeMillis());
        loginMember.setExpireTime(loginMember.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginMember缓存
        String memberKey = getTokenKey(loginMember.getToken());
        redisService.setCacheObject(memberKey, loginMember, expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String token) {
        return ACCESS_TOKEN + token;
    }
}
