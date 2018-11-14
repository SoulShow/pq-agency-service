package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyUserStudent;

import java.util.List;

public interface AgencyUserStudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyUserStudent record);

    AgencyUserStudent selectByPrimaryKey(Long id);

    List<AgencyUserStudent> selectAll();

    int updateByPrimaryKey(AgencyUserStudent record);
}