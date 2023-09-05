package com.cy.store.service;

import com.cy.store.entity.Notice;

public interface INoticeService {
    void addNotice(Notice notice);
    Notice getNewNotice();
}
