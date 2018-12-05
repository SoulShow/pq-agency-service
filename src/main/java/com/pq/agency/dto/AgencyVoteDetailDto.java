package com.pq.agency.dto;

import java.util.List;

public class AgencyVoteDetailDto {

    private Long id;

    private String userId;

    private String username;

    private String avatar;

    private String deadLine;

    private String content;

    private int votedCount;

    private int isVoted;

    private int voteStatus;

    private int isSecret;

    private List<VoteOptionDetailDto> optionList;

    private List<String> imgList;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVotedCount() {
        return votedCount;
    }

    public void setVotedCount(int votedCount) {
        this.votedCount = votedCount;
    }

    public int getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(int voteStatus) {
        this.voteStatus = voteStatus;
    }

    public int getIsVoted() {
        return isVoted;
    }

    public void setIsVoted(int isVoted) {
        this.isVoted = isVoted;
    }

    public int getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(int isSecret) {
        this.isSecret = isSecret;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public List<VoteOptionDetailDto> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<VoteOptionDetailDto> optionList) {
        this.optionList = optionList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}