package com.u2.auth.service;

import com.u2.api.enterprise.domain.EntMember;
import com.u2.api.enterprise.domain.EntPartner;
import com.u2.api.system.domain.SysUser;
import com.u2.common.core.constant.CacheConstants;
import com.u2.common.core.constant.Constants;
import com.u2.common.core.exception.ServiceException;
import com.u2.common.redis.service.RedisService;
import com.u2.common.security.utils.SecurityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 登录密码处理
 *
 * @author vhans
 */
@Component
public class PasswordService {

    @Resource
    private RedisService redisService;

    @Resource
    private SysRecordLogService recordLogService;

    /**
     * 登录账户密码错误次数缓存键名
     *
     * @param name 账号
     * @return 缓存键key
     */
    private String getCacheKey(String name) {
        return CacheConstants.PWD_ERR_CNT_KEY + name;
    }

    /**
     * 验证后台用户密码
     */
    public void validateUser(SysUser user, String password) {
        String name = user.getUserName();
        validateP(password, name, user.getPassword());
    }

    /**
     * 验证商城会员密码
     */
    public void validateMember(EntMember member, String password) {
        String name = member.getMemberName();
        validateP(password, name, member.getPassword());
    }

    /**
     * 验证企业合作者密码
     */
    public void validatePartner(EntPartner partner, String password) {
        String name = partner.getPartnerName();
        validateP(password, name, partner.getPassword());
    }

    /**
     * 验证密码
     * @param password 输入的密码
     * @param name 已查出的账号
     * @param truePassword 正确的密码
     */
    private void validateP(String password, String name, String truePassword) {
        Integer retryCount = redisService.getCacheObject(getCacheKey(name));
        if (retryCount == null) {
            retryCount = 0;
        }
        int maxRetryCount = CacheConstants.PASSWORD_MAX_RETRY_COUNT;
        Long lockTime = CacheConstants.PASSWORD_LOCK_TIME;
        if (retryCount >= maxRetryCount) {
            String errMsg = String.format("密码输入错误%s次，帐户锁定%s分钟", maxRetryCount, lockTime);
            recordLogService.recordLoginInfo(name, Constants.LOGIN_FAIL, errMsg);
            throw new ServiceException(errMsg);
        }
        if (!SecurityUtils.matchesPassword(password, truePassword)) {
            retryCount = retryCount + 1;
            recordLogService.recordLoginInfo(name, Constants.LOGIN_FAIL, String.format("密码输入错误%s次", retryCount));
            redisService.setCacheObject(getCacheKey(name), retryCount, lockTime, TimeUnit.MINUTES);
            throw new ServiceException("账户不存在/密码错误");
        } else {
            clearLoginRecordCache(name);
        }
    }

    /**
     * 清除登录缓存
     * @param loginName 登录账户
     */
    public void clearLoginRecordCache(String loginName) {
        if (redisService.hasKey(getCacheKey(loginName))) {
            redisService.deleteObject(getCacheKey(loginName));
        }
    }
}
