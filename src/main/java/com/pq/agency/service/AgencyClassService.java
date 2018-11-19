package com.pq.agency.service;


import com.pq.agency.dto.AgencyUserDto;
import com.pq.agency.entity.AgencyClass;
import com.pq.agency.entity.AgencyStudent;
import com.pq.agency.entity.Grade;
import com.pq.agency.param.AgencyUserRegisterForm;

import java.util.List;

/**
 * 机构班级服务
 * @author liutao
 */
public interface AgencyClassService {


    /**
     * check邀请码
     * @param agencyId
     * @param gradeId
     * @param classId
     * @param invitationCode
     * @return
     */
    Boolean checkInvitationCode(Long agencyId,Long gradeId,Long classId,String invitationCode);


    /**
     * 根据机构id查询机构班级
     * @param agencyId
     * @return
     */
    List<Grade> getAgencyGradeList(Long agencyId);

    /**
     * 根据机构id查询机构班级
     * @param agencyId
     * @param gradeId
     * @return
     */
    List<AgencyClass> getAgencyClassList(Long agencyId, Long gradeId);

    /**
     * 根据机构id查询机构班级
     * @param agencyId
     * @param gradeId
     * @param classId
     * @return
     */
    AgencyClass getAgencyClass(Long agencyId, Long gradeId,Long classId);

    /**
     * check学生
     * @param agencyId
     * @param gradeId
     * @param classId
     * @param studentId
     * @return
     */
    Boolean checkStudent(Long agencyId, Long gradeId, Long classId, Long studentId);

    /**
     * 获取学生信息
     * @param agencyId
     * @param gradeId
     * @param classId
     * @param studentName
     * @return
     */
    List<AgencyStudent> getStudentInfo(Long agencyId, Long gradeId, Long classId, String studentName);

    /**
     * 创建机构用户
     * @param registerForm
     */
    void createUser(AgencyUserRegisterForm registerForm);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    List<AgencyUserDto> getAgencyUserInfo(String userId);

}
