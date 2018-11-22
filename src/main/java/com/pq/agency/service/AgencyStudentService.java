package com.pq.agency.service;


import com.pq.agency.entity.AgencyStudent;

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
}
