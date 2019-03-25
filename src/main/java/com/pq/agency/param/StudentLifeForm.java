package com.pq.agency.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 */
public class StudentLifeForm implements Serializable {

    private static final long serialVersionUID = 7941181159343550297L;

    private Long agencyClassId;

    private Long studentId;

    private String title;

    private String content;

    private String userId;

    private List<String> imgList = new ArrayList<>();

    public Long getAgencyClassId() {
        return agencyClassId;
    }

    public void setAgencyClassId(Long agencyClassId) {
        this.agencyClassId = agencyClassId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
