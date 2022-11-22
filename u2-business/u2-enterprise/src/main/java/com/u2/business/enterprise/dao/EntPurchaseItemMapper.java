package com.u2.business.enterprise.dao;

import com.u2.api.enterprise.domain.EntPurchaseItem;

import java.util.List;

/**
 * 采购子项信息Mapper接口
 *
 * @author vhans
 * @date 2022-05-28
 */
public interface EntPurchaseItemMapper {
    /**
     * 查询采购子项信息
     *
     * @param id 采购子项信息主键
     * @return 采购子项信息
     */
    EntPurchaseItem selectPurchaseItemById(Long id);

    /**
     * 查询采购子项信息列表
     *
     * @param purchaseItem 采购子项信息
     * @return 采购子项信息集合
     */
    List<EntPurchaseItem> selectPurchaseItemList(EntPurchaseItem purchaseItem);

    /**
     * 根据所属采购ID查询采购子项信息列表
     *
     * @param purchaseId 所属采购ID
     * @return 采购子项信息集合
     */
    List<EntPurchaseItem> selectPurchaseItemListByPurchaseId(Long purchaseId);

    /**
     * 根据所属采购编号查询采购子项信息列表
     *
     * @param sn 所属采购sn
     * @return 采购子项信息集合
     */
    List<EntPurchaseItem> selectPurchaseItemListBySn(String  sn);

    /**
     * 新增采购子项信息
     *
     * @param purchaseItem 采购子项信息
     * @return 结果
     */
    int insertPurchaseItem(EntPurchaseItem purchaseItem);

    /**
     * 修改采购子项信息
     *
     * @param purchaseItem 采购子项信息
     * @return 结果
     */
    int updatePurchaseItem(EntPurchaseItem purchaseItem);

    /**
     * 删除采购子项信息
     *
     * @param id 采购子项信息主键
     * @return 结果
     */
    int deletePurchaseItemById(Long id);

    /**
     * 批量删除采购子项信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deletePurchaseItemByIds(Long[] ids);
}
