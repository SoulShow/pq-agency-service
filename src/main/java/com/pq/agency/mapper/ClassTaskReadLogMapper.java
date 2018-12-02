package com.pq.agency.mapper;

import com.pq.agency.entity.ClassTaskReadLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassTaskReadLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassTaskReadLog record);

    ClassTaskReadLog selectByPrimaryKey(Long id);

    List<ClassTaskReadLog> selectAll();

    int updateByPrimaryKey(ClassTaskReadLog record);

    ClassTaskReadLog selectByUserIdAndTaskId(@Param("userId") String userId, @Param("taskId") Long taskId);

}