package com.cy.store.controller;

import com.cy.store.controller.ex.ControllerException;
import com.cy.store.entity.Cart;
import com.cy.store.entity.Goods;
import com.cy.store.service.ICartService;
import com.cy.store.service.IGoodsService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController extends BaseController{
    @Autowired
    private ICartService cartService;
    @Autowired
    private IGoodsService goodsService;
    @RequestMapping("add_cart")
    public JsonResult<Void> addCart(Integer gid, HttpSession session){
        Goods goods=goodsService.getGoodsByGid(gid);
        if (goods.getUid().equals(getUidFromSession(session))){
            throw new ControllerException("不能把自己的商品加入购物车！！");
        }else {
            Cart cart = new Cart();
            cart.setGid(gid);
            cart.setUid(getUidFromSession(session));
            cartService.addCart(cart);
            return new JsonResult<Void>(OK);
        }
    }
    @GetMapping({"","/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
        List<CartVO> data = cartService.getVOByUid(getUidFromSession(session));
        return new JsonResult<List<CartVO>> (OK,data);
    }
    @GetMapping("list")
    public JsonResult<List<CartVO>> getVOByCtid(Integer[] ctids,HttpSession session){
        List<CartVO> data = cartService.getVOByCtid(getUidFromSession(session),ctids);
        return new JsonResult<List<CartVO>> (OK,data);
    }
    @RequestMapping("{ctid}/del")
    public JsonResult<Void> cartItemDel(@PathVariable("ctid") Integer ctid){
        cartService.cartItemDel(ctid);
        return new JsonResult<Void>(OK);
    }
}
