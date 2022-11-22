package com.u2.business.enterprise.dao;

import com.u2.api.enterprise.domain.EntOrder;

import java.util.List;

/**
 * 订单信息Mapper接口
 *
 * @author vhans
 * @date 2022-05-26
 */
public interface EntOrderMapper {
    /**
     * 根据ID查询订单信息
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
     * 根据条件分页查询订单列表
     *
     * @param order 订单信息
     * @return 订单信息集合信息
     */
    List<EntOrder> selectOrderList(EntOrder order);

    /**
     * 根据会员账号查询订单列表
     *
     * @param memberName 会员账号
     * @return 订单列表
     */
    List<EntOrder> selectOrderByMemberName(String  memberName);

    /**
     * 根据会员ID查询订单列表
     *
     * @param memberId 会员ID
     * @return 订单列表
     */
    List<EntOrder> selectOrderByMemberId(Long  memberId);

    /**
     * 新增订单信息
     *
     * @param order 订单信息
     * @return 结果
     */
    int insertOrder(EntOrder order);

    /**
     * 修改订单信息
     *
     * @param order 订单信息
     * @return 结果
     */
    int updateOrder(EntOrder order);

    /**
     * 删除订单信息
     *
     * @param orderId 订单信息主键
     * @return 结果
     */
    int deleteOrderById(Long orderId);

    /**
     * 校验订单编号是否唯一
     *
     * @param sn 订单编号
     * @return 结果
     */
    EntOrder checkSnUnique(String sn);
}
