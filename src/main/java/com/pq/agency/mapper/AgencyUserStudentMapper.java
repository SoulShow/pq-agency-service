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

    AgencyUserStudent selectByAgencycClassIdAndStudentIdAndRelation(@Param("agencyClassId") Long agencyClassId,
                                                  @Param("studentId")Long studentId,@Param("relation")String relation);
}