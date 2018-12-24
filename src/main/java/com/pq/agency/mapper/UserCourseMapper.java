package com.pq.agency.mapper;

import com.pq.agency.entity.UserCourse;

import java.util.List;

public interface UserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourse record);

    UserCourse selectByPrimaryKey(Long id);

    List<UserCourse> selectAll();

    int updateByPrimaryKey(UserCourse record);
}