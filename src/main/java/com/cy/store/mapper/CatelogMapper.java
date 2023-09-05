package com.cy.store.mapper;

import com.cy.store.entity.Catelog;

import java.util.List;

public interface CatelogMapper {
    List<Catelog> getAllCatelog();
    Integer updateNumByCid(Integer cid,Integer change);
}
