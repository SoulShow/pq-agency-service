package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyGradeShow;

import java.util.List;

public interface AgencyGradeShowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyGradeShow record);

    AgencyGradeShow selectByPrimaryKey(Long id);

    List<AgencyGradeShow> selectAll();

    int updateByPrimaryKey(AgencyGradeShow record);
}