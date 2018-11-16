package com.pq.agency.service;


import com.pq.agency.entity.AgencyClass;
import com.pq.agency.entity.Grade;

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
     * @return
     */
    AgencyClass getAgencyClass(Long agencyId, Long gradeId,Long classId);


}
