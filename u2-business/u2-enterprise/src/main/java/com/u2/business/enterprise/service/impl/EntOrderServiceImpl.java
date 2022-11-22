package com.u2.business.enterprise.service.impl;

import com.u2.api.enterprise.domain.EntOrder;
import com.u2.api.enterprise.domain.EntOrderItem;
import com.u2.business.enterprise.dao.EntOrderItemMapper;
import com.u2.business.enterprise.dao.EntOrderMapper;
import com.u2.business.enterprise.service.EntOrderService;
import com.u2.common.core.constant.UserConstants;
import com.u2.common.core.utils.DateUtils;
import com.u2.common.core.utils.SpringUtils;
import com.u2.common.core.utils.StringUtils;
import com.u2.common.core.web.domain.model.AjaxResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 订单信息Service业务层处理
 *
 * @author vhans
 * @date 2022-05-26
 */
@Service
public class EntOrderServiceImpl implements EntOrderService {
    @Resource
    private EntOrderMapper entOrderMapper;

    @Resource
    private EntOrderItemMapper entOrderItemMapper;

    @Override
    public EntOrder selectOrderById(Long orderId) {
        return entOrderMapper.selectOrderById(orderId);
    }

    public EntOrder selectOrderBySn(String sn) {
        return entOrderMapper.selectOrderBySn(sn);
    }

    @Override
    public List<EntOrder> selectOrderList(EntOrder entOrder) {
        return entOrderMapper.selectOrderList(entOrder);
    }

    @Override
    public List<EntOrder> selectOrderByMemberName(String memberName) {
        return entOrderMapper.selectOrderByMemberName(memberName);
    }

    @Override
    public int insertOrder(EntOrder entOrder) {
        return entOrderMapper.insertOrder(entOrder);
    }

    @Override
    public int updateOrder(EntOrder entOrder) {
        return entOrderMapper.updateOrder(entOrder);
    }

    @Override
    public int deleteOrderById(Long orderId) {
        return entOrderMapper.deleteOrderById(orderId);
    }

    @Override
    public String checkSnUnique(EntOrder order) {
        String sn = StringUtils.isNull(order.getSn()) ? "O-2020-00-00-00000000" : order.getSn();
        EntOrder info = entOrderMapper.checkSnUnique(sn);
        if(StringUtils.isNotNull(info) && !Objects.equals(info.getSn(), sn)){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public List<EntOrderItem> selectOrderItemList(Long orderId) {
        return entOrderItemMapper.selectOrderItemListByOrderId(orderId);
    }
}
