package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyGrade;

import java.util.List;

public interface AgencyGradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyGrade record);

    AgencyGrade selectByPrimaryKey(Long id);

    List<AgencyGrade> selectAll();

    int updateByPrimaryKey(AgencyGrade record);

    List<AgencyGrade> selectValid();
}