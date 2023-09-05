package com.cy.store.mapper;

import com.cy.store.entity.Orders;
import com.cy.store.vo.OrdersVO;

import java.util.List;

public interface OrdersMapper {
    Integer insert(Orders orders);
    Orders findByGid(Integer gid);
    OrdersVO findOrdersVOByOid(Integer oid);
    List<OrdersVO> findOrdersVOBySellid(String sellid);
    List<OrdersVO> findOrdersVOByBuyid(String buyid);
    List<OrdersVO> findConsignmentOrdersVO();
    List<OrdersVO> findAllOrdersVO();
    Integer setDelivered(Integer oid);
    Integer setReceived(Integer oid);
    Integer setInComplaint(Integer oid);
    Integer setRefund(Integer oid);
}
