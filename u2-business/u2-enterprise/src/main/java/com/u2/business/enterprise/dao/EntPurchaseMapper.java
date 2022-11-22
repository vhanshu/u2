package com.u2.business.enterprise.dao;

import com.u2.api.enterprise.domain.EntCategory;
import com.u2.api.enterprise.domain.EntPurchase;

import java.util.List;

/**
 * 采购单信息Mapper接口
 *
 * @author vhans
 * @date 2022-05-28
 */
public interface EntPurchaseMapper {
    /**
     * 根据ID查询采购信息
     *
     * @param id 采购信息主键
     * @return 采购信息
     */
    EntPurchase selectPurchaseById(Long id);

    /**
     * 根据条件分页查询采购列表
     *
     * @param purchase 采购信息
     * @return 采购信息集合信息
     */
    List<EntPurchase> selectPurchaseList(EntPurchase purchase);

    /**
     * 根据采购编号查询信息
     *
     * @param sn 采购编号
     * @return 采购信息
     */
    EntPurchase selectPurchaseBySn(String sn);

    /**
     * 新增采购信息
     *
     * @param entPurchase 采购信息
     * @return 结果
     */
    int insertPurchase(EntPurchase entPurchase);

    /**
     * 修改采购信息
     *
     * @param entPurchase 采购信息
     * @return 结果
     */
    int updatePurchase(EntPurchase entPurchase);

    /**
     * 删除采购信息
     *
     * @param purchaseId 采购信息主键
     * @return 结果
     */
    int deletePurchaseById(Long purchaseId);

    /**
     * 校验采购单编号是否唯一
     *
     * @param sn 订单编号
     * @return 结果
     */
    EntPurchase checkSnUnique(String sn);
}
