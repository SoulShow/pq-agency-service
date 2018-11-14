package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyClassCourse;

import java.util.List;

public interface AgencyClassCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyClassCourse record);

    AgencyClassCourse selectByPrimaryKey(Long id);

    List<AgencyClassCourse> selectAll();

    int updateByPrimaryKey(AgencyClassCourse record);
}