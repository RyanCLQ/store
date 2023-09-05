package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UidDuplicateException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String uid, String pwd, HttpSession session){
        User data=userService.login(uid,pwd);
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username",data.getUsername());
        //session.setAttribute("phone",data.getPhone());
        //session.setAttribute("point",data.getPoint());
        //session.setAttribute("gnum",data.getGnum());
        System.out.println(getUidFromSession(session));
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_pwd")
    public JsonResult<Void> change_pwd(String oldPwd, String newPwd, HttpSession session){
        String uid = getUidFromSession(session);
        userService.changePwd(uid,oldPwd,newPwd);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("get_user_by_uid")
    public JsonResult<User> getUserByUid(HttpSession session){
        String uid = getUidFromSession(session);
        User data = userService.getUserByUid(uid);
        return new JsonResult<User>(OK,data);
    }
    @RequestMapping("all_user")
    public JsonResult<List<User>> allUser(HttpSession session){
        if (getAidFromSession(session)==null){
            return null;
        }
        List<User> data = userService.allUser();
        return new JsonResult<List<User>>(OK,data);
    }

}
