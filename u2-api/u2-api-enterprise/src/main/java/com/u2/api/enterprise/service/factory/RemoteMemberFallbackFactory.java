package com.u2.api.enterprise.service.factory;

import com.u2.api.enterprise.domain.EntMember;
import com.u2.api.enterprise.domain.model.LoginMember;
import com.u2.api.enterprise.service.RemoteMemberService;
import com.u2.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 会员服务降级处理
 *
 * @author vhans
 */
@Component
public class RemoteMemberFallbackFactory implements FallbackFactory<RemoteMemberService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteMemberFallbackFactory.class);

    @Override
    public RemoteMemberService create(Throwable throwable) {
        log.error("会员服务调用失败:{}", throwable.getMessage());
        return new RemoteMemberService() {
            @Override
            public R<LoginMember> getMemberInfo(String memberName, String source) {
                return R.fail("获取会员失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> registerMemberInfo(EntMember entMember, String source) {
                return R.fail("注册会员失败:" + throwable.getMessage());
            }
        };
    }
}
