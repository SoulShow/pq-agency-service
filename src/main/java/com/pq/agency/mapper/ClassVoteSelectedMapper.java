package com.pq.agency.mapper;

import com.pq.agency.entity.ClassVoteSelected;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassVoteSelectedMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassVoteSelected record);

    ClassVoteSelected selectByPrimaryKey(Long id);

    List<ClassVoteSelected> selectAll();

    int updateByPrimaryKey(ClassVoteSelected record);

    Integer selectCountByVoteId(@Param("voteId")Long voteId);

    ClassVoteSelected selectByVoteIdAndUserIdAndStudentId(@Param("voteId")Long voteId,
                                                          @Param("userId") String userId,
                                                          @Param("studentId")Long studentId);

    List<ClassVoteSelected> selectByOptionAndVoteId(@Param("item")String item,@Param("voteId")Long voteId);
}