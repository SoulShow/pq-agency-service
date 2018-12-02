package com.pq.agency.mapper;

import com.pq.agency.entity.ClassTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassTask record);

    ClassTask selectByPrimaryKey(Long id);

    List<ClassTask> selectAll();

    int updateByPrimaryKey(ClassTask record);

    List<ClassTask> selectByClassId(@Param("agencyClassId")Long agencyClassId,
                                    @Param("offset")int offset,
                                    @Param("size") int size);
}