package com.cy.store.service;

import com.cy.store.entity.Admin;
import com.cy.store.entity.User;

public interface IAdminService {
    void reg(Admin admin, String superAid, String superPwd);

    Admin login(String aid, String pwd);

    Admin getAdminByAid(String aid);

}
