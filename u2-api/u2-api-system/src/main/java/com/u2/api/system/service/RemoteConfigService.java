package com.u2.api.system.service;

import com.u2.api.system.service.factory.RemoteConfigFallbackFactory;
import com.u2.common.core.constant.ServiceNameConstants;
import com.u2.common.core.web.domain.model.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 配置服务
 *
 * @author vhans
 */
@FeignClient(contextId = "remoteConfigService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteConfigFallbackFactory.class)
public interface RemoteConfigService {
    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/config/configKey/{configKey}")
    AjaxResult getConfig(@PathVariable String configKey);
}
