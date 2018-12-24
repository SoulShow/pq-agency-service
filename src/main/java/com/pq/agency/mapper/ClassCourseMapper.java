package com.pq.agency.mapper;

import com.pq.agency.entity.ClassCourse;

import java.util.List;

public interface ClassCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassCourse record);

    ClassCourse selectByPrimaryKey(Long id);

    List<ClassCourse> selectAll();

    int updateByPrimaryKey(ClassCourse record);
}