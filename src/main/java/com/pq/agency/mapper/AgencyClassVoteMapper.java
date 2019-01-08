package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyClassVote;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyClassVoteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyClassVote record);

    AgencyClassVote selectByPrimaryKey(Long id);

    List<AgencyClassVote> selectAll();

    int updateByPrimaryKey(AgencyClassVote record);

    List<AgencyClassVote> selectByClassIdList(@Param("agencyClassIdList") List<Long> agencyClassIdList,
                                          @Param("offset")int offset,@Param("size")int size);
}