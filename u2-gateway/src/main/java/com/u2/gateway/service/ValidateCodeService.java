package com.u2.gateway.service;

import com.u2.common.core.exception.CaptchaException;
import com.u2.common.core.web.domain.model.AjaxResult;

import java.io.IOException;

/**
 * 验证码处理
 *
 * @author vhans
 */
public interface ValidateCodeService {
    /**
     * 生成验证码
     */
    AjaxResult createCaptcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    void checkCaptcha(String key, String value) throws CaptchaException;
}
