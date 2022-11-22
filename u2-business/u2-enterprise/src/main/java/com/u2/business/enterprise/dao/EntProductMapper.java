package com.u2.business.enterprise.dao;

import com.u2.api.enterprise.domain.EntCategory;
import com.u2.api.enterprise.domain.EntProduct;

import java.util.List;

/**
 * 商品信息Mapper接口
 *
 * @author ego
 * @date 2022-04-11
 */
public interface EntProductMapper {
    /**
     * 通过商品名查询商品信息
     *
     * @param productName 商品名
     * @return 商品对象信息
     */
    EntProduct selectProductByProductName(String productName);

    /**
     * 通过商品类别查询商品列表
     *
     * @param categoryId 类别ID
     * @return 商品对象列表
     */
    List<EntProduct> selectProductListByCategoryId(Long categoryId);

    /**
     * 通过商品ID查询商品
     *
     * @param productId 商品ID
     * @return 商品对象信息
     */
    EntProduct selectProductById(Long productId);

    /**
     * 根据条件分页查询商品信息列表
     *
     * @param product 商品信息
     * @return 商品信息集合
     */
    List<EntProduct> selectProductList(EntProduct product);

    /**
     * 新增商品信息
     *
     * @param product 商品信息
     * @return 结果
     */
    int insertProduct(EntProduct product);

    /**
     * 修改商品信息
     *
     * @param product 商品信息
     * @return 结果
     */
    int updateProduct(EntProduct product);

    /**
     * 删除商品信息
     *
     * @param productId 商品信息主键
     * @return 结果
     */
    int deleteProductById(Long productId);

    /**
     * 批量删除商品信息
     *
     * @param productIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteProductByIds(Long[] productIds);

    /**
     * 校验商品名称是否唯一
     *
     * @param productName 商品名称
     * @return 结果
     */
    EntProduct checkProductNameUnique(String productName);
}
