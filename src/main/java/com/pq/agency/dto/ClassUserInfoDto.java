package com.pq.agency.dto;

import java.util.List;

public class ClassUserInfoDto {
    private Long studentId;

    private String userId;

    private String avatar;

    private String name;

    private String className;

    private int role;

    private String sex;

    private String huanxinId;

    private Integer disturbStatus;

    private Integer chatStatus;

    private List<ParentDto> parentList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<ParentDto> getParentList() {
        return parentList;
    }

    public void setParentList(List<ParentDto> parentList) {
        this.parentList = parentList;
    }

    public String getHuanxinId() {
        return huanxinId;
    }

    public void setHuanxinId(String huanxinId) {
        this.huanxinId = huanxinId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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
}