package com.u2.business.enterprise.service.impl;

import com.u2.api.enterprise.domain.EntBrand;
import com.u2.api.enterprise.domain.EntPartner;
import com.u2.api.system.domain.SysUser;
import com.u2.business.enterprise.dao.EntBrandMapper;
import com.u2.business.enterprise.dao.EntPartnerMapper;
import com.u2.business.enterprise.service.EntPartnerService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.utils.StringUtils;
import com.u2.common.security.utils.SecurityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 合作方信息Service业务层处理
 *
 * @author vhans
 * @date 2022-05-28
 */
@Service
public class EntPartnerServiceImpl implements EntPartnerService {
    @Resource
    private EntPartnerMapper entPartnerMapper;

    @Resource
    private EntBrandMapper entBrandMapper;

    @Override
    public EntPartner selectPartnerByPartnerId(Long partnerId) {
        return entPartnerMapper.selectPartnerByPartnerId(partnerId);
    }

    @Override
    public EntPartner selectPartnerByPartnerName(String partnerName) {
        return entPartnerMapper.selectPartnerByPartnerName(partnerName);
    }

    @Override
    public List<EntPartner> selectPartnerList(EntPartner entPartner) {
        return entPartnerMapper.selectPartnerList(entPartner);
    }

    @Override
    public int insertPartner(EntPartner entPartner) {
        return entPartnerMapper.insertPartner(entPartner);
    }

    @Override
    public boolean registerPartner(EntPartner partner) {
        return entPartnerMapper.insertPartner(partner) > 0;
    }

    @Override
    public int updatePartner(EntPartner entPartner) {
        return entPartnerMapper.updatePartner(entPartner);
    }

    @Override
    public int resetPwd(EntPartner entPartner) {
        return entPartnerMapper.updatePartner(entPartner);
    }

    @Override
    public int resetUserPwd(@Param("userName") String userName, @Param("password") String password) {
        return entPartnerMapper.resetUserPwd(userName, password);
    }

    @Override
    public int updatePartnerLogoUrl(@Param("brandName") String partnerName, @Param("logoUrl") String logoUrl) {
        return entPartnerMapper.updatePartnerLogoUrl(partnerName, logoUrl);
    }

    @Override
    public int deletePartnerById(Long partnerId) {
        return entPartnerMapper.deletePartnerById(partnerId);
    }

    @Override
    public String checkPartnerNameUnique(EntPartner entPartner) {
        long partnerId = StringUtils.isNull(entPartner.getPartnerId()) ? -1L : entPartner.getPartnerId();
        SysUser info = entPartnerMapper.checkPartnerNameUnique(entPartner.getPartnerName());
        if (StringUtils.isNotNull(info) && info.getUserId() != partnerId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkPhoneUnique(EntPartner entPartner) {
        long partnerId = StringUtils.isNull(entPartner.getPartnerId()) ? -1L : entPartner.getPartnerId();
        SysUser info = entPartnerMapper.checkPhoneUnique(entPartner.getPhone());
        if (StringUtils.isNotNull(info) && info.getUserId() != partnerId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public List<EntBrand> selectBrandList(Long partnerId) {
        return entBrandMapper.selectBrandListByPartnerId(partnerId);
    }
}
