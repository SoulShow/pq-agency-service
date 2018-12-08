package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyGroup record);

    AgencyGroup selectByPrimaryKey(Long id);

    List<AgencyGroup> selectAll();

    int updateByPrimaryKey(AgencyGroup record);

    List<AgencyGroup> selectByClassId(@Param("classId")Long classId);
}