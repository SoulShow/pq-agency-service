package com.pq.agency.mapper;

import com.pq.agency.entity.ClassCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassCourse record);

    ClassCourse selectByPrimaryKey(Long id);

    List<ClassCourse> selectAll();

    int updateByPrimaryKey(ClassCourse record);

    List<ClassCourse> selectByClassId(@Param("classId")Long classId);
}