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

    AgencyClassInvitationCode selectByAgencyClassId(@Param("agencyClassId")Long agencyClassId);
}