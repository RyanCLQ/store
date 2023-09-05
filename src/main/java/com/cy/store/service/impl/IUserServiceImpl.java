package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String uid = user.getUid();
        User result = userMapper.findByUid(uid);
        if(result != null){
            throw new UidDuplicateException("该学号已注册");
        }
        //密码加密处理 md5 盐值+密码+盐值 连续加载3次
        String oldPwd = user.getPwd();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Pwd = getMD5Pwd(oldPwd, salt);
        // 补全数据
        user.setPwd(md5Pwd);
        user.setSalt(salt);
        Date date = new Date();
        user.setCtime(date);
        user.setGnum(0);
        user.setPoint(100);
        user.setStatus(0);

        Integer rows = userMapper.insert(user);
        if(rows != 1){
            throw new InsertException("在用户注册时发生未知错误");
        }
    }
    @Override
    public User login(String uid, String pwd){
        User result = userMapper.findByUid(uid);
        if (result == null){
            throw new UserNotFoundException("用户数据不存在");
        }
        String salt = result.getSalt();
        String pwdInDb = result.getPwd();
        String newPwd = getMD5Pwd(pwd,salt);
        if (!newPwd.equals(pwdInDb)){
            throw new PwdNotMatchException("用户密码错误");
        }
        if(result.getStatus()==1){
            throw new AccountFrozenException("用户账号已被冻结");
        }
        return result;
    }

    @Override
    public void changePwd(String uid, String oldPwd, String newPwd) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getStatus()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldMd5Pwd = getMD5Pwd(oldPwd, result.getSalt());
        if (!result.getPwd().equals(oldMd5Pwd)){
            throw new PwdNotMatchException("密码错误");
        }
        String newMd5Pwd = getMD5Pwd(newPwd,result.getSalt());
        Integer rows = userMapper.updatePwdByUid(uid, newMd5Pwd);
        if (rows != 1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public User getUserByUid(String uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getStatus()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        return result;
    }

    @Override
    public void updateNumByUid(String uid, Integer change) {
        Integer rows = userMapper.updateNumByUid(uid,change);
        if(rows != 1){
            throw new UpdateException("在更新数据时发生未知错误");
        }

    }

    @Override
    public void updatePointByUid(String uid, Integer change) {
        Integer rows = userMapper.updatePoint(uid,change);
        if(rows != 1){
            throw new UpdateException("在更新数据时发生未知错误");
        }
        User user = getUserByUid(uid);
        if (user.getPoint()<=0){
            userMapper.setFrozen(uid);
        }
    }

    @Override
    public List<User> allUser() {
        List<User> data = userMapper.findAllUser();
        if (data.size()==0){
            throw new UserNotFoundException("用户数据不存在");
        }
        return data;
    }

    /**定义一个md5算法的加密处理*/
    private String getMD5Pwd(String pwd, String salt){
        for(int i = 0 ; i < 3 ; i++ ) {
            pwd = DigestUtils.md5DigestAsHex((salt + pwd + salt).getBytes()).toUpperCase();
        }
        return pwd;
    }
}
