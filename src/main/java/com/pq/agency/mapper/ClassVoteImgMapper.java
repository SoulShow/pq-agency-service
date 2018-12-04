package com.pq.agency.mapper;

import com.pq.agency.entity.ClassVoteImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassVoteImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassVoteImg record);

    ClassVoteImg selectByPrimaryKey(Long id);

    List<ClassVoteImg> selectAll();

    int updateByPrimaryKey(ClassVoteImg record);

    List<ClassVoteImg> selectByVoteId(@Param("voteId") Long voteId);
}