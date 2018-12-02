package com.pq.agency.mapper;

import com.pq.agency.entity.ClassTaskImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassTaskImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassTaskImg record);

    ClassTaskImg selectByPrimaryKey(Long id);

    List<ClassTaskImg> selectAll();

    int updateByPrimaryKey(ClassTaskImg record);

    List<ClassTaskImg> selectByTaskId(@Param("taskId") Long taskId);
}