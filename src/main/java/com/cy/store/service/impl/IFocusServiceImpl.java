package com.cy.store.service.impl;

import com.cy.store.entity.Focus;
import com.cy.store.mapper.FocusMapper;
import com.cy.store.service.IFocusService;
import com.cy.store.service.ex.DeleteException;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IFocusServiceImpl implements IFocusService {
    @Autowired
    private FocusMapper focusMapper;
    @Override
    public void addFocus(String uid, Integer gid) {
        Integer count = focusMapper.ifExit(uid,gid);
        if (count>=1){
            throw new InsertException("商品已被该用户收藏");
        }
        Focus focus = new Focus();
        focus.setUid(uid);
        focus.setGid(gid);
        Integer rows = focusMapper.insert(focus);
        if (rows != 1){
            throw new InsertException("插入收藏数据时发生未知错误");
        }
    }

    @Override
    public Integer[] findGidsByUid(String uid) {
        return focusMapper.findGidsByUid(uid);
    }

    @Override
    public void deleteFocus(String uid, Integer gid) {
        Integer count = focusMapper.ifExit(uid,gid);
        if (count<1){
            throw new DeleteException("商品已未该用户收藏");
        }
        Focus focus = new Focus();
        focus.setUid(uid);
        focus.setGid(gid);
        Integer rows = focusMapper.delete(focus);
        if (rows != 1){
            throw new DeleteException("删除收藏数据时发生未知错误");
        }
    }
}
