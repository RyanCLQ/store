package com.cy.store.mapper;

import com.cy.store.entity.Address;

import java.util.List;

public interface AddressMapper {
    Integer insert(Address address);
    Integer countByUid(String uid);
    List<Address> findByUid(String uid);
    Address findByAdsid(Integer adsid);
    Integer updateNonDef(String uid);
    Integer updateDefByAdsid(Integer adsid);
    Integer deleteByAdsid(Integer adsid);
    Address findLastAddress(String uid);
}
