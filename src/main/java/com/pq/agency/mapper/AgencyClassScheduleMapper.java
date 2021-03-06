package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyClassSchedule;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface AgencyClassScheduleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyClassSchedule record);

    AgencyClassSchedule selectByPrimaryKey(Long id);

    List<AgencyClassSchedule> selectAll();

    int updateByPrimaryKey(AgencyClassSchedule record);

    List<AgencyClassSchedule> selectByClassId(@Param("agencyClassId") Long agencyClassId);

    AgencyClassSchedule selectByClassIdAndWeek(@Param("agencyClassId") Long agencyClassId,
                                               @Param("week")int week);

}