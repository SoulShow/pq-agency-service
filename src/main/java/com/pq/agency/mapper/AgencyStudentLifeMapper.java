package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyStudentLife;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyStudentLifeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyStudentLife record);

    AgencyStudentLife selectByPrimaryKey(Long id);

    List<AgencyStudentLife> selectAll();

    int updateByPrimaryKey(AgencyStudentLife record);

    List<AgencyStudentLife> selectByStudentIdAndAgencyClassId(@Param("studentId")Long studentId,
                                                              @Param("agencyClassId")Long agencyClassId,
                                                              @Param("offset")int offset,
                                                              @Param("size") int size);
}