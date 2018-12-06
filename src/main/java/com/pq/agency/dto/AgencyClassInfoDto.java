package com.pq.agency.dto;

import java.util.List;

public class AgencyClassInfoDto {
    private Long id;
    private Long groupId;
    private String img;
    private String name;
    private int count;
    private List<ClassUserInfoDto> list;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ClassUserInfoDto> getList() {
        return list;
    }

    public void setList(List<ClassUserInfoDto> list) {
        this.list = list;
    }
}