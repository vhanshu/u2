package com.u2.auth.controller;

import com.u2.api.system.domain.model.LoginUser;
import com.u2.auth.model.LoginBody;
import com.u2.auth.model.RegisterBody;
import com.u2.auth.service.EntranceService;
import com.u2.auth.service.SysRecordLogService;
import com.u2.common.core.constant.Constants;
import com.u2.common.core.constant.SecurityConstants;
import com.u2.common.core.domain.R;
import com.u2.common.core.utils.JwtUtils;
import com.u2.common.core.utils.StringUtils;
import com.u2.common.security.auth.AuthUtil;
import com.u2.common.security.service.UserTokenService;
import com.u2.common.security.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户token 控制
 *
 * @author vhans
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserTokenService userTokenService;

    @Resource
    private EntranceService entranceService;

    @Resource
    private SysRecordLogService recordLogService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form) {
        // 用户登录
        LoginUser userInfo = entranceService.loginUser(form.getName(), form.getPassword());
        // 获取登录token
        return R.ok(userTokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            String username = JwtUtils.getPeopleName(token, SecurityConstants.DETAILS_USERNAME);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            recordLogService.recordLoginInfo(username, Constants.LOGOUT, "退出成功");
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request) {
        LoginUser loginUser = userTokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            // 刷新令牌有效期
            userTokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody) {
        // 用户注册
        entranceService.registerUser(registerBody.getName(), registerBody.getPassword());
        return R.ok();
    }
}
