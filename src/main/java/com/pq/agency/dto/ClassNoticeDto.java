package com.pq.agency.dto;

import java.util.List;

public class ClassNoticeDto {

    private List<NoticeFileDto> fileList;

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

    public Long getAgencyClassId() {
        return agencyClassId;
    }

    public void setAgencyClassId(Long agencyClassId) {
        this.agencyClassId = agencyClassId;
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
}