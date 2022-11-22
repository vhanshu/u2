package com.u2.business.enterprise.service.impl;

import com.u2.api.enterprise.domain.EntPurchase;
import com.u2.api.enterprise.domain.EntPurchaseItem;
import com.u2.business.enterprise.dao.EntPurchaseItemMapper;
import com.u2.business.enterprise.dao.EntPurchaseMapper;
import com.u2.business.enterprise.service.EntPurchaseService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 采购单信息Service业务层处理
 *
 * @author vhans
 * @date 2022-05-28
 */
@Service
public class EntPurchaseServiceImpl implements EntPurchaseService {
    @Resource
    private EntPurchaseMapper entPurchaseMapper;

    @Resource
    private EntPurchaseItemMapper entPurchaseItemMapper;

    @Override
    public EntPurchase selectPurchaseById(Long id) {
        return entPurchaseMapper.selectPurchaseById(id);
    }

    @Override
    public EntPurchase selectPurchaseBySn(String sn) {
        return entPurchaseMapper.selectPurchaseBySn(sn);
    }

    @Override
    public List<EntPurchase> selectPurchaseList(EntPurchase entPurchaseAll) {
        return entPurchaseMapper.selectPurchaseList(entPurchaseAll);
    }

    @Override
    public int insertPurchase(EntPurchase entPurchaseAll) {
        return entPurchaseMapper.insertPurchase(entPurchaseAll);
    }

    @Override
    public int updatePurchase(EntPurchase entPurchaseAll) {
        return entPurchaseMapper.updatePurchase(entPurchaseAll);
    }

    @Override
    public int deletePurchaseById(Long id) {
        return entPurchaseMapper.deletePurchaseById(id);
    }

    @Override
    public String checkSnUnique(EntPurchase purchase) {
        String sn = StringUtils.isNull(purchase.getSn()) ? "P-2020-00-00-00000000" : purchase.getSn();
        EntPurchase info = entPurchaseMapper.checkSnUnique(sn);
        if(StringUtils.isNotNull(info) && !Objects.equals(info.getSn(), sn)){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public List<EntPurchaseItem> selectPurchaseItemList(Long purchaseId) {
        return entPurchaseItemMapper.selectPurchaseItemListByPurchaseId(purchaseId);
    }
}
