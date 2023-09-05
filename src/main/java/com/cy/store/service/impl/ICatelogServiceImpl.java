package com.cy.store.service.impl;

import com.cy.store.entity.Catelog;
import com.cy.store.mapper.CatelogMapper;
import com.cy.store.service.ICatelogService;
import com.cy.store.service.ex.CatelogNotFoundException;
import com.cy.store.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ICatelogServiceImpl implements ICatelogService {
    @Autowired
    private CatelogMapper catelogMapper;
    @Override
    public List<Catelog> getAllCatelog() {
        List<Catelog> result= catelogMapper.getAllCatelog();
        if (result == null){
            throw new CatelogNotFoundException("分类数据不存在");
        } return result;
    }

    @Override
    public void updateNumByCid(Integer cid, Integer change) {
        Integer rows = catelogMapper.updateNumByCid(cid,change);
        if(rows != 1){
            throw new UpdateException("在更新数据时发生未知错误");
        }
    }

}
