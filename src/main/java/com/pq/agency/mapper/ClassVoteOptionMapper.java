package com.pq.agency.mapper;

import com.pq.agency.entity.ClassVoteOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassVoteOptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassVoteOption record);

    ClassVoteOption selectByPrimaryKey(Long id);

    List<ClassVoteOption> selectAll();

    int updateByPrimaryKey(ClassVoteOption record);

    List<ClassVoteOption> selectByVoteId(@Param("voteId") Long voteId);

}