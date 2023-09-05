package com.cy.store.mapper;

import com.cy.store.entity.Admin;
import com.cy.store.entity.User;

public interface AdminMapper {
    Integer insert(Admin admin);

    Admin findByUsername(String username);

    Admin findByAid(String aid);

    Admin findSuperAdmin();
}
