package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;

import java.util.List;

public interface CartMapper {
    Integer insert(Cart cart);
    Cart findByUidAndGid(String uid,Integer gid);
    List<CartVO> findVOByUid(String uid);
    List<CartVO> findVOByCtid(Integer[] ctids);
    Integer delByCtid(Integer ctid);
    Integer delByGid(Integer gid);
}
