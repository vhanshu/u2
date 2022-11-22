package com.u2.business.enterprise.service;

import com.u2.api.enterprise.domain.EntBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌信息Service接口
 *
 * @author vhans
 * @date 2022-05-28
 */
public interface EntBrandService {
    /**
     * 查询品牌信息
     *
     * @param id 品牌信息主键
     * @return 品牌信息
     */
    EntBrand selectBrandById(Long id);

    /**
     * 通过品牌名查询品牌
     *
     * @param brandName 品牌名
     * @return 品牌对象信息
     */
    EntBrand selectBrandByBrandName(String brandName);

    /**
     * 通过所属企业ID查询品牌列表
     *
     * @param partnerId 所属企业ID
     * @return 品牌对象信息
     */
    List<EntBrand> selectBrandListByPartnerId(Long partnerId);

    /**
     * 根据条件分页查询品牌汇总信息列表
     *
     * @param entBrand 品牌信息
     * @return 品牌信息集合
     */
    List<EntBrand> selectBrandList(EntBrand entBrand);

    /**
     * 新增品牌信息
     *
     * @param entBrand 品牌信息
     * @return 结果
     */
    int insertBrand(EntBrand entBrand);

    /**
     * 修改品牌信息
     *
     * @param entBrand 品牌信息
     * @return 结果
     */
    int updateBrand(EntBrand entBrand);

    /**
     * 修改品牌LOGO
     *
     * @param brandName 品牌名
     * @param logoUrl   LOGO地址
     * @return 结果
     */
    int updateBrandLogoUrl(@Param("brandName") String brandName, @Param("logoUrl") String logoUrl);


    /**
     * 批量删除品牌信息
     *
     * @param ids 需要删除的品牌信息主键集合
     * @return 结果
     */
    int deleteBrandByIds(Long[] ids);

    /**
     * 删除品牌信息信息
     *
     * @param id 品牌信息主键
     * @return 结果
     */
    int deleteBrandById(Long id);

    /**
     * 校验品牌是否唯一
     *
     * @param brand 品牌
     * @return 结果
     */
    String checkBrandNameUnique(EntBrand brand);

    /**
     * 查询品牌下是否存在商品
     *
     * @param brandId 品牌ID
     * @return 结果
     */
    int checkBrandExistProduct(Long brandId);
}
