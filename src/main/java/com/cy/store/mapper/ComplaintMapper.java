package com.cy.store.mapper;

import com.cy.store.entity.Complaint;
import com.cy.store.entity.Focus;

import java.util.List;

public interface ComplaintMapper {

    Integer insert(Complaint complaint);

    Integer setHandled(Integer cptid);

    Complaint findByCptid(Integer cptid);

    List<Complaint> findAllComplaint();
}
