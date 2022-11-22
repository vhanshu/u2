package com.u2.business.enterprise.service.impl;

import com.u2.api.enterprise.domain.EntMember;
import com.u2.api.enterprise.domain.EntOrder;
import com.u2.api.system.service.RemoteConfigService;
import com.u2.business.enterprise.dao.EntMemberMapper;
import com.u2.business.enterprise.dao.EntOrderMapper;
import com.u2.business.enterprise.service.EntMemberService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.exception.ServiceException;
import com.u2.common.core.utils.StringUtils;
import com.u2.common.core.utils.bean.BeanValidators;
import com.u2.common.core.web.domain.model.AjaxResult;
import com.u2.common.security.utils.SecurityUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.List;

/**
 * 客户信息Service业务层处理
 *
 * @author vhans
 * @date 2022-05-25
 */
@Service
public class EntMemberServiceImpl implements EntMemberService {
    private static final Logger log = LoggerFactory.getLogger(EntMemberServiceImpl.class);

    @Resource
    private EntMemberMapper entMemberMapper;

    @Resource
    private EntOrderMapper entOrderMapper;

    private RemoteConfigService remoteConfigService;

    @Resource
    protected Validator validator;

    @Override
    public EntMember selectMemberById(Long id) {
        return entMemberMapper.selectMemberById(id);
    }

    @Override
    public EntMember selectMemberByMemberName(String memberName) {
        return entMemberMapper.selectMemberByMemberName(memberName);
    }

    @Override
    public List<EntMember> selectMemberList(EntMember entMember) {
        return entMemberMapper.selectMemberList(entMember);
    }

    @Override
    public boolean registerMember(EntMember entMember) {
        return entMemberMapper.insertMember(entMember) > 0;
    }

    @Override
    public int insertMember(EntMember entMember) {
        return entMemberMapper.insertMember(entMember);
    }

    @Override
    public int updateMember(EntMember entMember) {
        return entMemberMapper.updateMember(entMember);
    }

    @Override
    public int updateMemberAvatar(@Param("memberName") String memberName, @Param("avatar") String avatar) {
        return entMemberMapper.updateMemberAvatar(memberName, avatar);
    }

    @Override
    public int resetMemberPwd(@Param("memberName") String memberName, @Param("password") String password) {
        return entMemberMapper.resetMemberPwd(memberName, password);
    }

    @Override
    public int deleteMemberByIds(Long[] ids) {
        for (Long id : ids) {
            // 将会员的所有订单冻结
            List<EntOrder> infos = entOrderMapper.selectOrderByMemberId(id);
            for (EntOrder info : infos) {
                entOrderMapper.deleteOrderById(info.getOrderId());
            }
        }
        return entMemberMapper.deleteMemberByIds(ids);
    }

    @Override
    public int deleteMemberById(Long id) {
        // 将会员的所有订单冻结
        List<EntOrder> infos = entOrderMapper.selectOrderByMemberId(id);
        for (EntOrder info : infos) {
            entOrderMapper.deleteOrderById(info.getOrderId());
        }
        return entMemberMapper.deleteMemberById(id);
    }

    @Override
    public String checkMemberNameUnique(EntMember member) {
        long memberId = StringUtils.isNull(member.getMemberId()) ? -1L : member.getMemberId();
        EntMember info = entMemberMapper.checkMemberNameUnique(member.getMemberName());
        if (StringUtils.isNotNull(info) && info.getMemberId() != memberId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkPhoneUnique(EntMember member) {
        long memberId = StringUtils.isNull(member.getMemberId()) ? -1L : member.getMemberId();
        EntMember info = entMemberMapper.checkPhoneUnique(member.getPhoneNumber());
        if (StringUtils.isNotNull(info) && info.getMemberId() != memberId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkEmailUnique(EntMember member) {
        long memberId = StringUtils.isNull(member.getMemberId()) ? -1L : member.getMemberId();
        EntMember info = entMemberMapper.checkEmailUnique(member.getEmail());
        if (StringUtils.isNotNull(info) && info.getMemberId() != memberId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int resetPwd(EntMember member) {
        return entMemberMapper.updateMember(member);
    }

    @Override
    public int updateMemberStatus(EntMember member) {
        return entMemberMapper.updateMember(member);
    }

    @Override
    public String importMember(List<EntMember> memberList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(memberList) || memberList.size() == 0) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        String password = "123456";
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        AjaxResult config = remoteConfigService.getConfig("ent.member.initPassword");
        if(StringUtils.isNotNull(config)){
            password = (String) config.get("msg");
        }
        for (EntMember member : memberList) {
            try {
                // 验证是否存在这个会员
                EntMember m = entMemberMapper.selectMemberByMemberName(member.getMemberName());
                if (StringUtils.isNull(m)) {
                    BeanValidators.validateWithException(validator, member);
                    member.setPassword(SecurityUtils.encryptPassword(password));
                    member.setCreateBy(operName);
                    this.insertMember(member);
                    successNum++;
                    successMsg.append("<br/>").append(successNum).append("、账号 ").append(member.getMemberName()).append(" 导入成功");
                } else if (isUpdateSupport) {
                    BeanValidators.validateWithException(validator, member);
                    member.setUpdateBy(operName);
                    this.updateMember(member);
                    successNum++;
                    successMsg.append("<br/>").append(successNum).append("、账号 ").append(member.getMemberName()).append(" 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>").append(failureNum).append("、账号 ").append(member.getMemberName()).append(" 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + member.getMemberName() + " 导入失败：";
                failureMsg.append(msg).append(e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
