package com.pq.agency.dto;

import java.util.List;

public class VoteOptionDto {

    private int count;

    private String option;

    private List<OptionUserDto> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public List<OptionUserDto> getList() {
        return list;
    }

    public void setList(List<OptionUserDto> list) {
        this.list = list;
    }
}