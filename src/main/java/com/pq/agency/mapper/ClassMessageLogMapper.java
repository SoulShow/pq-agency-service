package com.pq.agency.mapper;

import com.pq.agency.entity.ClassMessageLog;

import java.util.List;

public interface ClassMessageLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassMessageLog record);

    ClassMessageLog selectByPrimaryKey(Long id);

    List<ClassMessageLog> selectAll();

    int updateByPrimaryKey(ClassMessageLog record);


}