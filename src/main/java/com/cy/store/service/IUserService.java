package com.cy.store.service;

import com.cy.store.entity.User;

import java.util.List;

public interface IUserService {
    void reg(User user);

    User login(String uid, String pwd);

    void changePwd(String uid, String oldPwd, String newPwd);

    User getUserByUid(String uid);

    void updateNumByUid(String uid, Integer change);

    void updatePointByUid(String uid, Integer change);

    List<User> allUser();
}
