package com.cy.store.service;

import com.cy.store.entity.Complaint;

import java.util.List;

public interface IComplaintService {

    void addComplaint(Complaint complaint);

    List<Complaint> getAllComplaint();

    void setHandled(Integer cptid);

    Complaint getComplaintByCptid(Integer cptid);
}
