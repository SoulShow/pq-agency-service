package com.pq.agency.service;


import com.pq.agency.dto.AgencyStudentLifeListDto;
import com.pq.agency.entity.AgencyStudent;
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

//    /**
//     * 获取学生信息
//     * @return
//     */
//    List<AgencyStudent> getStudentByStudentName(String studentName);
}
