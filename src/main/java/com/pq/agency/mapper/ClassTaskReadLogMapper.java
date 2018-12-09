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

    ClassTaskReadLog selectByUserIdAndStudentIdAndTaskId(@Param("userId") String userId, @Param("studentId")Long studentId, @Param("taskId") Long taskId);

    List<ClassTaskReadLog> selectByStudentIdAndTaskId(@Param("studentId")Long studentId, @Param("taskId") Long taskId);
}