package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.Goods;
import com.cy.store.entity.Orders;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.GoodsMapper;
import com.cy.store.mapper.OrdersMapper;
import com.cy.store.service.IOrdersService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.OrderNotFoundException;
import com.cy.store.service.ex.OrdersStatusException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import com.cy.store.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IOrdersServiceImpl implements IOrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public Float createByGid(Integer adsid, String uid, Integer gid) {
        Goods goods = goodsMapper.findByGid(gid);
        Address address = addressMapper.findByAdsid(adsid);
        Orders orders = new Orders();
        orders.setContent(address.getContent());
        orders.setName(address.getName());
        orders.setPhone(address.getPhone());
        System.out.println("phone!!!!!!!!!!!!"+address.getPhone());
        orders.setSellid(goods.getUid());
        orders.setTag(goods.getTag());
        orders.setGid(goods.getGid());
        orders.setPrice(goods.getPrice());
        orders.setBuyid(uid);
        orders.setCtime(new Date());
        orders.setStatus(0);
        System.out.println(orders.toString());
        Integer rows = ordersMapper.insert(orders);
        if (rows!=1){
            throw new InsertException("插入订单"+goods.getGname()+"时发生未知错误");
        }
        //创建订单后，删除数据库中所有该商品的购物车记录，并把该商品的状态设置为下架
        cartMapper.delByGid(gid);
        goodsMapper.setOff(gid);
        return orders.getPrice();
    }

    @Override
    public Float createByCtids(Integer adsid, String uid, Integer[] ctids) {
        Float total_price = Float.valueOf(0);
        List<CartVO> list = cartMapper.findVOByCtid(ctids);
        for (CartVO cartVO :list) {
          total_price+=createByGid(adsid,uid,cartVO.getGid());
        }
        return total_price;
    }

    @Override
    public Orders getOrderByGid(Integer gid) {
        Orders data = ordersMapper.findByGid(gid);
        if (data ==null){
            throw new OrderNotFoundException("该商品订单不存在！");
        }
        return data;
    }

    @Override
    public OrdersVO getOrderVOByOid(Integer oid) {
        return ordersMapper.findOrdersVOByOid(oid);
    }

    @Override
    public List<OrdersVO> getOrderBySellid(String sellid) {
        List<OrdersVO> list = ordersMapper.findOrdersVOBySellid(sellid);
        if (list.size()==0){
            return null;
        }
        return list;
    }

    @Override
    public List<OrdersVO> getOrderByBuyid(String buyid) {
        List<OrdersVO> list = ordersMapper.findOrdersVOByBuyid(buyid);
        if (list.size()==0){
            return null;
        }
        return list;
    }

    @Override
    public List<OrdersVO> getConsignmentOrder() {
        List<OrdersVO> list = ordersMapper.findConsignmentOrdersVO();
        if (list.size()==0){
            return null;
        }
        return list;

    }

    @Override
    public List<OrdersVO> getAllOrder() {
        List<OrdersVO> list = ordersMapper.findAllOrdersVO();
        if (list.size()==0){
            return null;
        }
        return list;
    }

    @Override
    public void setDelivered(Integer oid) {
        OrdersVO ordersVO = ordersMapper.findOrdersVOByOid(oid);
        if (ordersVO.getStatus()!=0){
            throw new OrdersStatusException("订单状态不为待发货，无法发货");
        }
        Integer rows = ordersMapper.setDelivered(oid);
        if (rows!=1){
            throw new UpdateException("更新订单产生未知异常");
        }
    }

    @Override
    public void setReceived(Integer oid) {
        OrdersVO ordersVO = ordersMapper.findOrdersVOByOid(oid);
        if (ordersVO.getStatus()==2){
            throw new OrdersStatusException("订单已经确认收货");
        }
        Integer rows = ordersMapper.setReceived(oid);
        if (rows!=1){
            throw new UpdateException("更新订单产生未知异常");
        }
    }

    @Override
    public void setInComplaint(Integer oid) {
        OrdersVO ordersVO = ordersMapper.findOrdersVOByOid(oid);
        if (ordersVO.getStatus()==2){
            throw new OrdersStatusException("订单已经确认收货，无法投诉");
        }
        if (ordersVO.getStatus()==3){
            throw new OrdersStatusException("订单已经投诉过了，无法投诉");
        }
        Integer rows = ordersMapper.setInComplaint(oid);
        if (rows!=1){
            throw new UpdateException("更新订单产生未知异常");
        }
    }

    @Override
    public void setRefund(Integer oid) {
        OrdersVO ordersVO = ordersMapper.findOrdersVOByOid(oid);
        if (ordersVO.getStatus()==4){
            throw new OrdersStatusException("订单已经退款，无法再退款");
        }
       Integer rows = ordersMapper.setRefund(oid);
        if (rows!=1){
            throw new UpdateException("更新订单产生未知异常");
        }
    }

}
