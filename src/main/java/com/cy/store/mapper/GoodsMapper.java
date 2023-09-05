package com.cy.store.mapper;

import com.cy.store.entity.Goods;
import com.cy.store.entity.User;
import com.cy.store.vo.CartVO;
import com.cy.store.vo.GoodsVO;

import java.util.List;

public interface GoodsMapper {

    Integer insert(Goods goods);

    List<Goods> findByUid(String uid);

    List<Goods> findGoodsExamine();

    List<Goods> findByCid(Integer cid);

    List<Goods> findNewGoods();

    Goods findByGid(Integer gid);

    List<Goods> findByGids(Integer[] gids);

    List<Goods> searchByGname(String gname);

    Integer setOn(Integer gid);

    Integer setOff(Integer gid);

    Integer  updateGoods(Goods goods);

    GoodsVO findVOByGid(Integer gid);
}
