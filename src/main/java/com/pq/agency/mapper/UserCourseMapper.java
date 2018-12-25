package com.pq.agency.mapper;

import com.pq.agency.entity.UserCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourse record);

    UserCourse selectByPrimaryKey(Long id);

    List<UserCourse> selectAll();

    int updateByPrimaryKey(UserCourse record);

    List<UserCourse> selectByUserId(@Param("userId")String userId);
}