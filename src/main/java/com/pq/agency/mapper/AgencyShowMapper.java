package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyShowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyShow record);

    AgencyShow selectByPrimaryKey(Long id);

    List<AgencyShow> selectAll();

    int updateByPrimaryKey(AgencyShow record);

    List<AgencyShow> selectByAgencyId(@Param("agencyId") Long agencyId, @Param("isBanner") int isBanner,
                                      @Param("offset")int offset,@Param("size")int size);
}