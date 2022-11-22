package com.u2.business.enterprise.dao;

import com.u2.api.enterprise.domain.EntBrand;
import com.u2.api.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌信息Mapper接口
 *
 * @author vhans
 * @date 2022-05-28
 */
public interface EntBrandMapper {
    /**
     * 通过品牌名查询品牌
     *
     * @param brandName 品牌名
     * @return 品牌对象信息
     */
    EntBrand selectBrandByBrandName(String brandName);

    /**
     * 通过品牌ID查询品牌
     *
     * @param brandId 品牌ID
     * @return 品牌对象信息
     */
    EntBrand selectBrandById(Long brandId);

    /**
     * 通过所属企业ID查询品牌列表
     *
     * @param partnerId 所属企业ID
     * @return 品牌对象信息
     */
    List<EntBrand> selectBrandListByPartnerId(Long partnerId);

    /**
     * 根据条件分页查询品牌信息列表
     *
     * @param brand 品牌信息
     * @return 品牌信息集合
     */
    List<EntBrand> selectBrandList(EntBrand brand);

    /**
     * 新增品牌信息
     *
     * @param brand 品牌信息
     * @return 结果
     */
    int insertBrand(EntBrand brand);

    /**
     * 修改品牌信息
     *
     * @param brand 品牌信息
     * @return 结果
     */
    int updateBrand(EntBrand brand);

    /**
     * 修改品牌LOGO
     *
     * @param brandName 品牌名
     * @param logoUrl   LOGO地址
     * @return 结果
     */
    int updateBrandLogoUrl(@Param("brandName") String brandName, @Param("logoUrl") String logoUrl);

    /**
     * 通过品牌ID删除品牌信息
     *
     * @param id 品牌信息主键
     * @return 结果
     */
    int deleteBrandById(Long id);

    /**
     * 批量删除品牌信息
     *
     * @param brandIds 需要删除的品牌ID
     * @return 结果
     */
    int deleteBrandByIds(Long[] brandIds);

    /**
     * 校验品牌名称是否唯一
     *
     * @param brandName 品牌名称
     * @return 结果
     */
    EntBrand checkBrandNameUnique(String brandName);

    /**
     * 查询品牌下是否存在商品
     *
     * @param brandId 品牌ID
     * @return 结果
     */
    int checkBrandExistProduct(Long brandId);
}
