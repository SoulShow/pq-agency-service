package com.pq.agency.param;

import com.pq.agency.dto.ClassStudentDto;

import java.io.Serializable;
import java.util.List;

/**
 * @author liutao
 */
public class AddGroupMemberForm implements Serializable {

    private static final long serialVersionUID = -356033830923119698L;
    private String userId;
    private Long groupId;
    private List<String> teacherList;
    private List<ClassStudentDto> studentList;

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

    public List<ClassStudentDto> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<ClassStudentDto> studentList) {
        this.studentList = studentList;
    }
}
