package com.cy.store.service;

import com.cy.store.entity.Orders;
import com.cy.store.vo.OrdersVO;

import java.util.List;

public interface IOrdersService {

    Float createByGid(Integer adsid, String uid, Integer gid);

    Float createByCtids(Integer adsid, String uid, Integer[] ctids);

    Orders getOrderByGid(Integer gid);

    OrdersVO getOrderVOByOid(Integer oid);

    List<OrdersVO> getOrderBySellid(String sellid);

    List<OrdersVO> getOrderByBuyid(String buyid);

    List<OrdersVO> getConsignmentOrder();

    List<OrdersVO> getAllOrder();

    void setDelivered(Integer oid);

    void setReceived(Integer oid);

    void setInComplaint(Integer oid);

    void setRefund(Integer oid);

}
