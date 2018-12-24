package com.pq.agency.param;

import java.io.Serializable;

/**
 * @author liutao
 */
public class VoteDelForm implements Serializable {

    private static final long serialVersionUID = 95754034401651992L;
    private Long voteId;

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

}
