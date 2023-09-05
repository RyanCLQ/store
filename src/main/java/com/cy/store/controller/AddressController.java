package com.cy.store.controller;

import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_address")
    public JsonResult<Void> addAddress(Address address, HttpSession session){
        String uid = getUidFromSession(session);
        address.setUid(uid);
        System.out.println(address.toString());
        addressService.addAddress(address);
        System.out.println(address.toString()+"!!!!!!!!!!!!!!");
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("get_by_uid")
    public JsonResult<List<Address>> getByUid(HttpSession session){
        String uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<List<Address>>(OK,data);
    }
    @RequestMapping("{adsid}/set_def")
    public JsonResult<Void> setDef(@PathVariable("adsid") Integer adsid, HttpSession session){
        String uid = getUidFromSession(session);
        addressService.setDef(uid,adsid);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("{adsid}/delete")
    public JsonResult<Void> delete(@PathVariable("adsid") Integer adsid, HttpSession session){
        String uid = getUidFromSession(session);
        addressService.delete(adsid,uid);
        return new JsonResult<Void>(OK);
    }


}
