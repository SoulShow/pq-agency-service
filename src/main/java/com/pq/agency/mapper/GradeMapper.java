package com.pq.agency.mapper;

import com.pq.agency.entity.Grade;

import java.util.List;

public interface GradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Grade record);

    Grade selectByPrimaryKey(Long id);

    List<Grade> selectAll();

    int updateByPrimaryKey(Grade record);

    List<Grade> selectValid();

}