package com.u2.business.enterprise.service;

import com.u2.api.enterprise.domain.EntCategory;
import com.u2.api.enterprise.domain.EntOrderItem;
import com.u2.api.enterprise.domain.EntPurchase;
import com.u2.api.enterprise.domain.EntPurchaseItem;

import java.util.List;

/**
 * 采购单信息Service接口
 *
 * @author vhans
 * @date 2022-05-28
 */
public interface EntPurchaseService {
    /**
     * 查询采购单信息
     *
     * @param id 采购单信息主键
     * @return 采购单信息
     */
    EntPurchase selectPurchaseById(Long id);

    /**
     * 根据采购编号查询信息
     *
     * @param sn 采购编号
     * @return 采购信息
     */
    EntPurchase selectPurchaseBySn(String sn);

    /**
     * 查询采购单信息列表
     *
     * @param entPurchase 采购单信息
     * @return 采购单信息集合
     */
    List<EntPurchase> selectPurchaseList(EntPurchase entPurchase);

    /**
     * 新增采购单信息
     *
     * @param entPurchase 采购单信息
     * @return 结果
     */
    int insertPurchase(EntPurchase entPurchase);

    /**
     * 修改采购单信息
     *
     * @param entPurchase 采购单信息
     * @return 结果
     */
    int updatePurchase(EntPurchase entPurchase);

    /**
     * 删除采购单信息信息
     *
     * @param id 采购单信息主键
     * @return 结果
     */
    int deletePurchaseById(Long id);

    /**
     * 校验采购单编号是否唯一
     *
     * @param purchase 采购单
     * @return 结果
     */
    String checkSnUnique(EntPurchase purchase);

    /**
     * 根据采购单ID查询采购单子项列表
     *
     * @param purchaseId 采购单ID
     * @return 采购单子项列表
     */
    List<EntPurchaseItem> selectPurchaseItemList(Long purchaseId);
}
