package com.cy.store.service;

import com.cy.store.entity.Focus;

public interface IFocusService {

    void addFocus(String uid ,Integer gid);

    Integer[] findGidsByUid(String uid);

    void deleteFocus(String uid, Integer gid);

}
