package com.pq.agency.service.impl;

import com.pq.agency.entity.AgencyClass;
import com.pq.agency.entity.AgencyClassInvitationCode;
import com.pq.agency.entity.Grade;
import com.pq.agency.mapper.AgencyClassInvitationCodeMapper;
import com.pq.agency.mapper.AgencyClassMapper;
import com.pq.agency.mapper.GradeMapper;
import com.pq.agency.service.AgencyClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 */
@Service
public class AgencyClassServiceImpl implements AgencyClassService {

    @Autowired
    private AgencyClassInvitationCodeMapper invitationCodeMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private AgencyClassMapper agencyClassMapper;
    @Override
    public Boolean checkInvitationCode(Long agencyId,Long gradeId,Long classId,String invitationCode){

        AgencyClass agencyClass = getAgencyClass(agencyId,gradeId,classId);
        AgencyClassInvitationCode agencyClassInvitationCode = invitationCodeMapper.
                selectByAgencyClassId(agencyClass.getId());
        if(agencyClassInvitationCode==null
                ||!invitationCode.equals(agencyClassInvitationCode.getCode())){
            return false;
        }
        return true;
    }

    @Override
    public List<Grade> getAgencyGradeList(Long agencyId){
        List<Grade> gradeList = new ArrayList<>();
        if(agencyId==null){
            gradeList = gradeMapper.selectValid();
        }else {
            List<AgencyClass> gradeIdList = agencyClassMapper.selectGradeByAgencyId(agencyId);
            for(AgencyClass agencyClass:gradeIdList){
                gradeList.add(gradeMapper.selectByPrimaryKey(agencyClass.getGradeId()));
            }
        }
        return gradeList;
    }
    @Override
    public List<AgencyClass> getAgencyClassList(Long agencyId, Long gradeId){
        List<AgencyClass> classList = agencyClassMapper.selectClassByAgencyIdAndGradeId(agencyId,gradeId);
        return classList;
    }

    @Override
    public AgencyClass getAgencyClass(Long agencyId, Long gradeId,Long classId){
        return agencyClassMapper.selectByAgencyInfo(agencyId,gradeId,classId);
    }




}
