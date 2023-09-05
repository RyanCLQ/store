package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IAddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public void addAddress(Address address) {
        Integer count = addressMapper.countByUid(address.getUid());
        System.out.println(address.toString());
        System.out.println(count);
        if (count >= 5){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }
        if (count == 0){
            address.setStatus(0);
        }else address.setStatus(1);
        address.setCtime(new Date());
        System.out.println(address.toString());
        Integer rows = addressMapper.insert(address);
        if(rows != 1){
            throw new InsertException("在插入地址时发生未知错误");
        }
    }

    @Override
    public List<Address> getByUid(String uid) {
        List<Address> list = addressMapper.findByUid(uid);
        return list;
    }

    @Override
    public void setDef(String uid, Integer adsid) {
        Address result = addressMapper.findByAdsid(adsid);
        if (result==null){
            throw new AddressNotFoundException("地址不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.updateNonDef(uid);
        if (rows<1){
            throw new UpdateException("更新数据产生未知的异常");
        }
        rows = addressMapper.updateDefByAdsid(adsid);
        if (rows!=1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public void delete(Integer adsid, String uid) {
        Address result = addressMapper.findByAdsid(adsid);
        if (result ==null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.deleteByAdsid(adsid);
        if (rows != 1){
            throw  new DeleteException("删除数据产生未知异常");
        }
        //如果删除的是非默认的，不用设置新的默认
        if (result.getStatus() == 1){
            return;
        }
        //如果删除完了，不用设置新的默认
        Integer count = addressMapper.countByUid(uid);
        if (count==0){
            return;
        }
        Address address = addressMapper.findLastAddress(uid);
        rows = addressMapper.updateDefByAdsid(address.getAdsid());
        if (rows != 1){
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }

    @Override
    public Address getByAdsid(Integer adsid, String uid) {
        Address address=addressMapper.findByAdsid(adsid);
        if (address == null) {
            throw new AddressNotFoundException("该收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        return address;
    }
}
