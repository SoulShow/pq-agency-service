package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyClass;

import java.util.List;

public interface AgencyClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyClass record);

    AgencyClass selectByPrimaryKey(Long id);

    List<AgencyClass> selectAll();

    int updateByPrimaryKey(AgencyClass record);
}