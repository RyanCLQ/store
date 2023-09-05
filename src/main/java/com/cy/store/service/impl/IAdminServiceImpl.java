package com.cy.store.service.impl;

import com.cy.store.entity.Admin;
import com.cy.store.entity.User;
import com.cy.store.mapper.AdminMapper;
import com.cy.store.service.IAdminService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class IAdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public void reg(Admin admin, String superAid, String superPwd) {
        String aid = admin.getAid();
        Admin result = adminMapper.findByAid(aid);
        if(result != null){
            throw new UidDuplicateException("该学号已注册");
        }
        Admin superAdmin = adminMapper.findSuperAdmin();
        if (!superAdmin.getAid().equals(superAid)||!superAdmin.getPwd().equals(superPwd)){
            throw new UserNotFoundException("超级管理员账号或密码错误");
        }
        //密码加密处理 md5 盐值+密码+盐值 连续加载3次
        String oldPwd = admin.getPwd();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Pwd = getMD5Pwd(oldPwd, salt);
        // 补全数据
        admin.setPwd(md5Pwd);
        admin.setSalt(salt);
        admin.setRole(0);

        Integer rows = adminMapper.insert(admin);
        if(rows != 1){
            throw new InsertException("在管理员注册时发生未知错误");
        }
    }

    @Override
    public Admin login(String aid, String pwd) {
        Admin result = adminMapper.findByAid(aid);
        if (result == null){
            throw new UserNotFoundException("管理员数据不存在");
        }
        String salt = result.getSalt();
        String pwdInDb = result.getPwd();
        String newPwd = getMD5Pwd(pwd,salt);
        if (!newPwd.equals(pwdInDb)){
            throw new PwdNotMatchException("管理员密码错误");
        }
        return result;
    }

    @Override
    public Admin getAdminByAid(String aid) {
        Admin result = adminMapper.findByAid(aid);
        if (result == null){
            throw new UserNotFoundException("管理员数据不存在");
        }
        return result;
    }
    private String getMD5Pwd(String pwd, String salt){
        for(int i = 0 ; i < 3 ; i++ ) {
            pwd = DigestUtils.md5DigestAsHex((salt + pwd + salt).getBytes()).toUpperCase();
        }
        return pwd;
    }
}
