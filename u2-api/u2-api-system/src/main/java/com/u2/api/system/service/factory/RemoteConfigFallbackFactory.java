package com.u2.api.system.service.factory;

import com.u2.api.system.service.RemoteConfigService;
import com.u2.common.core.web.domain.model.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author vhans
 */
@Component
public class RemoteConfigFallbackFactory implements FallbackFactory<RemoteConfigService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteConfigFallbackFactory.class);

    @Override
    public RemoteConfigService create(Throwable throwable) {
        log.error("配置服务调用失败:{}", throwable.getMessage());
        return null;
    }
}
