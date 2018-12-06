package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyUser record);

    AgencyUser selectByPrimaryKey(Long id);

    List<AgencyUser> selectAll();

    int updateByPrimaryKey(AgencyUser record);

    List<Long> selectClassIdByUserId(String userId);

    List<AgencyUser> selectByUserAndClassId(@Param("userId")String userId,@Param("agencyClassId")Long agencyClassId);

    Integer selectCountByClassIdAndRole(@Param("agencyClassId")Long agencyClassId,@Param("role")int role);

    List<AgencyUser> selectByClassIdAndRole(@Param("agencyClassId")Long agencyClassId,@Param("role")int role);

}