package com.pq.agency.mapper;

import com.pq.agency.entity.ClassShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassShowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassShow record);

    ClassShow selectByPrimaryKey(Long id);

    List<ClassShow> selectAll();

    int updateByPrimaryKey(ClassShow record);

    List<ClassShow> selectByClassId(@Param("agencyClassId")Long agencyClassId,
                                    @Param("offset")int offset,@Param("size")int size);
}