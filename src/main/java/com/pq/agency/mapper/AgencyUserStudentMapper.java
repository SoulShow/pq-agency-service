package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyUserStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyUserStudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyUserStudent record);

    AgencyUserStudent selectByPrimaryKey(Long id);

    List<AgencyUserStudent> selectAll();

    int updateByPrimaryKey(AgencyUserStudent record);

    List<AgencyUserStudent> selectByUserId(@Param("userId") String userId);

    List<AgencyUserStudent> selectByAgencyClassIdAndStudentId(@Param("agencyClassId") Long agencyClassId,
                                                         @Param("studentId")Long studentId);

    AgencyUserStudent selectByUserIdAndStudentId(@Param("userId") String userId, @Param("studentId")Long studentId);

    List<AgencyUserStudent> selectByAgencyClassIdAndUserId(@Param("agencyClassId") Long agencyClassId,
                                                           @Param("userId") String userId);
}