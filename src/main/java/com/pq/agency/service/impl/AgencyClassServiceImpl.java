package com.pq.agency.service.impl;

import com.pq.agency.entity.AgencyClassInvitationCode;
import com.pq.agency.mapper.AgencyClassInvitationCodeMapper;
import com.pq.agency.service.AgencyClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liutao
 */
@Service
public class AgencyClassServiceImpl implements AgencyClassService {

    @Autowired
    private AgencyClassInvitationCodeMapper invitationCodeMapper;
    @Override
    public Boolean checkInvitationCode(Long agencyId,Long gradeId,Long classId,String invitationCode){
        AgencyClassInvitationCode agencyClassInvitationCode = invitationCodeMapper.
                selectByAgencyInfo(agencyId,gradeId,classId);
        if(agencyClassInvitationCode==null
                ||!invitationCode.equals(agencyClassInvitationCode.getCode())){
            return false;
        }
        return true;
    }


}
