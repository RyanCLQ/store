package com.cy.store.service;

import com.cy.store.entity.Catelog;

import java.util.List;

public interface ICatelogService {

    List<Catelog> getAllCatelog();

    void updateNumByCid(Integer cid, Integer change);

}
