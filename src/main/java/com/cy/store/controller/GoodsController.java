package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.Address;
import com.cy.store.entity.Goods;
import com.cy.store.entity.User;
import com.cy.store.service.IFocusService;
import com.cy.store.service.IGoodsService;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.GoodsVO;
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
@RequestMapping("goods")
public class GoodsController extends BaseController{
    public static final String filePath = "E:/computer class/store/src/main/resources/static/images/goods/";
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
    private IGoodsService iGoodsService;
    @Autowired
    private IFocusService iFocusService;
    @PostMapping("add_goods")
    public JsonResult<Void> addGoods(@RequestParam("file") MultipartFile file, HttpSession session, Goods goods){
        System.out.println(goods.toString());
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

        // 头像路径
        String img = "../images/goods/" + filename;
        System.out.println(img);
        goods.setImg(img);
        goods.setUid(getUidFromSession(session));
        iGoodsService.addGoods(goods);
        // 返回成功头像路径
        return new JsonResult<Void>(OK);

    }
    @RequestMapping("my_goods")
    public JsonResult<List<Goods>> myGoods(HttpSession session){
        String uid = getUidFromSession(session);
        List<Goods> list = iGoodsService.getGoodsByUid(uid);
        return new JsonResult<List<Goods>>(OK,list);
    }
    @RequestMapping("goods_examine")
    public JsonResult<List<Goods>> goodsExamine(HttpSession session){
        if (getAidFromSession(session)==null){
            throw new ControllerException("管理员尚未登录");
        }
        List<Goods> list = iGoodsService.getGoodsExamine();
        return new JsonResult<List<Goods>>(OK,list);
    }
    @RequestMapping("catelog/{cid}")
    public JsonResult<List<Goods>> getByCid(@PathVariable("cid") Integer cid){
        List<Goods> list = iGoodsService.getGoodsByCid(cid);
        return new JsonResult<List<Goods>>(OK,list);
    }
    @RequestMapping("new_goods")
    public JsonResult<List<Goods>> newGoods(){
        List<Goods> list = iGoodsService.getNewGoods();
        return new JsonResult<List<Goods>>(OK,list);
    }
    @GetMapping("{gid}/details")
    public JsonResult<GoodsVO> getGoodsVOByGid(@PathVariable("gid") Integer gid){
        GoodsVO data = iGoodsService.getVOByGid(gid);
        return new JsonResult<GoodsVO>(OK, data);
    }
    @GetMapping("{gid}")
    public JsonResult<Goods> getGoodsByGid(@PathVariable("gid") Integer gid){
        System.out.println(gid);
        Goods data = iGoodsService.getGoodsByGid(gid);
        System.out.println(data.toString());
        return new JsonResult<Goods>(OK, data);
    }
    @RequestMapping("search_goods")
    public JsonResult<List<Goods>> searchGoods(String gname){
        System.out.println(gname);
        List<Goods>data = iGoodsService.searchGoodsByGname(gname);
        System.out.println(data.get(0).toString());
        return new JsonResult<List<Goods>>(OK,data);
    }
    @RequestMapping("add_focus")
    public JsonResult<Void> addFocus(Integer gid, HttpSession session){
        String uid = getUidFromSession(session);
        iFocusService.addFocus(uid,gid);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("focus_list")
    public JsonResult<List<Goods>> focusList(HttpSession session){
        String uid = getUidFromSession(session);
        Integer[] gids = iFocusService.findGidsByUid(uid);
        System.out.println(gids.toString());
        List<Goods>data = iGoodsService.getGoodsByGids(gids);
        return new JsonResult<List<Goods>>(OK,data);
    }
    @RequestMapping("delete_focus")
    public JsonResult<Void> deleteFocus(Integer gid, HttpSession session){
        String uid = getUidFromSession(session);
        iFocusService.deleteFocus(uid,gid);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("{gid}/set_on")
    public JsonResult<Void> setOn(@PathVariable("gid") Integer gid){
        iGoodsService.setOn(gid);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("{gid}/set_off")
    public JsonResult<Void> setOff(@PathVariable("gid") Integer gid){
        iGoodsService.setOff(gid);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("update_goods")
    public JsonResult<Void> updateGoods(Goods goods){
        System.out.println("!!!!!"+goods.toString());
        iGoodsService.updateGoods(goods);
        return new JsonResult<Void>(OK);
    }
}
