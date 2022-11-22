package com.u2.business.enterprise.dao;

import com.u2.api.enterprise.domain.EntPartner;
import com.u2.api.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 合作方信息Mapper接口
 *
 * @author vhans
 * @date 2022-05-28
 */
public interface EntPartnerMapper {
    /**
     * 根据ID查询合作方信息
     *
     * @param partnerId 合作方信息主键
     * @return 合作方信息
     */
    EntPartner selectPartnerByPartnerId(Long partnerId);

    /**
     * 根据企业名称查询合作方信息
     *
     * @param partnerName 合作方企业名称
     * @return 合作方信息
     */
    EntPartner selectPartnerByPartnerName(String partnerName);

    /**
     * 查询合作方信息列表
     *
     * @param partner 合作方信息
     * @return 合作方信息集合
     */
    List<EntPartner> selectPartnerList(EntPartner partner);

    /**
     * 新增合作方信息
     *
     * @param partner 合作方信息
     * @return 结果
     */
    int insertPartner(EntPartner partner);

    /**
     * 修改合作方信息
     *
     * @param partner 合作方信息
     * @return 结果
     */
    int updatePartner(EntPartner partner);

    /**
     * 重置登录密码
     *
     * @param userName 负责人
     * @param password 密码
     * @return 结果
     */
    int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

    /**
     * 修改企业LOGO
     *
     * @param partnerName 企业名
     * @param logoUrl   LOGO地址
     * @return 结果
     */
    int updatePartnerLogoUrl(@Param("brandName") String partnerName, @Param("logoUrl") String logoUrl);

    /**
     * 删除合作方信息
     *
     * @param partnerId 合作方信息主键
     * @return 结果
     */
    int deletePartnerById(Long partnerId);

    /**
     * 校验企业名称是否唯一
     *
     * @param partnerName 企业名称
     * @return 结果
     */
    SysUser checkPartnerNameUnique(String partnerName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phone 手机号码
     * @return 结果
     */
    SysUser checkPhoneUnique(String phone);
}
