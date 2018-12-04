package com.pq.agency.entity;

import java.sql.Timestamp;

public class ClassVoteOption {
    private Long id;

    private Long voteId;

    private String option;

    private Boolean state;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option == null ? null : option.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }
}