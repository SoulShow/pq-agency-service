package com.pq.agency.dto;

import java.util.List;

public class ClassAndTeacherInfoDto {
    private List<AgencyClassDto> classList;

    private List<AgencyTeacherDto> teacherList;


    public List<AgencyClassDto> getClassList() {
        return classList;
    }

    public void setClassList(List<AgencyClassDto> classList) {
        this.classList = classList;
    }

    public List<AgencyTeacherDto> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<AgencyTeacherDto> teacherList) {
        this.teacherList = teacherList;
    }
}