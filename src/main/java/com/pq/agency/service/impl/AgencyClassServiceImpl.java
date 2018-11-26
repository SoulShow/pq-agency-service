package com.pq.agency.service.impl;

import com.pq.agency.dto.AgencyUserDto;
import com.pq.agency.entity.*;
import com.pq.agency.exception.AgencyErrors;
import com.pq.agency.exception.AgencyException;
import com.pq.agency.mapper.*;
import com.pq.agency.param.AgencyUserRegisterForm;
import com.pq.agency.service.AgencyClassService;
import com.pq.common.constants.ParentRelationTypeEnum;
import com.pq.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private AgencyStudentMapper agencyStudentMapper;
    @Autowired
    private AgencyUserStudentMapper agencyUserStudentMapper;
    @Autowired
    private AgencyUserMapper agencyUserMapper;
    @Autowired
    private AgencyMapper agencyMapper;

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
    @Override
    public  Boolean checkStudent(Long agencyId, Long gradeId, Long classId, Long studentId){
        AgencyClass agencyClass = getAgencyClass(agencyId,gradeId,classId);
        if(agencyClass==null){
            return false;
        }
        AgencyStudent student = agencyStudentMapper.selectByAgencyClassIdAndId(agencyClass.getId(),studentId);
        if(student==null){
            return false;
        }
        return true;
    }

    @Override
    public List<AgencyStudent> getStudentInfo(Long agencyId, Long gradeId, Long classId, String studentName){
        AgencyClass agencyClass = getAgencyClass(agencyId,gradeId,classId);
        if(agencyClass==null){
            AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOT_EXIST_ERROR);
        }
        return agencyStudentMapper.selectByAgencyClassIdAndName(agencyClass.getId(),studentName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(AgencyUserRegisterForm registerForm){
        AgencyClass agencyClass = getAgencyClass(registerForm.getAgencyId(),registerForm.getGradeId(),registerForm.getClassId());
        if(agencyClass==null){
            AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOT_EXIST_ERROR);
        }
        AgencyUser agencyUser = new AgencyUser();
        agencyUser.setAgencyClassId(agencyClass.getId());
        agencyUser.setUserId(registerForm.getUserId());
        agencyUser.setRole(registerForm.getRole());
        agencyUser.setState(true);
        agencyUser.setCreatedTime(DateUtil.currentTime());
        agencyUser.setUpdatedTime(DateUtil.currentTime());
        agencyUserMapper.insert(agencyUser);

        AgencyUserStudent userStudent = new AgencyUserStudent();
        userStudent.setAgencyClassId(agencyClass.getId());
        userStudent.setUserId(registerForm.getUserId());
        userStudent.setAgencyUserId(agencyUser.getId());
        userStudent.setStudentId(registerForm.getStudentId());
        userStudent.setStudentName(agencyStudentMapper.selectByPrimaryKey(registerForm.getStudentId()).getName());
        userStudent.setState(true);
        userStudent.setCreatedTime(DateUtil.currentTime());
        userStudent.setUpdatedTime(DateUtil.currentTime());
        agencyUserStudentMapper.insert(userStudent);
    }
    @Override
    public List<AgencyUserDto> getAgencyUserInfo(String userId){
        List<AgencyUserDto> userDtoList = new ArrayList<>();
       List<AgencyUserStudent> list = agencyUserStudentMapper.selectByUserId(userId);
       for(AgencyUserStudent agencyUserStudent:list){
           AgencyUserDto agencyUserDto = new AgencyUserDto();
           agencyUserDto.setAgencyClassId(agencyUserStudent.getAgencyClassId());
           agencyUserDto.setUserId(agencyUserStudent.getUserId());
           agencyUserDto.setStudentId(agencyUserStudent.getStudentId());
           AgencyUser agencyUser = agencyUserMapper.selectByPrimaryKey(agencyUserStudent.getAgencyUserId());
           if(agencyUser==null){
               AgencyException.raise(AgencyErrors.AGENCY_CLASS_USER_NOT_EXIST_ERROR);
           }
           agencyUserDto.setRole(agencyUser.getRole());
           agencyUserDto.setRelation(agencyUserStudent.getRelation());
           agencyUserDto.setStudentName(agencyUserStudent.getStudentName());
           agencyUserDto.setName(agencyUserStudent.getStudentName()+ agencyUserStudent.getRelation());
           AgencyClass agencyClass = agencyClassMapper.selectByPrimaryKey(agencyUserStudent.getAgencyClassId());
           if(agencyUser==null){
               AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOT_EXIST_ERROR);
           }
           Agency agency = agencyMapper.selectByPrimaryKey(agencyClass.getAgencyId());
           if(agency==null){
               AgencyException.raise(AgencyErrors.AGENCY_NOT_EXIST_ERROR);
           }
           agencyUserDto.setAgencyName(agency.getName());
           agencyUserDto.setClassName(agencyClass.getName());
           AgencyStudent agencyStudent = agencyStudentMapper.selectByPrimaryKey(agencyUserStudent.getStudentId());
           agencyUserDto.setSex(agencyStudent.getSex());
           agencyUserDto.setAvatar(agencyStudent.getAvatar());
           userDtoList.add(agencyUserDto);
       }
       return userDtoList;
    }

    @Override
    public List<Long> getUserClassId(String userId){
        return agencyUserMapper.selectClassIdByUserId(userId);
    }


}
