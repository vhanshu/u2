package com.u2.business.enterprise.service;

import com.u2.api.enterprise.domain.EntProduct;

import java.util.List;

/**
 * 商品信息Service接口
 *
 * @author ego
 * @date 2022-04-11
 */
public interface EntProductService {
    /**
     * 查询商品信息
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    EntProduct selectProductById(Long id);

    /**
     * 通过商品名查询商品信息
     *
     * @param productName 商品名
     * @return 商品对象信息
     */
    EntProduct selectProductByProductName(String productName);

    /**
     * 查询商品信息列表
     *
     * @param entProduct 商品信息
     * @return 商品信息集合
     */
    List<EntProduct> selectProductList(EntProduct entProduct);

    /**
     * 通过商品类别查询商品列表
     *
     * @param categoryId 类别ID
     * @return 商品对象列表
     */
    List<EntProduct> selectProductListByCategoryId(Long categoryId);

    /**
     * 新增商品信息
     *
     * @param entProduct 商品信息
     * @return 结果
     */
    int insertProduct(EntProduct entProduct);

    /**
     * 修改商品信息
     *
     * @param entProduct 商品信息
     * @return 结果
     */
    int updateProduct(EntProduct entProduct);

    /**
     * 批量删除商品信息
     *
     * @param ids 需要删除的商品信息主键集合
     * @return 结果
     */
    int deleteProductByIds(Long[] ids);

    /**
     * 删除商品信息信息
     *
     * @param id 商品信息主键
     * @return 结果
     */
    int deleteProductById(Long id);

    /**
     * 校验商品名称是否唯一
     *
     * @param entProduct 商品
     * @return 结果
     */
    String checkProductNameUnique(EntProduct entProduct);
}
