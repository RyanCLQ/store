package com.cy.store.service.impl;

import com.cy.store.entity.Notice;
import com.cy.store.mapper.NoticeMapper;
import com.cy.store.service.INoticeService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class INoticeServiceImpl implements INoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public void addNotice(Notice notice) {
        Date date = new Date();
        notice.setCtime(date);
        Integer rows = noticeMapper.insert(notice);
        if (rows!=1){
            throw new InsertException("插入公告发生未知错误");
        }
    }

    @Override
    public Notice getNewNotice() {
        Notice data = noticeMapper.findNewNotice();
        if (data==null){
            throw new ServiceException("找不到公告数据");
        }
        return data;
    }
}
