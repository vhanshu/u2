package com.u2.auth.controller;

import com.u2.api.enterprise.domain.model.LoginMember;
import com.u2.auth.model.LoginBody;
import com.u2.auth.model.RegisterBody;
import com.u2.auth.service.EntranceService;
import com.u2.common.core.domain.R;
import com.u2.common.core.utils.StringUtils;
import com.u2.common.security.auth.AuthUtil;
import com.u2.common.security.service.MemberTokenService;
import com.u2.common.security.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 会员token 控制
 * @author vhans
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberTokenService memberTokenService;

    @Resource
    private EntranceService entranceService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form) {
        // 会员登录
        LoginMember memberInfo = entranceService.loginMember(form.getName(), form.getPassword());
        // 获取登录token
        return R.ok(memberTokenService.createToken(memberInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            // 删除会员缓存记录
            AuthUtil.logoutByToken(token);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request) {
        LoginMember loginMember = memberTokenService.getLoginMember(request);
        if (StringUtils.isNotNull(loginMember)) {
            // 刷新令牌有效期
            memberTokenService.refreshToken(loginMember);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody) {
        // 会员注册
        entranceService.registerMember(registerBody.getName(), registerBody.getPassword());
        return R.ok();
    }
}
