package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyUser;

import java.util.List;

public interface AgencyUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyUser record);

    AgencyUser selectByPrimaryKey(Long id);

    List<AgencyUser> selectAll();

    int updateByPrimaryKey(AgencyUser record);

    List<Long> selectClassIdByUserId(String userId);
}