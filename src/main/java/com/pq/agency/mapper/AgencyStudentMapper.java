package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyStudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyStudent record);

    AgencyStudent selectByPrimaryKey(Long id);

    List<AgencyStudent> selectAll();

    int updateByPrimaryKey(AgencyStudent record);

    List<AgencyStudent> selectByAgencyClassIdAndName(@Param("agencyClassId")Long agencyClassId,@Param("name")String studentName);
}