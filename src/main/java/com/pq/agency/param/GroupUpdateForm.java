package com.pq.agency.param;

import java.io.Serializable;

/**
 * @author liutao
 */
public class GroupUpdateForm implements Serializable {

    private static final long serialVersionUID = -2424901040798656377L;
    private String userId;
    private String name;
    private String img;
    private Long groupId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
