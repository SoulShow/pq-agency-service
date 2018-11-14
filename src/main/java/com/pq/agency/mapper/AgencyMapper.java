package com.pq.agency.mapper;

import com.pq.agency.entity.Agency;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Agency record);

    Agency selectByPrimaryKey(Long id);

    List<Agency> selectAll();

    int updateByPrimaryKey(Agency record);

    List<Agency> selectByName(@Param("name")String name);
}