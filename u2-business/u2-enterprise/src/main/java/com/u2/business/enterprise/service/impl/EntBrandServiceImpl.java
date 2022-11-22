package com.u2.business.enterprise.service.impl;


import com.u2.api.enterprise.domain.EntBrand;
import com.u2.business.enterprise.dao.EntBrandMapper;
import com.u2.business.enterprise.service.EntBrandService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.utils.DateUtils;
import com.u2.common.core.utils.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌信息Service业务层处理
 *
 * @author vhans
 * @date 2022-05-28
 */
@Service
public class EntBrandServiceImpl implements EntBrandService {
    @Resource
    private EntBrandMapper entBrandMapper;

    @Override
    public EntBrand selectBrandById(Long id) {
        return entBrandMapper.selectBrandById(id);
    }

    @Override
    public EntBrand selectBrandByBrandName(String brandName){
        return entBrandMapper.selectBrandByBrandName(brandName);
    }

    @Override
    public List<EntBrand> selectBrandListByPartnerId(Long partnerId){
        return entBrandMapper.selectBrandListByPartnerId(partnerId);
    }

    @Override
    public List<EntBrand> selectBrandList(EntBrand entBrand) {
        return entBrandMapper.selectBrandList(entBrand);
    }

    @Override
    public int insertBrand(EntBrand entBrand) {
        entBrand.setCreateTime(DateUtils.getNowDate());
        return entBrandMapper.insertBrand(entBrand);
    }

    @Override
    public int updateBrand(EntBrand entBrand) {
        entBrand.setUpdateTime(DateUtils.getNowDate());
        return entBrandMapper.updateBrand(entBrand);
    }

    public int updateBrandLogoUrl(@Param("brandName") String brandName, @Param("logoUrl") String logoUrl){
        return entBrandMapper.updateBrandLogoUrl(brandName, logoUrl);
    }

    @Override
    public int deleteBrandByIds(Long[] ids) {
        return entBrandMapper.deleteBrandByIds(ids);
    }

    @Override
    public int deleteBrandById(Long id) {
        return entBrandMapper.deleteBrandById(id);
    }

    @Override
    public String  checkBrandNameUnique(EntBrand brand){
        long brandId = StringUtils.isNull(brand.getBrandId()) ? -1L : brand.getBrandId();
        EntBrand info = entBrandMapper.checkBrandNameUnique(brand.getBrandName());
        if (StringUtils.isNotNull(info) && info.getBrandId() != brandId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int checkBrandExistProduct(Long brandId) {
        return entBrandMapper.checkBrandExistProduct(brandId);
    }
}
