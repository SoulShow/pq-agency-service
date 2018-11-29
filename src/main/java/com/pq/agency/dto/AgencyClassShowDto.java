package com.pq.agency.dto;

import java.util.List;

public class AgencyClassShowDto {

    private String className;

    private List<AgencyClassShowDetailDto> showList;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<AgencyClassShowDetailDto> getShowList() {
        return showList;
    }

    public void setShowList(List<AgencyClassShowDetailDto> showList) {
        this.showList = showList;
    }
}