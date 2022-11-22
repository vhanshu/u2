package com.u2.api.enterprise.service;

import com.u2.api.enterprise.domain.EntMember;
import com.u2.api.enterprise.domain.model.LoginMember;
import com.u2.api.enterprise.service.factory.RemoteMemberFallbackFactory;
import com.u2.common.core.constant.SecurityConstants;
import com.u2.common.core.constant.ServiceNameConstants;
import com.u2.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 会员服务
 *
 * @author vhans
 */
@FeignClient(contextId = "remoteMemberService", value = ServiceNameConstants.ENTERPRISE_SERVICE, fallbackFactory = RemoteMemberFallbackFactory.class)
public interface RemoteMemberService {
    /**
     * 通过会员名查询会员信息
     *
     * @param memberName 会员名
     * @param source   请求来源
     * @return 结果
     */
    @GetMapping("/member/info/{memberName}")
    R<LoginMember> getMemberInfo(@PathVariable("memberName") String memberName, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册会员信息
     *
     * @param entMember 会员信息
     * @param source  请求来源
     * @return 结果
     */
    @PostMapping("/member/register")
    R<Boolean> registerMemberInfo(@RequestBody EntMember entMember, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
