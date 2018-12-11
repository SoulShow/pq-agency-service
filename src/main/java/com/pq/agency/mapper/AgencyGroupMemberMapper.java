package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyGroupMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyGroupMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyGroupMember record);

    AgencyGroupMember selectByPrimaryKey(Long id);

    List<AgencyGroupMember> selectAll();

    int updateByPrimaryKey(AgencyGroupMember record);

    Integer selectCountByGroupId(@Param("groupId") Long groupId);

    List<AgencyGroupMember> selectByGroupId(@Param("groupId") Long groupId);

    List<AgencyGroupMember> selectByStudentIdOrUserId(@Param("studentId")Long studentId,
                                                      @Param("userId")String userId);

    AgencyGroupMember selectByGroupIdAndStudentOrUserId(@Param("groupId") Long groupId,
                                                        @Param("studentId")Long studentId,
                                                        @Param("userId")String userId);

    List<AgencyGroupMember> selectDisturbByStudentIdOrUserId(@Param("studentId")Long studentId,
                                                             @Param("userId")String userId);
}