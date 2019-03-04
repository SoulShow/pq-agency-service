package com.pq.agency.dto;

import java.util.List;

public class AgencyStudentRelationDto {
    private Long studentId;
    private Long agencyClassId;
    private List<String> relationList;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<String> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<String> relationList) {
        this.relationList = relationList;
    }

    public Long getAgencyClassId() {
        return agencyClassId;
    }

    public void setAgencyClassId(Long agencyClassId) {
        this.agencyClassId = agencyClassId;
    }
}