package com.u2.business.enterprise.service.impl;

import com.u2.api.enterprise.domain.EntProduct;
import com.u2.business.enterprise.dao.EntProductMapper;
import com.u2.business.enterprise.service.EntProductService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.utils.DateUtils;
import com.u2.common.core.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品信息Service业务层处理
 *
 * @author vhans
 * @date 2022-04-11
 */
@Service
public class EntProductServiceImpl implements EntProductService {
    @Resource
    private EntProductMapper entProductMapper;

    @Override
    public EntProduct selectProductById(Long id) {
        return entProductMapper.selectProductById(id);
    }

    @Override
    public EntProduct selectProductByProductName(String productName) {
        return entProductMapper.selectProductByProductName(productName);
    }

    @Override
    public List<EntProduct> selectProductList(EntProduct entProduct) {
        return entProductMapper.selectProductList(entProduct);
    }

    @Override
    public List<EntProduct> selectProductListByCategoryId(Long categoryId) {
        return entProductMapper.selectProductListByCategoryId(categoryId);
    }

    @Override
    public int insertProduct(EntProduct entProduct) {
        return entProductMapper.insertProduct(entProduct);
    }

    @Override
    public int updateProduct(EntProduct entProduct) {
        return entProductMapper.updateProduct(entProduct);
    }

    @Override
    public int deleteProductByIds(Long[] ids) {
        return entProductMapper.deleteProductByIds(ids);
    }

    @Override
    public int deleteProductById(Long id) {
        return entProductMapper.deleteProductById(id);
    }

    @Override
    public String checkProductNameUnique(EntProduct entProduct) {
        long entProductId = StringUtils.isNull(entProduct.getProductId()) ? -1L : entProduct.getProductId();
        EntProduct info = entProductMapper.checkProductNameUnique(entProduct.getProductName());
        if (StringUtils.isNotNull(info) && info.getProductId() != entProductId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
