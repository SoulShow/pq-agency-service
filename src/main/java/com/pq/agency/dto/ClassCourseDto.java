package com.pq.agency.dto;

import com.pq.agency.entity.ClassCourse;

import java.util.List;

public class ClassCourseDto {
    private Long classId;
    private String className;
    private List<ClassCourse> classCourseList;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ClassCourse> getClassCourseList() {
        return classCourseList;
    }

    public void setClassCourseList(List<ClassCourse> classCourseList) {
        this.classCourseList = classCourseList;
    }
}