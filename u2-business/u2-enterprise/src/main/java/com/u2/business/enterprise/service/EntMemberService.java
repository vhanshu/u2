package com.u2.business.enterprise.service;

import com.u2.api.enterprise.domain.EntMember;
import com.u2.api.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员信息Service接口
 *
 * @author vhans
 * @date 2022-05-25
 */
public interface EntMemberService {
    /**
     * 通过ID查询会员信息
     *
     * @param id 会员信息主键
     * @return 会员信息
     */
    EntMember selectMemberById(Long id);

    /**
     * 通过会员名查询会员
     *
     * @param memberName 会员名
     * @return 会员对象信息
     */
    EntMember selectMemberByMemberName(String memberName);

    /**
     * 查询会员信息列表
     *
     * @param entMember 会员信息
     * @return 会员信息集合
     */
    List<EntMember> selectMemberList(EntMember entMember);

    /**
     * 注册会员信息
     *
     * @param entMember 会员信息
     * @return 结果
     */
    boolean registerMember(EntMember entMember);

    /**
     * 新增会员信息
     *
     * @param entMember 会员信息
     * @return 结果
     */
    int insertMember(EntMember entMember);

    /**
     * 修改会员信息
     *
     * @param entMember 会员信息
     * @return 结果
     */
    int updateMember(EntMember entMember);

    /**
     * 修改会员头像
     *
     * @param memberName 会员名
     * @param avatar   头像地址
     * @return 结果
     */
    int updateMemberAvatar(@Param("memberName") String memberName, @Param("avatar") String avatar);

    /**
     * 重置会员密码
     *
     * @param memberName 会员名
     * @param password 密码
     * @return 结果
     */
    int resetMemberPwd(@Param("memberName") String memberName, @Param("password") String password);

    /**
     * 批量删除会员信息
     *
     * @param ids 需要删除的会员信息主键集合
     * @return 结果
     */
    int deleteMemberByIds(Long[] ids);

    /**
     * 删除会员信息信息
     *
     * @param id 会员信息主键
     * @return 结果
     */
    int deleteMemberById(Long id);

    /**
     * 校验会员名称是否唯一
     *
     * @param member 会员
     * @return 结果
     */
    String checkMemberNameUnique(EntMember member);

    /**
     * 校验手机号码是否唯一
     *
     * @param member 会员
     * @return 结果
     */
    String checkPhoneUnique(EntMember member);

    /**
     * 校验email是否唯一
     *
     * @param member 会员
     * @return 结果
     */
    String checkEmailUnique(EntMember member);

    /**
     * 重置会员密码
     *
     * @param member 会员信息
     * @return 结果
     */
    int resetPwd(EntMember member);

    /**
     * 修改会员状态
     *
     * @param member 会员信息
     * @return 结果
     */
    int updateMemberStatus(EntMember member);

    /**
     * 导入会员数据
     *
     * @param memberList        会员数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    String importMember(List<EntMember> memberList, Boolean isUpdateSupport, String operName);
}
