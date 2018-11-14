package com.pq.agency.mapper;

import com.pq.agency.entity.ClassShowImg;

import java.util.List;

public interface ClassShowImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassShowImg record);

    ClassShowImg selectByPrimaryKey(Long id);

    List<ClassShowImg> selectAll();

    int updateByPrimaryKey(ClassShowImg record);
}