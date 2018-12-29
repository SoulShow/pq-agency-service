package com.pq.agency.param;

import java.io.Serializable;

/**
 * @author liutao
 */
public class ChatStatusForm implements Serializable {
    private static final long serialVersionUID = -5012356177885649604L;
    private String userId;
    private Long groupId;
    private Integer status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
