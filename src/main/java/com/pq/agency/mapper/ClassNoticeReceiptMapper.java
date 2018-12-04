package com.pq.agency.mapper;

import com.pq.agency.entity.ClassNoticeReceipt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassNoticeReceiptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassNoticeReceipt record);

    ClassNoticeReceipt selectByPrimaryKey(Long id);

    List<ClassNoticeReceipt> selectAll();

    int updateByPrimaryKey(ClassNoticeReceipt record);

    ClassNoticeReceipt selectByNoticeIdAndUserIdAndStudentId(@Param("noticeId") Long noticeId,
                                                             @Param("userId") String userId,
                                                             @Param("studentId") Long studentId);
}