package com.pq.agency.service;



/**
 * 机构班级服务
 * @author liutao
 */
public interface AgencyStudentService {


    /**
     * check学生
     * @param agencyId
     * @param gradeId
     * @param classId
     * @param studentName
     * @return
     */
    Boolean checkStudent(Long agencyId, Long gradeId, Long classId, String studentName);



}
