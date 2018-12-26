package com.pq.agency.param;

import java.io.Serializable;
import java.util.List;

/**
 * @author liutao
 */
public class DelGroupMemberForm implements Serializable {

    private static final long serialVersionUID = 7426376938245257660L;
    private String userId;
    private Long groupId;
    private List<String> teacherList;
    private List<Long> studentList;

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


    public List<String> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<String> teacherList) {
        this.teacherList = teacherList;
    }

    public List<Long> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Long> studentList) {
        this.studentList = studentList;
    }
}
