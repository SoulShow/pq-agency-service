package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyClass record);

    AgencyClass selectByPrimaryKey(Long id);

    List<AgencyClass> selectAll();

    int updateByPrimaryKey(AgencyClass record);

    List<AgencyClass> selectGradeByAgencyId(@Param("agencyId")Long agencyId);

    List<AgencyClass> selectClassByAgencyIdAndGradeId(@Param("agencyId")Long agencyId,
                                                      @Param("gradeId")Long gradeId);

    AgencyClass selectByAgencyInfo(@Param("agencyId")Long agencyId, @Param("gradeId")Long gradeId,@Param("classId")Long classId);


}