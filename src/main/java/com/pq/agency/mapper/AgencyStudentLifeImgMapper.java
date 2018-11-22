package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyStudentLifeImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyStudentLifeImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyStudentLifeImg record);

    AgencyStudentLifeImg selectByPrimaryKey(Long id);

    List<AgencyStudentLifeImg> selectAll();

    int updateByPrimaryKey(AgencyStudentLifeImg record);

    List<AgencyStudentLifeImg> selectByLifeId(@Param("lifeId") Long lifeId);
}