package com.pq.agency.param;


import java.util.List;

public class ScheduleUpdateForm {

    private Long agencyClassId;

    private int week;

    private List<String> schedule;

    public Long getAgencyClassId() {
        return agencyClassId;
    }

    public void setAgencyClassId(Long agencyClassId) {
        this.agencyClassId = agencyClassId;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }
}