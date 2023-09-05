package com.cy.store.mapper;

import com.cy.store.entity.Focus;

import java.util.List;

public interface FocusMapper {
    Integer insert(Focus focus);
    Integer ifExit(String uid ,Integer gid);
    Integer[] findGidsByUid(String uid);
    Integer delete(Focus focus);
}
