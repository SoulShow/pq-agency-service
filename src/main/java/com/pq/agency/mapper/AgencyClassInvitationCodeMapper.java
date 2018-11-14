package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyClassInvitationCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyClassInvitationCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyClassInvitationCode record);

    AgencyClassInvitationCode selectByPrimaryKey(Long id);

    List<AgencyClassInvitationCode> selectAll();

    int updateByPrimaryKey(AgencyClassInvitationCode record);

    List<AgencyClassInvitationCode> selectGradeByAgencyId(@Param("agencyId")Long agencyId);

    List<AgencyClassInvitationCode> selectClassByAgencyIdAndGradeId(@Param("agencyId")Long agencyId,
                                                                    @Param("gradeId")Long gradeId);

    AgencyClassInvitationCode selectByAgencyInfo(@Param("agencyId")Long agencyId, @Param("gradeId")Long gradeId,
                              @Param("classId") Long classId);
}