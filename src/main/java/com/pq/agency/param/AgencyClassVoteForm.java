package com.pq.agency.param;

import java.util.List;

public class AgencyClassVoteForm {

    private List<Long> agencyClassIdList;

    private String userId;

    private String title;

    private String deadline;

    private Integer type;

    private Integer isSecret;

    private List<String> optionList;

    private List<String> imgList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(Integer isSecret) {
        this.isSecret = isSecret;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<String> optionList) {
        this.optionList = optionList;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public List<Long> getAgencyClassIdList() {
        return agencyClassIdList;
    }

    public void setAgencyClassIdList(List<Long> agencyClassIdList) {
        this.agencyClassIdList = agencyClassIdList;
    }
}