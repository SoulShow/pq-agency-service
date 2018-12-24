package com.pq.agency.param;


import com.pq.agency.dto.ScheduleDto;

import java.util.List;

public class ScheduleUpdateForm {

    private Long agencyClassId;
    private List<ScheduleDto> scheduleList;

    public Long getAgencyClassId() {
        return agencyClassId;
    }

    public void setAgencyClassId(Long agencyClassId) {
        this.agencyClassId = agencyClassId;
    }

    public List<ScheduleDto> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<ScheduleDto> scheduleList) {
        this.scheduleList = scheduleList;
    }
}