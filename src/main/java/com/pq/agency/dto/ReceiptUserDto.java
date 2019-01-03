package com.pq.agency.dto;

import java.util.List;

public class ReceiptUserDto {

    private Long studentId;

    private String avatar;

    private String name;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<ParentDto> getParentList() {
        return parentList;
    }

    public void setParentList(List<ParentDto> parentList) {
        this.parentList = parentList;
    }


}