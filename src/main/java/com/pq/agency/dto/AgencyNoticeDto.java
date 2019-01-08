package com.pq.agency.dto;

public class AgencyNoticeDto {
    private Long id;

    private String title;

    private String createdTime;

    private String content;

    private int receiptStatus;

    private int readStatus;

    private int fileStatus;

    private int imgStatus;

    private String teacherName;

    private String className;

    private String avatar;

    private int isReceipt;

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(int receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public int getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(int fileStatus) {
        this.fileStatus = fileStatus;
    }

    public int getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(int imgStatus) {
        this.imgStatus = imgStatus;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getIsReceipt() {
        return isReceipt;
    }

    public void setIsReceipt(int isReceipt) {
        this.isReceipt = isReceipt;
    }
}