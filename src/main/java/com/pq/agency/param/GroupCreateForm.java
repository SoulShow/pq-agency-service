package com.pq.agency.param;

import com.pq.agency.dto.ClassStudentDto;

import java.io.Serializable;
import java.util.List;

/**
 * @author liutao
 */
public class GroupCreateForm implements Serializable {

    private static final long serialVersionUID = -1398886718987610241L;

    private String userId;
    private List<String> teacherList;
    private List<ClassStudentDto> studentList;
    private String name;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<String> teacherList) {
        this.teacherList = teacherList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassStudentDto> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<ClassStudentDto> studentList) {
        this.studentList = studentList;
    }
}
