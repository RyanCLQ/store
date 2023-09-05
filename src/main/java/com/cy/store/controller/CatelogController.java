package com.cy.store.controller;


import com.cy.store.entity.Catelog;
import com.cy.store.entity.Goods;
import com.cy.store.service.ICatelogService;
import com.cy.store.service.IGoodsService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("catelog")
public class CatelogController extends BaseController{
    @Autowired
    private ICatelogService iCatelogService;
    @RequestMapping("get_all")
    public JsonResult<List<Catelog>> myGoods(){
        List<Catelog> list = iCatelogService.getAllCatelog();
        return new JsonResult<List<Catelog>>(OK,list);
    }
}
