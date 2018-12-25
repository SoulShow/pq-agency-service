package com.pq.agency.param;

import java.io.Serializable;

/**
 * @author liutao
 */
public class ShowDelForm implements Serializable {

    private static final long serialVersionUID = 1718961352133464329L;
    private Long showId;
    private String userId;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
