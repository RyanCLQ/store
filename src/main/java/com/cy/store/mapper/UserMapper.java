package com.cy.store.mapper;

import com.cy.store.entity.User;

import java.util.List;


public interface UserMapper {

    Integer insert(User user);

    User findByUsername(String username);

    User findByUid(String uid);

    Integer updatePwdByUid(String uid, String pwd);//返回影响的行数

    Integer updateNumByUid(String uid,Integer change);

    List<User> findAllUser();

    Integer updatePoint(String uid,Integer change);

    Integer setFrozen(String uid);
}
