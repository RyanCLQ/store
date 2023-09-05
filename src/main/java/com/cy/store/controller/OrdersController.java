package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.Complaint;
import com.cy.store.entity.Goods;
import com.cy.store.service.IComplaintService;
import com.cy.store.service.IGoodsService;
import com.cy.store.service.IOrdersService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orders")
public class OrdersController extends BaseController{
    public static final String filePath = "E:/computer class/store/src/main/resources/static/images/complaint/";
    /** 头像文件大小的上限值(10MB) */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /** 允许上传的头像的文件类型 */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }
    @Autowired
    private IOrdersService ordersService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IComplaintService complaintService;
    @GetMapping("create_in_cart")
    public JsonResult<Float> createInCart(Integer adsid, Integer[] ctids, HttpSession session){
        System.out.println("test create in cart!!!!!!!!!!!"+adsid);
        String uid = getUidFromSession(session);
        float price = ordersService.createByCtids(adsid,uid,ctids);
        System.out.println(price+"!!!!!!!!!!!");
        return new JsonResult<Float>(OK,price);
    }
    @GetMapping("create_in_goods")
    public JsonResult<Float> createInGoods(Integer adsid, Integer gid, HttpSession session){
        String uid = getUidFromSession(session);
        Goods goods = goodsService.getGoodsByGid(gid);
        if (goods.getUid().equals(uid)){
            throw new ControllerException("不能购买自己的商品！！");
        }else {
            float price = ordersService.createByGid(adsid,uid,gid);
            return new JsonResult<Float>(OK,price);
        }
    }
    @RequestMapping("my_sell_orders")
    public JsonResult<List<OrdersVO>> mySellOrders(HttpSession session){
        String sellid = getUidFromSession(session);
        List<OrdersVO> data = ordersService.getOrderBySellid(sellid);
        return new JsonResult<List<OrdersVO>>(OK,data);
    }
    @RequestMapping("my_buy_orders")
    public JsonResult<List<OrdersVO>> myBuyOrders(HttpSession session){
        String buyid = getUidFromSession(session);
        List<OrdersVO> data = ordersService.getOrderByBuyid(buyid);
        return new JsonResult<List<OrdersVO>>(OK,data);
    }
    @RequestMapping("{oid}/set_delivered")
    public JsonResult<Void> setDelivered(@PathVariable("oid") Integer oid){
        ordersService.setDelivered(oid);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("{oid}/set_received")
    public JsonResult<Void> setReceived(@PathVariable("oid") Integer oid){
        ordersService.setReceived(oid);
        return new JsonResult<Void>(OK);
    }
    @PostMapping("add_complaint")
    public JsonResult<Void> addComplaint(@RequestParam("file") MultipartFile file, Complaint complaint){
        System.out.println(complaint.toString());
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的图片文件不允许为空");
        }if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的图片文件");
        }

        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // boolean contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件，允许的文件类型：" + AVATAR_TYPES);
        }

        // 获取当前项目的绝对磁盘路径
        // 保存图像文件的文件夹
        File dir = new File(filePath);
        // 保存的图像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重新尝试");
        }

        String img = "../images/complaint/" + filename;
        System.out.println(img);
        complaint.setImg(img);
        complaintService.addComplaint(complaint);
        // 返回成功头像路径
        return new JsonResult<Void>(OK);

    }
    @RequestMapping("consignment_orders")
    public JsonResult<List<OrdersVO>> consignmentOrders(HttpSession session){
        if (getAidFromSession(session)==null){
            return null;
        }
        List<OrdersVO> data = ordersService.getConsignmentOrder();
        return new JsonResult<List<OrdersVO>>(OK,data);
    }
    @RequestMapping("all_orders")
    public JsonResult<List<OrdersVO>> allOrders(HttpSession session){
        if (getAidFromSession(session)==null){
            return null;
        }
        List<OrdersVO> data = ordersService.getAllOrder();
        return new JsonResult<List<OrdersVO>>(OK,data);
    }
    @RequestMapping("{oid}/info")
    public JsonResult<OrdersVO> ordersInfo(@PathVariable("oid") Integer oid){
        OrdersVO data = ordersService.getOrderVOByOid(oid);
        return new JsonResult<OrdersVO>(OK,data);
    }
}
