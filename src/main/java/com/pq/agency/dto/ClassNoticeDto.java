package com.pq.agency.dto;

import java.util.List;

public class ClassNoticeDto {

    private Long noticeId;

    private List<NoticeFileDto> fileList;

    private List<Long> agencyClassIdList;

    private Long agencyClassId;

    private String userId;

    private String title;

    private String content;

    private int isReceipt;

    public List<NoticeFileDto> getFileList() {
        return fileList;
    }

    public void setFileList(List<NoticeFileDto> fileList) {
        this.fileList = fileList;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsReceipt() {
        return isReceipt;
    }

    public void setIsReceipt(int isReceipt) {
        this.isReceipt = isReceipt;
    }

    public List<Long> getAgencyClassIdList() {
        return agencyClassIdList;
    }

    public void setAgencyClassIdList(List<Long> agencyClassIdList) {
        this.agencyClassIdList = agencyClassIdList;
    }

    public Long getAgencyClassId() {
        return agencyClassId;
    }

    public void setAgencyClassId(Long agencyClassId) {
        this.agencyClassId = agencyClassId;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }
}