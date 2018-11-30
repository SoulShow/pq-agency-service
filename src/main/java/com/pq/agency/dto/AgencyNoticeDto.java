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
}