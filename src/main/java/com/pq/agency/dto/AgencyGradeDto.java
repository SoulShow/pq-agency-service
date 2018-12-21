package com.pq.agency.dto;

import java.util.List;

public class AgencyGradeDto {
    private Long id;

    private List<AgencyClassDto> classList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AgencyClassDto> getClassList() {
        return classList;
    }

    public void setClassList(List<AgencyClassDto> classList) {
        this.classList = classList;
    }
}