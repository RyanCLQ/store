package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Goods;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.GoodsMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.DeleteException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ICartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public void addCart(Cart cart) {
        Cart result = cartMapper.findByUidAndGid(cart.getUid(),cart.getGid());
        if (result != null){
            throw new InsertException("该商品已在购物车中");
        }
        Goods goods = goodsMapper.findByGid(cart.getGid());
        cart.setPrice(goods.getPrice());
        cart.setCtime(new Date());
        Integer rows = cartMapper.insert(cart);
        if (rows != 1){
            throw new InsertException("插入数据时产生未知的异常");
        }

    }

    @Override
    public List<CartVO> getVOByUid(String uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public List<CartVO> getVOByCtid(String uid, Integer[] ctids) {
        List<CartVO> list = cartMapper.findVOByCtid(ctids);
        Iterator<CartVO> it = list.iterator();//迭代器,用于遍历list检查是否有不是当前用户的记录
        while (it.hasNext()){
            CartVO cartVO = it.next();//要next一下才指向第一个
            if (!cartVO.getUid().equals(uid)){
                list.remove(cartVO);
            }

        } return list;
    }

    @Override
    public void cartItemDel(Integer ctid) {
        Integer rows = cartMapper.delByCtid(ctid);
        if (rows!=1){
            throw new DeleteException("删除购物车记录时产生未知错误!!");
        }
    }

    @Override
    public void delAllCartByGid(Integer gid) {
        Integer rows = cartMapper.delByGid(gid);
        if (rows!=1){
            throw new DeleteException("删除购物车记录时产生未知错误!!");
        }
    }


}
