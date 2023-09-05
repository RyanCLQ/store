package com.cy.store.service.impl;

import com.cy.store.entity.Complaint;
import com.cy.store.entity.Orders;
import com.cy.store.entity.User;
import com.cy.store.mapper.ComplaintMapper;
import com.cy.store.mapper.OrdersMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IComplaintService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.ServiceException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IComplaintServiceImpl implements IComplaintService {
    @Autowired
    private ComplaintMapper complaintMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public void addComplaint(Complaint complaint) {
        Date date = new Date();
        complaint.setCtime(date);
        complaint.setStatus(0);
        Integer rows = complaintMapper.insert(complaint);
        if (rows!=1){
            throw new InsertException("创建投诉记录时发生未知错误");
        }
        ordersMapper.setInComplaint(complaint.getOid());
    }

    @Override
    public List<Complaint> getAllComplaint() {
        List<Complaint> data = complaintMapper.findAllComplaint();
        if (data.size()==0){
            throw new ServiceException("查无投诉记录");
        }
        return data;
    }

    @Override
    public void setHandled(Integer cptid) {
        Integer rows = complaintMapper.setHandled(cptid);
        if (rows!=1){
            throw new UpdateException("更新投诉记录出现未知错误");
        }
        Complaint complaint = complaintMapper.findByCptid(cptid);
        ordersMapper.setRefund(complaint.getOid());//订单设置成退款中
        OrdersVO ordersVO = ordersMapper.findOrdersVOByOid(complaint.getOid());
        userMapper.updatePoint(ordersVO.getSellid(),-20);
    }

    @Override
    public Complaint getComplaintByCptid(Integer cptid) {
        return complaintMapper.findByCptid(cptid);
    }
}
