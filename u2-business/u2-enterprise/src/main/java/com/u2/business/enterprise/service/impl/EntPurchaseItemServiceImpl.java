package com.u2.business.enterprise.service.impl;

import com.u2.api.enterprise.domain.EntPurchaseItem;
import com.u2.business.enterprise.dao.EntPurchaseItemMapper;
import com.u2.business.enterprise.service.EntPurchaseItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 采购子项信息Service业务层处理
 *
 * @author vhans
 * @date 2022-05-28
 */
@Service
public class EntPurchaseItemServiceImpl implements EntPurchaseItemService {
    @Resource
    private EntPurchaseItemMapper entPurchaseItemMapper;

    /**
     * 查询采购子项信息
     *
     * @param id 采购子项信息主键
     * @return 采购子项信息
     */
    @Override
    public EntPurchaseItem selectPurchaseItemById(Long id) {
        return entPurchaseItemMapper.selectPurchaseItemById(id);
    }

    /**
     * 查询采购子项信息列表
     *
     * @param entPurchaseItem 采购子项信息
     * @return 采购子项信息
     */
    @Override
    public List<EntPurchaseItem> selectPurchaseItemList(EntPurchaseItem entPurchaseItem) {
        return entPurchaseItemMapper.selectPurchaseItemList(entPurchaseItem);
    }

    @Override
    public List<EntPurchaseItem> selectPurchaseItemListByPurchaseId(Long purchaseId) {
        return entPurchaseItemMapper.selectPurchaseItemListByPurchaseId(purchaseId);
    }

    @Override
    public List<EntPurchaseItem> selectPurchaseItemListBySn(String sn) {
        return entPurchaseItemMapper.selectPurchaseItemListBySn(sn);
    }

    /**
     * 新增采购子项信息
     *
     * @param entPurchaseItem 采购子项信息
     * @return 结果
     */
    @Override
    public int insertPurchaseItem(EntPurchaseItem entPurchaseItem) {
        return entPurchaseItemMapper.insertPurchaseItem(entPurchaseItem);
    }

    /**
     * 修改采购子项信息
     *
     * @param entPurchaseItem 采购子项信息
     * @return 结果
     */
    @Override
    public int updatePurchaseItem(EntPurchaseItem entPurchaseItem) {
        return entPurchaseItemMapper.updatePurchaseItem(entPurchaseItem);
    }

    /**
     * 批量删除采购子项信息
     *
     * @param ids 需要删除的采购子项信息主键
     * @return 结果
     */
    @Override
    public int deletePurchaseItemByIds(Long[] ids) {
        return entPurchaseItemMapper.deletePurchaseItemByIds(ids);
    }

    /**
     * 删除采购子项信息信息
     *
     * @param id 采购子项信息主键
     * @return 结果
     */
    @Override
    public int deletePurchaseItemById(Long id) {
        return entPurchaseItemMapper.deletePurchaseItemById(id);
    }
}
