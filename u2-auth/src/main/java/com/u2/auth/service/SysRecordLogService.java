package com.u2.auth.service;

import com.u2.api.system.domain.SysLoginInfo;
import com.u2.api.system.service.RemoteLogService;
import com.u2.common.core.constant.Constants;
import com.u2.common.core.constant.SecurityConstants;
import com.u2.common.core.utils.ServletUtils;
import com.u2.common.core.utils.StringUtils;
import com.u2.common.core.utils.ip.IpUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 记录日志方法
 *
 * @author vhans
 */
@Component
public class SysRecordLogService {
    @Resource
    private RemoteLogService remoteLogService;

    /**
     * 记录登录信息
     *
     * @param name    账号
     * @param status  状态
     * @param message 消息内容
     */
    public void recordLoginInfo(String name, String status, String message) {
        SysLoginInfo loginInfo = new SysLoginInfo();
        loginInfo.setUserName(name);
        loginInfo.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        loginInfo.setMsg(message);
        // 日志状态
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
            loginInfo.setStatus(Constants.LOGIN_SUCCESS_STATUS);
        } else if (Constants.LOGIN_FAIL.equals(status)) {
            loginInfo.setStatus(Constants.LOGIN_FAIL_STATUS);
        }
        remoteLogService.saveLoginInfo(loginInfo, SecurityConstants.INNER);
    }
}
