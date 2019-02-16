package com.pq.agency.param;

import java.io.Serializable;
import java.util.List;

/**
 * @author liutao
 */
public class TeacherCourseForm implements Serializable {

    private static final long serialVersionUID = 6463724844871324162L;
    private String userId;
    private Long classId;
    private List<Long> classCourseIdList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public List<Long> getClassCourseIdList() {
        return classCourseIdList;
    }

    public void setClassCourseIdList(List<Long> classCourseIdList) {
        this.classCourseIdList = classCourseIdList;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
