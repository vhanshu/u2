package com.u2.business.enterprise.service;

import com.u2.api.enterprise.domain.EntBrand;
import com.u2.api.enterprise.domain.EntPartner;
import com.u2.api.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 合作方信息Service接口
 *
 * @author vhans
 * @date 2022-05-28
 */
public interface EntPartnerService {
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
     * @param partnerName 企业名称
     * @return 合作方信息
     */
    EntPartner selectPartnerByPartnerName(String partnerName);

    /**
     * 查询合作方信息列表
     *
     * @param entPartner 合作方信息
     * @return 合作方信息集合
     */
    List<EntPartner> selectPartnerList(EntPartner entPartner);

    /**
     * 新增合作方信息
     *
     * @param entPartner 合作方信息
     * @return 结果
     */
    int insertPartner(EntPartner entPartner);

    /**
     * 注册企业信息
     *
     * @param partner 企业信息
     * @return 结果
     */
    boolean registerPartner(EntPartner partner);

    /**
     * 修改合作方信息
     *
     * @param entPartner 合作方信息
     * @return 结果
     */
    int updatePartner(EntPartner entPartner);

    /**
     * 重置合作者密码
     *
     * @param entPartner 合作者信息
     * @return 结果
     */
    int resetPwd(EntPartner entPartner);

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
     * @param logoUrl     LOGO地址
     * @return 结果
     */
    int updatePartnerLogoUrl(@Param("brandName") String partnerName, @Param("logoUrl") String logoUrl);

    /**
     * 删除合作方信息信息
     *
     * @param partnerId 合作方信息主键
     * @return 结果
     */
    int deletePartnerById(Long partnerId);

    /**
     * 校验企业名称是否唯一
     *
     * @param entPartner 企业
     * @return 结果
     */
    String checkPartnerNameUnique(EntPartner entPartner);

    /**
     * 校验手机号码是否唯一
     *
     * @param entPartner 企业
     * @return 结果
     */
    String checkPhoneUnique(EntPartner entPartner);

    /**
     * 查询合作方所有品牌列表
     *
     * @param partnerId 合作方ID
     * @return 合作方所有品牌列表
     */
    List<EntBrand> selectBrandList(Long partnerId);
}
