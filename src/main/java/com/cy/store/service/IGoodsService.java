package com.cy.store.service;

import com.cy.store.entity.Goods;
import com.cy.store.vo.GoodsVO;

import java.util.List;

public interface IGoodsService {
    void addGoods(Goods goods);

    List<Goods> getGoodsByUid(String uid);

    List<Goods> getGoodsExamine();

    List<Goods> getGoodsByCid(Integer cid);

    List<Goods> getNewGoods();

    Goods getGoodsByGid(Integer gid);

    List<Goods> getGoodsByGids(Integer[] gids);

    List<Goods> searchGoodsByGname(String gname);

    void setOn(Integer gid);

    void setOff(Integer gid);

    void updateGoods(Goods goods);

    GoodsVO getVOByGid(Integer gid);
}
