package com.pq.agency.param;

import java.io.Serializable;

/**
 * @author liutao
 */
public class DisturbForm implements Serializable {
    private static final long serialVersionUID = -5352520869185582057L;
    private String userId;
    private Long studentId;
    private Long groupId;
    private Integer status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
