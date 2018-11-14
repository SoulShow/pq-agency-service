package com.pq.agency.mapper;

import com.pq.agency.entity.ClassShow;

import java.util.List;

public interface ClassShowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassShow record);

    ClassShow selectByPrimaryKey(Long id);

    List<ClassShow> selectAll();

    int updateByPrimaryKey(ClassShow record);
}