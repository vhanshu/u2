package com.u2.api.system.service;

import com.u2.api.system.domain.SysLoginInfo;
import com.u2.api.system.domain.SysOperLog;
import com.u2.api.system.service.factory.RemoteLogFallbackFactory;
import com.u2.common.core.constant.SecurityConstants;
import com.u2.common.core.constant.ServiceNameConstants;
import com.u2.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 日志服务
 *
 * @author vhans
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService {
    /**
     * 保存系统日志
     *
     * @param sysOperLog 日志实体
     * @param source     请求来源
     * @return 结果
     */
    @PostMapping("/operlog")
    R<Boolean> saveLog(@RequestBody SysOperLog sysOperLog, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 保存访问记录
     *
     * @param sysLoginInfo 访问实体
     * @param source       请求来源
     * @return 结果
     */
    @PostMapping("/logininfor")
    R<Boolean> saveLoginInfo(@RequestBody SysLoginInfo sysLoginInfo, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
