package com.cy.store.mapper;

import com.cy.store.entity.Complaint;
import com.cy.store.entity.Notice;

public interface NoticeMapper {
    Integer insert(Notice notice);
    Notice findNewNotice();
}
