package com.u2.auth.service;

import com.u2.api.enterprise.domain.EntMember;
import com.u2.api.enterprise.domain.model.LoginMember;
import com.u2.api.enterprise.service.RemoteMemberService;
import com.u2.api.system.domain.SysUser;
import com.u2.api.system.domain.model.LoginUser;
import com.u2.api.system.service.RemoteUserService;
import com.u2.common.core.constant.Constants;
import com.u2.common.core.constant.SecurityConstants;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.domain.R;
import com.u2.common.core.enums.UserStatus;
import com.u2.common.core.exception.ServiceException;
import com.u2.common.core.utils.StringUtils;
import com.u2.common.security.utils.SecurityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 入口校验方法
 *
 * @author vhans
 */
@Component
public class EntranceService {
    @Resource
    private RemoteUserService remoteUserService;

    @Resource
    private RemoteMemberService remoteMemberService;

    @Resource
    private PasswordService passwordService;

    @Resource
    private SysRecordLogService recordLogService;

    /**
     * 用户登录
     */
    public LoginUser loginUser(String name, String password) {
        // 账号或密码为空 错误
        validateLInfo(name, password);
        // 查询用户信息
        R<LoginUser> result = remoteUserService.getUserInfo(name, SecurityConstants.INNER);
        // 验证账户存在
        validateAccount(result, name);
        LoginUser userInfo = result.getData();
        SysUser user = result.getData().getSysUser();
        // 验证账户状态
        validateStatus(name, user.getDelFlag(), user.getStatus());
        passwordService.validateUser(user, password);
        recordLogService.recordLoginInfo(name, Constants.LOGIN_SUCCESS, "登录成功");
        return userInfo;
    }

    /**
     * 会员登录
     */
    public LoginMember loginMember(String name, String password) {
        // 账号或密码为空 错误
        validateLInfo(name, password);
        // 查询会员信息
        R<LoginMember> result = remoteMemberService.getMemberInfo(name, SecurityConstants.INNER);
        // 验证账户存在
        validateAccount(result, name);
        LoginMember memberInfo = result.getData();
        EntMember member = result.getData().getEntMember();
        // 验证账户状态
        validateStatus(name, member.getDelFlag(), member.getStatus());
        passwordService.validateMember(member, password);
        return memberInfo;
    }

    private void validateStatus(String name, String delFlag, String status) {
        if (UserStatus.DELETED.getCode().equals(delFlag)) {
            recordLogService.recordLoginInfo(name, Constants.LOGIN_FAIL, "对不起，您的账户已被删除");
            throw new ServiceException("对不起，您的账户：" + name + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(status)) {
            recordLogService.recordLoginInfo(name, Constants.LOGIN_FAIL, "账户已停用，请联系管理员");
            throw new ServiceException("对不起，您的账户：" + name + " 已停用");
        }
    }

    /**
     * 注册用户
     */
    public void registerUser(String name, String password) {
        // 账号或密码为空 错误
        validateR(name, password);
        // 注册用户信息
        SysUser sysUser = new SysUser();
        sysUser.setUserName(name);
        sysUser.setNickName(name);
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        R<?> registerResult = remoteUserService.registerUserInfo(sysUser, SecurityConstants.INNER);
        if (R.FAIL == registerResult.getCode()) {
            throw new ServiceException(registerResult.getMsg());
        }
        recordLogService.recordLoginInfo(name, Constants.REGISTER, "注册成功");
    }

    /**
     * 注册会员
     */
    public void registerMember(String name, String password) {
        // 账号或密码为空 错误
        validateR(name, password);
        // 注册会员信息
        EntMember entMember = new EntMember();
        entMember.setMemberName(name);
        entMember.setNickName(name);
        entMember.setPassword(SecurityUtils.encryptPassword(password));
        R<?> registerResult = remoteMemberService.registerMemberInfo(entMember, SecurityConstants.INNER);
        if (R.FAIL == registerResult.getCode()) {
            throw new ServiceException(registerResult.getMsg());
        }
    }

    /**
     * 验证登录信息 账号或密码为空、长度错误
     *
     * @param name     输入的账号
     * @param password 输入的密码
     */
    private void validateLInfo(String name, String password) {
        if (StringUtils.isAnyBlank(name, password)) {
            recordLogService.recordLoginInfo(name, Constants.LOGIN_FAIL, "账号/密码必须填写");
            throw new ServiceException("账号/密码必须填写");
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            recordLogService.recordLoginInfo(name, Constants.LOGIN_FAIL, "密码长度不在指定范围");
            throw new ServiceException("密码长度不在指定范围");
        }
        // 用户名不在指定范围内 错误
        if (name.length() < UserConstants.USERNAME_MIN_LENGTH
                || name.length() > UserConstants.USERNAME_MAX_LENGTH) {
            recordLogService.recordLoginInfo(name, Constants.LOGIN_FAIL, "账号长度不在指定范围");
            throw new ServiceException("账号长度不在指定范围");
        }
    }

    /**
     * 验证账号是否存在
     *
     * @param result 查询的结果
     * @param name   输入的账号
     */
    private void validateAccount(R<?> result, String name) {
        if (StringUtils.isNull(result) || StringUtils.isNull(result.getData())) {
            recordLogService.recordLoginInfo(name, Constants.LOGIN_FAIL, "登录账户不存在");
            throw new ServiceException("登录账户：" + name + " 不存在");
        }
        if (R.FAIL == result.getCode()) {
            throw new ServiceException(result.getMsg());
        }
    }

    /**
     * 验证注册信息 账号或密码为空、长度错误
     *
     * @param name     账号
     * @param password 密码
     */
    private void validateR(String name, String password) {
        if (StringUtils.isAnyBlank(name, password)) {
            throw new ServiceException("账号/密码必须填写");
        }
        if (name.length() < UserConstants.USERNAME_MIN_LENGTH
                || name.length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new ServiceException("账户长度必须在2到20个字符之间");
        }
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new ServiceException("密码长度必须在5到20个字符之间");
        }
    }
}
