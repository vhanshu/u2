package com.u2.business.enterprise.service;

import com.u2.api.enterprise.domain.EntOrder;
import com.u2.api.enterprise.domain.EntOrderItem;

import java.util.List;

/**
 * 订单信息Service接口
 *
 * @author vhans
 * @date 2022-05-26
 */
public interface EntOrderService {
    /**
     * 查询订单信息
     *
     * @param orderId 订单信息主键
     * @return 订单信息
     */
    EntOrder selectOrderById(Long orderId);

    /**
     * 根据订单编号查询信息
     *
     * @param sn 订单编号
     * @return 订单信息
     */
    EntOrder selectOrderBySn(String sn);

    /**
     * 查询订单信息列表
     *
     * @param entOrder 订单信息
     * @return 订单信息集合
     */
    List<EntOrder> selectOrderList(EntOrder entOrder);

    /**
     * 根据会员账号查询订单列表
     *
     * @param memberName 会员账号
     * @return 订单列表
     */
    List<EntOrder> selectOrderByMemberName(String memberName);

    /**
     * 新增订单信息
     *
     * @param entOrder 订单信息
     * @return 结果
     */
    int insertOrder(EntOrder entOrder);

    /**
     * 修改订单信息
     *
     * @param entOrder 订单信息
     * @return 结果
     */
    int updateOrder(EntOrder entOrder);

    /**
     * 删除订单信息信息
     *
     * @param orderId 订单信息主键
     * @return 结果
     */
    int deleteOrderById(Long orderId);

    /**
     * 校验订单编号是否唯一
     *
     * @param order 订单
     * @return 结果
     */
    String checkSnUnique(EntOrder order);

    /**
     * 根据订单ID查询订单子项列表
     *
     * @param orderId 订单ID
     * @return 订单信息
     */
    List<EntOrderItem> selectOrderItemList(Long orderId);
}
