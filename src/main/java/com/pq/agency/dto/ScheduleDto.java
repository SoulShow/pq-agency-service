package com.pq.agency.dto;


import java.util.List;

public class ScheduleDto {

    private int week;

    private List<String> schedule;


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