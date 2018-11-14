package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyClassNotice;

import java.util.List;

public interface AgencyClassNoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyClassNotice record);

    AgencyClassNotice selectByPrimaryKey(Long id);

    List<AgencyClassNotice> selectAll();

    int updateByPrimaryKey(AgencyClassNotice record);
}