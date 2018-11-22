package com.pq.agency.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author liutao
 */
public class StudentModifyDto implements Serializable {

    private static final long serialVersionUID = 4414194841724802043L;
    private Long studentId;
    private int sex;
    private MultipartFile avatar;
    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

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
}
