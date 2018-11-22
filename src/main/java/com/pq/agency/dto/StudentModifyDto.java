package com.pq.agency.dto;

import java.io.Serializable;

/**
 * @author liutao
 */
public class StudentModifyDto implements Serializable {

    private static final long serialVersionUID = 4414194841724802043L;
    private Long studentId;
    private int sex;
    private String avatar;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
