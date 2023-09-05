package com.cy.store.service;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;

import java.util.List;

public interface ICartService {

    void addCart(Cart cart);

    List<CartVO> getVOByUid(String uid);

    List<CartVO> getVOByCtid(String uid,Integer[] ctids);

    void cartItemDel(Integer ctid);

    void delAllCartByGid(Integer gid);
}
