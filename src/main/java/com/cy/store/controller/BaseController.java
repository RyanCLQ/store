package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    //操作成功的状态码
    public static final int OK = 200;
    protected final String getUidFromSession(HttpSession session) {
        if (session.getAttribute("uid")==null){
            return null;
        }
        return session.getAttribute("uid").toString();
    }

    protected final String getAidFromSession(HttpSession session) {
        if (session.getAttribute("aid")==null){
            return null;
        }
        return session.getAttribute("aid").toString();
    }
    //对异常进行处理，返回数据给前端
    @ExceptionHandler({ServiceException.class,FileUploadException.class,ControllerException.class})//这个类型的异常就会转到这里
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UidDuplicateException) {
            result.setState(4000);
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
        } else if (e instanceof PwdNotMatchException) {
            result.setState(4002);
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
        } else if (e instanceof AccessDeniedException) {
            result.setState(4004);
        } else if (e instanceof AddressNotFoundException) {
            result.setState(4005);
        } else if (e instanceof AccountFrozenException) {
            result.setState(4006);
        } else if (e instanceof CatelogNotFoundException) {
            result.setState(4007);
        } else if (e instanceof GoodsNotFoundException) {
            result.setState(4008);
        } else if (e instanceof OrderNotFoundException) {
            result.setState(4009);
        } else if (e instanceof OrdersStatusException) {
            result.setState(4010);
        } else if (e instanceof InsertException) {
            result.setState(5000);
        } else if (e instanceof UpdateException) {
            result.setState(5001);
        } else if (e instanceof DeleteException) {
            result.setState(5002);
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
        } else if (e instanceof FileStateException) {
            result.setState(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
        }else if (e instanceof ControllerException) {
            result.setState(7000);
        }
        return result;
    }
}
