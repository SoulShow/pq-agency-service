package com.pq.agency.param;

import java.io.Serializable;
import java.util.List;

/**
 * @author liutao
 */
public class VoteSelectedForm implements Serializable {

    private static final long serialVersionUID = -2674106306479114117L;
    private Long voteId;
    private List<String> options;
    private Long studentId;
    private String userId;
    private String name;

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

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
}
