package com.pq.agency.entity;

import java.sql.Timestamp;

public class AgencyClassSchedule {
    private Long id;

    private Long agencyClassId;

    private Integer week;

    private String schedule;

    private Boolean state;

    private Timestamp updatedTime;

    private Timestamp createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgencyClassId() {
        return agencyClassId;
    }

    public void setAgencyClassId(Long agencyClassId) {
        this.agencyClassId = agencyClassId;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule == null ? null : schedule.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }
}