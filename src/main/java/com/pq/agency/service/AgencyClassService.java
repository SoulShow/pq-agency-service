package com.pq.agency.service;



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



}
