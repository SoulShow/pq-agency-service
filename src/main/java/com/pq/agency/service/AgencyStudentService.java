package com.pq.agency.service;


import com.pq.agency.dto.AgencyStudentDto;
import com.pq.agency.dto.AgencyStudentLifeListDto;
import com.pq.agency.dto.AgencyStudentRelationDto;
import com.pq.agency.dto.AgencyTeacherDto;
import com.pq.agency.entity.AgencyStudent;
import com.pq.agency.param.AddStudentForm;
import com.pq.agency.param.StudentLifeForm;

import java.util.List;

/**
 * 机构学生服务
 * @author liutao
 */
public interface AgencyStudentService {

    /**
     * 更新学生信息
     * @param agencyStudent
     */
    void updateStudentInfo(AgencyStudent agencyStudent);

    /**
     * 获取学生信息
     * @param id
     * @return
     */
    AgencyStudent getAgencyStudentById(Long id);

    /**
     * 获取学生成长动态
     * @param studentId
     * @param agencyClassId
     * @param offset
     * @param size
     * @return
     */
    AgencyStudentLifeListDto getStudentLife(Long studentId, Long agencyClassId, int offset, int size);


    /**
     * 创建学生成长动态
     * @param studentLifeForm
     */
    void createStudentLife(StudentLifeForm studentLifeForm);


    /**
     * 获取学生数量
     * @param classId
     * @return
     */
    int getStudentCount(Long classId);


    /**
     * 获取学生相关信息
     * @param studentId
     * @return
     */
    AgencyStudentDto getStudentInfoById(Long studentId);

    /**
     * 获取班级老师
     * @param studentId
     * @return
     */
    List<AgencyTeacherDto> getClassTeachersByStudentId(Long studentId);

    /**
     * 获取班级学生列表
     * @param classId
     * @return
     */
    List<Long> getClassStudentList(Long classId);

    /**
     * 获取学校全部学生
     * @param classId
     * @return
     */
    List<Long> getAgencyStudentList(Long classId);

    /**
     * 检查学生姓名是都存在
     * @param code
     * @param name
     * @param userId
     * @return
     */
    AgencyStudentRelationDto getStudentExistRelation(String code, String name, String userId);

    /**
     * 用户添加学生
     * @param addStudentForm
     */
    void userAddStudent(AddStudentForm addStudentForm);




}
