package com.cy.store.service.impl;

import com.cy.store.entity.Goods;
import com.cy.store.entity.User;
import com.cy.store.mapper.GoodsMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.ICatelogService;
import com.cy.store.service.IGoodsService;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import com.cy.store.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class IGoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ICatelogService iCatelogService;
    @Autowired
    private IUserService iUserService;
    @Override
    public void addGoods(Goods goods) {
        Date date = new Date();
        goods.setCtime(date);
        goods.setStatus(0);
        Integer rows = goodsMapper.insert(goods);
        if(rows != 1){
            throw new InsertException("在插入商品时发生未知错误");
        }
    }

    @Override
    public List<Goods> getGoodsByUid(String uid) {
        List<Goods> data = goodsMapper.findByUid(uid);
        if (data == null){
            throw new GoodsNotFoundException("商品数据不存在");
        }
        return data;
    }

    @Override
    public List<Goods> getGoodsExamine() {
        List<Goods> data = goodsMapper.findGoodsExamine();
        if (data == null){
            throw new GoodsNotFoundException("商品数据不存在");
        }
        return data;
    }

    @Override
    public List<Goods> getGoodsByCid(Integer cid) {
        List<Goods> data = goodsMapper.findByCid(cid);
        if (data == null){
            throw new GoodsNotFoundException("商品数据不存在");
        }
        return data;
    }

    @Override
    public List<Goods> getNewGoods() {
        return goodsMapper.findNewGoods();
    }

    @Override
    public Goods getGoodsByGid(Integer gid) {
        return goodsMapper.findByGid(gid);
    }

    @Override
    public List<Goods> getGoodsByGids(Integer[] gids) {
        if (gids.length==0){
            return null;//还没有收藏的商品
        }
        return goodsMapper.findByGids(gids);
    }

    @Override
    public List<Goods> searchGoodsByGname(String gname) {
        String name = "%"+gname+"%";//模糊搜索
        return goodsMapper.searchByGname(name);
    }

    @Override
    public void setOn(Integer gid) {
        Integer rows = goodsMapper.setOn(gid);
        if (rows!=1){
            throw new UpdateException("更新商品产生未知异常");
        }
        //新商品审核通过后会调用
        Goods goods= goodsMapper.findByGid(gid);
        iCatelogService.updateNumByCid(goods.getCid(),1);
        iUserService.updateNumByUid(goods.getUid(),1);
    }
    @Override
    public void setOff(Integer gid) {
        Integer rows = goodsMapper.setOff(gid);
        if (rows!=1){
            throw new UpdateException("更新商品产生未知异常");
        }
        Goods goods= goodsMapper.findByGid(gid);
        iCatelogService.updateNumByCid(goods.getCid(),-1);
        iUserService.updateNumByUid(goods.getUid(),-1);
    }

    @Override
    public void updateGoods(Goods goods) {


        Integer rows = goodsMapper.updateGoods(goods);
        if (rows!=1){
            throw new UpdateException("更新商品产生未知异常");
        }
        //更新后修改分类的商品数，不用，因为是在下架的时候修改的
//        Goods goods1= goodsMapper.findByGid(goods.getGid());
//        if (!goods.getCid().equals(goods1.getCid())) {
//            iCatelogService.updateNumByCid(goods1.getCid(), -1);
//            iCatelogService.updateNumByCid(goods.getCid(), 1);
//        }
    }

    @Override
    public GoodsVO getVOByGid(Integer gid) {
        GoodsVO data = goodsMapper.findVOByGid(gid);
        if (data==null){
            throw new GoodsNotFoundException("商品数据不存在");
        }
        return data;
    }


}
