package com.pq.agency.mapper;

import com.pq.agency.entity.ClassNoticeReceipt;

import java.util.List;

public interface ClassNoticeReceiptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassNoticeReceipt record);

    ClassNoticeReceipt selectByPrimaryKey(Long id);

    List<ClassNoticeReceipt> selectAll();

    int updateByPrimaryKey(ClassNoticeReceipt record);
}