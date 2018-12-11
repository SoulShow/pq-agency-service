package com.pq.agency.dto;

import java.util.List;

public class AgencyClassInfoDto {
    private Long id;
    private String groupId;
    private String img;
    private String name;
    private int count;
    private int type;
    private Integer disturbStatus;
    private Integer chatStatus;
    private List<ClassUserInfoDto> list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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