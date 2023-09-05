package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

public interface IAddressService {

    void addAddress(Address address);

    List<Address> getByUid(String uid);

    void setDef(String uid,Integer adsid);

    void delete(Integer adsid,String uid);

    Address getByAdsid(Integer adsid, String uid);
}
