package com.pq.agency.mapper;

import com.pq.agency.entity.ClassNoticeFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassNoticeFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassNoticeFile record);

    ClassNoticeFile selectByPrimaryKey(Long id);

    List<ClassNoticeFile> selectAll();

    int updateByPrimaryKey(ClassNoticeFile record);

    List<ClassNoticeFile> selectByNoticeId(@Param("noticeId") Long noticeId);
}