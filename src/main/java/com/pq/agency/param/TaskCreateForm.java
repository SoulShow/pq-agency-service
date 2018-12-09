package com.pq.agency.param;

import java.io.Serializable;
import java.util.List;

/**
 * @author liutao
 */
public class TaskCreateForm implements Serializable {

    private static final long serialVersionUID = -3531384792517813873L;
    private String userId;
    private String content;
    private String title;
    private Long classId;
    private List<String> imgList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
