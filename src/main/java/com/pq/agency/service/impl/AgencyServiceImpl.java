package com.pq.agency.service.impl;

import com.pq.agency.entity.Agency;
import com.pq.agency.entity.AgencyClass;
import com.pq.agency.entity.AgencyClassInvitationCode;
import com.pq.agency.entity.AgencyGrade;
import com.pq.agency.mapper.AgencyClassInvitationCodeMapper;
import com.pq.agency.mapper.AgencyGradeMapper;
import com.pq.agency.mapper.AgencyMapper;
import com.pq.agency.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 */
@Service
public class AgencyServiceImpl implements AgencyService {
    @Autowired
    private AgencyMapper agencyMapper;
    @Autowired
    private AgencyClassInvitationCodeMapper invitationCodeMapper;
    @Autowired
    private AgencyGradeMapper agencyGradeMapper;
    @Override
    public List<Agency> getAgencyList(String name){
        return agencyMapper.selectByName(name);
    }

    @Override
    public List<AgencyGrade> getAgencyGradeList(Long agencyId){
        List<AgencyGrade> gradeList = new ArrayList<>();
        if(agencyId==null){
            gradeList = agencyGradeMapper.selectValid();
        }else {
            List<AgencyClassInvitationCode> gradeIdList = invitationCodeMapper.selectGradeByAgencyId(agencyId);
            for(AgencyClassInvitationCode invitationCode:gradeIdList){
                gradeList.add(agencyGradeMapper.selectByPrimaryKey(invitationCode.getGradeId()));
            }
        }
        return gradeList;
    }
    @Override
    public List<AgencyClass> getAgencyClassList(Long agencyId, Long gradeId){
        List<AgencyClass> list = new ArrayList<>();
        List<AgencyClassInvitationCode> classList = invitationCodeMapper.selectClassByAgencyIdAndGradeId(agencyId,gradeId);
        for(AgencyClassInvitationCode classInvitationCode:classList){
            AgencyClass agencyClass = new AgencyClass();
            agencyClass.setId(classInvitationCode.getClassId());
            agencyClass.setName(classInvitationCode.getName());
            list.add(agencyClass);
        }
        return list;
    }


}
