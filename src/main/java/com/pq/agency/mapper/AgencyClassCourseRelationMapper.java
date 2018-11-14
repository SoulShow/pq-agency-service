package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyClassCourseRelation;

import java.util.List;

public interface AgencyClassCourseRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyClassCourseRelation record);

    AgencyClassCourseRelation selectByPrimaryKey(Long id);

    List<AgencyClassCourseRelation> selectAll();

    int updateByPrimaryKey(AgencyClassCourseRelation record);
}