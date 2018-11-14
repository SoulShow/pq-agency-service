package com.pq.agency.service;


import com.pq.agency.entity.Agency;
import com.pq.agency.entity.AgencyClass;
import com.pq.agency.entity.AgencyGrade;

import java.util.List;

/**
 * 机构服务
 * @author liutao
 */
public interface AgencyService {

    /**
     * 获取机构列表
     *
     * @param name
     * @return
     * @throws Exception
     */
    List<Agency> getAgencyList(String name);


    /**
     * 根据机构id查询机构班级
     * @param agencyId
     * @return
     */
    List<AgencyGrade> getAgencyGradeList(Long agencyId);

    /**
     * 根据机构id查询机构班级
     * @param agencyId
     * @param gradeId
     * @return
     */
    List<AgencyClass> getAgencyClassList(Long agencyId,Long gradeId);

}
