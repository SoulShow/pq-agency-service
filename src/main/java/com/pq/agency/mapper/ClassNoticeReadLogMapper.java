package com.pq.agency.mapper;

import com.pq.agency.entity.ClassNoticeReadLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassNoticeReadLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassNoticeReadLog record);

    ClassNoticeReadLog selectByPrimaryKey(Long id);

    List<ClassNoticeReadLog> selectAll();

    int updateByPrimaryKey(ClassNoticeReadLog record);

    ClassNoticeReadLog selectByUserIdAndNoticeId(@Param("userId") String userId,@Param("noticeId") Long noticeId);

    ClassNoticeReadLog selectByNoticeIdAndStudentId(@Param("noticeId") Long noticeId,@Param("studentId") Long studentId);
}