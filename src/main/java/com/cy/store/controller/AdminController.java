package com.cy.store.controller;

import com.cy.store.controller.ex.ControllerException;
import com.cy.store.entity.Admin;
import com.cy.store.entity.Complaint;
import com.cy.store.entity.Notice;
import com.cy.store.entity.User;
import com.cy.store.service.IAdminService;
import com.cy.store.service.IComplaintService;
import com.cy.store.service.INoticeService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController extends BaseController{
    @Autowired
    private IAdminService adminService;
    @Autowired
    private INoticeService noticeService;
    @Autowired
    private IComplaintService complaintService;
    @RequestMapping("reg")
    public JsonResult<Void> reg(Admin admin, String superAid, String superPwd){
        adminService.reg(admin,superAid,superPwd);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("login")
    public JsonResult<Admin> login(String aid, String pwd, HttpSession session){
        System.out.println("!!!!!"+aid+"  "+pwd);
        Admin data=adminService.login(aid,pwd);
        session.setAttribute("aid", data.getAid());
        return new JsonResult<Admin>(OK, data);
    }
    @RequestMapping("get_admin_by_aid")
    public JsonResult<Admin> getAdminByAid(HttpSession session){
        String aid = getAidFromSession(session);
        if (aid==null){
            throw new ControllerException("管理员尚未登录");
        }
        Admin data = adminService.getAdminByAid(aid);
        return new JsonResult<Admin>(OK,data);
    }
    @RequestMapping("add_notice")
    public JsonResult<Void> addNotice(Notice notice, HttpSession session){
        String aid = getAidFromSession(session);
        if (aid==null){
            throw new ControllerException("管理员尚未登录");
        }
        notice.setAid(aid);
        noticeService.addNotice(notice);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("get_new_notice")
    public JsonResult<Notice> getNewNotice(){
        Notice data = noticeService.getNewNotice();
        return new JsonResult<Notice>(OK,data);
    }
    @RequestMapping("all_complaint")
    public JsonResult<List<Complaint>> allComplaint(HttpSession session){
        if (getAidFromSession(session)==null){
            throw new ControllerException("管理员尚未登录");
        }
        List<Complaint> data = complaintService.getAllComplaint();
        return new JsonResult<List<Complaint>>(OK,data);
    }
    @RequestMapping("{cptid}/set_handled")
    public JsonResult<Void> setDef(@PathVariable("cptid") Integer cptid,HttpSession session){
        if (getAidFromSession(session)==null){
            throw new ControllerException("管理员尚未登录");
        }
        complaintService.setHandled(cptid);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("{cptid}/info")
    public JsonResult<Complaint> complaintInfo(@PathVariable("cptid") Integer cptid){
        Complaint data = complaintService.getComplaintByCptid(cptid);
        return new JsonResult<Complaint>(OK,data);
    }

}
