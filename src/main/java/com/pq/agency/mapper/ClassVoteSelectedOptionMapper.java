package com.pq.agency.mapper;

import com.pq.agency.entity.ClassVoteSelectedOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassVoteSelectedOptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassVoteSelectedOption record);

    ClassVoteSelectedOption selectByPrimaryKey(Long id);

    List<ClassVoteSelectedOption> selectAll();

    int updateByPrimaryKey(ClassVoteSelectedOption record);

    Integer selectCountByVoteIdAndOption(@Param("voteId")Long voteId,@Param("option")String option);
}