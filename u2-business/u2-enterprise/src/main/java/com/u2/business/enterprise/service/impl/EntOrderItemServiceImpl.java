package com.u2.business.enterprise.service.impl;

import com.u2.api.enterprise.domain.EntOrderItem;
import com.u2.business.enterprise.dao.EntOrderItemMapper;
import com.u2.business.enterprise.service.EntOrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单子项信息Service业务层处理
 *
 * @author vhans
 * @date 2022-05-26
 */
@Service
public class EntOrderItemServiceImpl implements EntOrderItemService {
    @Resource
    private EntOrderItemMapper entOrderItemMapper;

    @Override
    public EntOrderItem selectOrderItemById(Long id) {
        return entOrderItemMapper.selectOrderItemById(id);
    }

    @Override
    public List<EntOrderItem> selectOrderItemList(EntOrderItem entOrderItem) {
        return entOrderItemMapper.selectOrderItemList(entOrderItem);
    }

    @Override
    public List<EntOrderItem> selectOrderItemListByOrderId(Long orderId) {
        return entOrderItemMapper.selectOrderItemListByOrderId(orderId);
    }

    @Override
    public List<EntOrderItem> selectOrderItemListBySn(String sn) {
        return entOrderItemMapper.selectOrderItemListBySn(sn);
    }

    @Override
    public int insertOrderItem(EntOrderItem entOrderItem) {
        return entOrderItemMapper.insertOrderItem(entOrderItem);
    }

    @Override
    public int updateOrderItem(EntOrderItem entOrderItem) {
        return entOrderItemMapper.updateOrderItem(entOrderItem);
    }

    @Override
    public int deleteOrderItemByIds(Long[] ids) {
        return entOrderItemMapper.deleteOrderItemByIds(ids);
    }
    
    @Override
    public int deleteOrderItemById(Long id) {
        return entOrderItemMapper.deleteOrderItemById(id);
    }
}
