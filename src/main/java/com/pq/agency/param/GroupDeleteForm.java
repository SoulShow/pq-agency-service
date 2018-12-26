package com.pq.agency.param;

import java.io.Serializable;

/**
 * @author liutao
 */
public class GroupDeleteForm implements Serializable {


    private static final long serialVersionUID = 1763225329434232323L;
    private String userId;
    private Long groupId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
