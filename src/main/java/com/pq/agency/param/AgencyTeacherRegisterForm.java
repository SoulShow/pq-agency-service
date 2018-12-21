package com.pq.agency.param;

import com.pq.agency.dto.AgencyClassDto;

import java.util.List;

public class AgencyTeacherRegisterForm {

    private String userId;

    private int role;

    private List<AgencyClassDto> classList;


    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public List<AgencyClassDto> getClassList() {
        return classList;
    }

    public void setClassList(List<AgencyClassDto> classList) {
        this.classList = classList;
    }
}