package com.pq.agency.entity;

import java.sql.Timestamp;

public class AgencyGroupMember {
    private Long id;

    private Long groupId;

    private Long studentId;

    private String userId;

    private Integer disturbStatus;

    private Integer chatStatus;

    private Integer isHead;

    private Boolean state;

    private Timestamp updatedTime;

    private Timestamp createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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

    public Integer getDisturbStatus() {
        return disturbStatus;
    }

    public void setDisturbStatus(Integer disturbStatus) {
        this.disturbStatus = disturbStatus;
    }

    public Integer getChatStatus() {
        return chatStatus;
    }

    public void setChatStatus(Integer chatStatus) {
        this.chatStatus = chatStatus;
    }

    public Integer getIsHead() {
        return isHead;
    }

    public void setIsHead(Integer isHead) {
        this.isHead = isHead;
    }
}