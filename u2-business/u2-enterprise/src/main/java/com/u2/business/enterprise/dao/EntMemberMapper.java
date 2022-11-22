package com.u2.business.enterprise.dao;

import com.u2.api.enterprise.domain.EntMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员信息Mapper接口
 *
 * @author vhans
 * @date 2022-05-25
 */
public interface EntMemberMapper {
    /**
     * 根据条件分页查询会员列表
     *
     * @param entMember 会员信息
     * @return 会员信息集合信息
     */
    List<EntMember> selectMemberList(EntMember entMember);

    /**
     * 通过会员名查询会员
     *
     * @param memberName 会员名
     * @return 会员对象信息
     */
    EntMember selectMemberByMemberName(String memberName);

    /**
     * 通过会员ID查询会员
     *
     * @param memberId 会员ID
     * @return 会员对象信息
     */
    EntMember selectMemberById(Long memberId);

    /**
     * 新增会员信息
     *
     * @param member 会员信息
     * @return 结果
     */
    int insertMember(EntMember member);

    /**
     * 修改会员信息
     *
     * @param member 会员信息
     * @return 结果
     */
    int updateMember(EntMember member);

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
     * 通过会员ID删除会员
     *
     * @param memberId 会员ID
     * @return 结果
     */
    int deleteMemberById(Long memberId);

    /**
     * 批量删除会员信息
     *
     * @param memberIds 需要删除的会员ID
     * @return 结果
     */
    int deleteMemberByIds(Long[] memberIds);

    /**
     * 校验会员名称是否唯一
     *
     * @param memberName 会员名称
     * @return 结果
     */
    EntMember checkMemberNameUnique(String memberName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phoneNumber 手机号码
     * @return 结果
     */
    EntMember checkPhoneUnique(String phoneNumber);

    /**
     * 校验email是否唯一
     *
     * @param email 会员邮箱
     * @return 结果
     */
    EntMember checkEmailUnique(String email);
}
