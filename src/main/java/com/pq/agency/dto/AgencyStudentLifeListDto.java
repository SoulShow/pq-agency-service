package com.pq.agency.dto;

import java.util.List;

public class AgencyStudentLifeListDto {

    private Long agencyClassId;

    private Long studentId;

    private List<AgencyStudentLifeDto> list;


    public Long getAgencyClassId() {
        return agencyClassId;
    }

    public void setAgencyClassId(Long agencyClassId) {
        this.agencyClassId = agencyClassId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<AgencyStudentLifeDto> getList() {
        return list;
    }

    public void setList(List<AgencyStudentLifeDto> list) {
        this.list = list;
    }
}