package com.pq.agency.mapper;

import com.pq.agency.entity.Class;

import java.util.List;

public interface ClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Class record);

    Class selectByPrimaryKey(Long id);

    List<Class> selectAll();

    int updateByPrimaryKey(Class record);
}