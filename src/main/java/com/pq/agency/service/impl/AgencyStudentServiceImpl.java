package com.pq.agency.service.impl;

import com.pq.agency.dto.*;
import com.pq.agency.entity.*;
import com.pq.agency.exception.AgencyErrorCode;
import com.pq.agency.exception.AgencyErrors;
import com.pq.agency.exception.AgencyException;
import com.pq.agency.feign.UserFeign;
import com.pq.agency.mapper.*;
import com.pq.agency.param.AddStudentForm;
import com.pq.agency.param.StudentLifeForm;
import com.pq.agency.service.AgencyStudentService;
import com.pq.agency.utils.AgencyResult;
import com.pq.common.constants.CommonConstants;
import com.pq.common.exception.CommonErrors;
import com.pq.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 */
@Service
public class AgencyStudentServiceImpl implements AgencyStudentService {

    @Autowired
    private AgencyStudentMapper studentMapper;
    @Autowired
    private AgencyStudentLifeMapper studentLifeMapper;
    @Autowired
    private AgencyStudentLifeImgMapper studentLifeImgMapper;
    @Autowired
    private AgencyClassMapper agencyClassMapper;
    @Autowired
    private AgencyUserMapper agencyUserMapper;
    @Autowired
    private AgencyUserStudentMapper agencyUserStudentMapper;
    @Autowired
    private AgencyClassInvitationCodeMapper invitationCodeMapper;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void updateStudentInfo(AgencyStudent agencyStudent){
        agencyStudent.setUpdatedTime(DateUtil.currentTime());
        studentMapper.updateByPrimaryKey(agencyStudent);
    }

    @Override
    public AgencyStudent getAgencyStudentById(Long id){
        return studentMapper.selectByPrimaryKey(id);
    }
    @Override
    public AgencyStudentLifeListDto getStudentLife(Long studentId, Long agencyClassId, int offset, int size){
        List<AgencyStudentLife> agencyStudentLifeList = studentLifeMapper.selectByStudentIdAndAgencyClassId(studentId,agencyClassId, offset, size);
        if(agencyStudentLifeList==null || agencyStudentLifeList.size()==0){
            return null;
        }
        List<AgencyStudentLifeDto> lifeList = new ArrayList<>();
        AgencyStudentLifeListDto lifeListDto = new AgencyStudentLifeListDto();
        for(AgencyStudentLife agencyStudentLife:agencyStudentLifeList){
            List<AgencyStudentLifeImg> imgList = studentLifeImgMapper.selectByLifeId(agencyStudentLife.getId());
            List<String> list = new ArrayList<>();
            for(AgencyStudentLifeImg img:imgList){
                list.add(img.getImg());
            }
            lifeListDto.setAgencyClassId(agencyStudentLife.getAgencyClassId());
            lifeListDto.setStudentId(agencyStudentLife.getStudentId());

            AgencyStudentLifeDto lifeDto = new AgencyStudentLifeDto();
            lifeDto.setImgList(list);
            lifeDto.setContent(agencyStudentLife.getContent());
            lifeDto.setTitle(agencyStudentLife.getTitle());

            lifeDto.setCreatedTime(DateUtil.formatDate(agencyStudentLife.getCreatedTime(),DateUtil.DEFAULT_DATE_FORMAT));
            lifeDto.setId(agencyStudentLife.getId());
            lifeList.add(lifeDto);
        }
        lifeListDto.setList(lifeList);
        return lifeListDto;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createStudentLife(StudentLifeForm studentLifeForm){
        AgencyStudentLife agencyStudentLife = new AgencyStudentLife();
        agencyStudentLife.setAgencyClassId(studentLifeForm.getAgencyClassId());
        agencyStudentLife.setStudentId(studentLifeForm.getStudentId());
        agencyStudentLife.setContent(studentLifeForm.getContent());
        agencyStudentLife.setTitle(studentLifeForm.getTitle());
        agencyStudentLife.setState(true);
        agencyStudentLife.setCreatedTime(DateUtil.currentTime());
        agencyStudentLife.setUpdatedTime(DateUtil.currentTime());
        studentLifeMapper.insert(agencyStudentLife);
        for(String img:studentLifeForm.getImgList()){
            AgencyStudentLifeImg studentLifeImg = new AgencyStudentLifeImg();
            studentLifeImg.setImg(img);
            studentLifeImg.setLifeId(agencyStudentLife.getId());
            studentLifeImg.setCreatedTime(DateUtil.currentTime());
            studentLifeImg.setUpdatedTime(DateUtil.currentTime());
            studentLifeImg.setState(true);
            studentLifeImgMapper.insert(studentLifeImg);
        }
    }
    @Override
    public int getStudentCount(Long classId){
        Integer count = studentMapper.selectCountByAgencyClassId(classId);
        if(count==null){
            count=0;
        }
        return count;
    }

    @Override
    public AgencyStudentDto getStudentInfoById(Long studentId){
        AgencyStudent agencyStudent = studentMapper.selectByPrimaryKey(studentId);
        if(agencyStudent==null){
            AgencyException.raise(AgencyErrors.AGENCY_STUDENT_NOT_EXIST_ERROR);
        }

        AgencyClass agencyClass = agencyClassMapper.selectByPrimaryKey(agencyStudent.getAgencyClassId());
        if(agencyClass==null){
            AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOT_EXIST_ERROR);
        }
        AgencyStudentDto agencyStudentDto = new AgencyStudentDto();
        agencyStudentDto.setStudentId(studentId);
        agencyStudentDto.setSex(agencyStudent.getSex());
        agencyStudentDto.setAvatar(agencyStudent.getAvatar());
        agencyStudentDto.setName(agencyStudent.getName());
        agencyStudentDto.setClassName(agencyClass.getName());
        agencyStudentDto.setClassId(agencyClass.getId());
        agencyStudentDto.setParentList(getParentList(agencyClass.getId(),agencyStudent));
        return agencyStudentDto;
    }

    private List<ParentDto> getParentList(Long classId, AgencyStudent agencyStudent){
        List<ParentDto> parentList = new ArrayList<>();

        List<AgencyUserStudent> userStudentList = agencyUserStudentMapper.
                selectByAgencyClassIdAndStudentId(classId,agencyStudent.getId());
        for(AgencyUserStudent userStudent : userStudentList){
            ParentDto parentDto = new ParentDto();
            parentDto.setUserId(userStudent.getUserId());
            parentDto.setName(agencyStudent.getName()+userStudent.getRelation());
            AgencyResult<UserDto> result = userFeign.getUserInfo(userStudent.getUserId());
            if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
                throw new AgencyException(new AgencyErrorCode(result.getStatus(),result.getMessage()));
            }
            UserDto userDto = result.getData();
            parentDto.setPhone(userDto.getUsername());
            parentDto.setHuanxinId(userDto.getHuanxinId());
            parentDto.setAvatar(userDto.getAvatar());
            parentList.add(parentDto);
        }
        return parentList;
    }

    @Override
    public List<AgencyTeacherDto> getClassTeachersByStudentId(Long studentId){

        AgencyStudent agencyStudent = studentMapper.selectByPrimaryKey(studentId);
        if(agencyStudent==null){
            AgencyException.raise(AgencyErrors.AGENCY_USER_STUDENT_NOT_EXIST_ERROR);
        }
        AgencyClass agencyClass = agencyClassMapper.selectByPrimaryKey(agencyStudent.getAgencyClassId());
        if(agencyClass==null){
            AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOT_EXIST_ERROR);
        }

        List<AgencyUser> userList = agencyUserMapper.selectByClassIdAndRole(agencyClass.getId(),
                CommonConstants.PQ_LOGIN_ROLE_TEACHER);
        List<AgencyTeacherDto> list = new ArrayList<>();
        for(AgencyUser agencyUser:userList){
            AgencyTeacherDto teacherDto = new AgencyTeacherDto();
            AgencyResult<UserDto> result = userFeign.getUserInfo(agencyUser.getUserId());
            if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
                throw new AgencyException(new AgencyErrorCode(result.getStatus(),result.getMessage()));
            }
            UserDto userDto = result.getData();

            teacherDto.setUserId(agencyUser.getUserId());
            teacherDto.setHuanxinId(userDto.getHuanxinId());
            teacherDto.setName(userDto.getName());
            teacherDto.setAvatar(userDto.getAvatar());
            list.add(teacherDto);
        }
        return list;
    }
    @Override
    public List<Long> getClassStudentList(Long classId){
        List<Long> studentDtos = new ArrayList<>();
        List<AgencyStudent> list = studentMapper.selectByAgencyClassId(classId);
        for(AgencyStudent agencyStudent:list){
            studentDtos.add(agencyStudent.getId());
        }
        return studentDtos;
    }

    @Override
    public List<Long> getAgencyStudentList(Long classId){
        List<Long> studentDtos = new ArrayList<>();
        AgencyClass agencyClass = agencyClassMapper.selectByPrimaryKey(classId);
        List<AgencyClass> classList = agencyClassMapper.selectGradeByAgencyId(agencyClass.getAgencyId());
        for(AgencyClass classInfo:classList ){
            List<AgencyStudent> list = studentMapper.selectByAgencyClassId(classInfo.getId());
            for(AgencyStudent agencyStudent:list){
                studentDtos.add(agencyStudent.getId());
            }
        }
        return studentDtos;
    }
    @Override
    public  AgencyStudentRelationDto getStudentExistRelation(String code,String name,String userId){
        AgencyClassInvitationCode invitationCode = invitationCodeMapper.selectByCode(code);
        if(invitationCode==null){
            AgencyException.raise(AgencyErrors.INVITATION_CODE_ERROR);
        }
        //check用户是否绑定过这个学生
        List<AgencyStudent> list = studentMapper.selectByAgencyClassIdAndName(invitationCode.getAgencyClassId(),name);

        AgencyUserStudent agencyUserStudent = agencyUserStudentMapper.selectByUserIdAndStudentId(userId,list.get(0).getId());
        if(agencyUserStudent!=null){
            AgencyException.raise(AgencyErrors.AGENCY_ADD_STUDENT_REPEAT_ERROR);
        }
        if(list==null||list.size()==0){
            if(redisTemplate.hasKey("STUDENT_NAME_ERROR_TIME"+userId)){
                int count = (int)redisTemplate.opsForValue().get("STUDENT_NAME_ERROR_TIME"+userId);
                if(count>=3){
                    redisTemplate.delete("STUDENT_NAME_ERROR_TIME"+userId);
                    AgencyException.raise(AgencyErrors.AGENCY_GET_STUDENT_NAME_ERROR);
                }
                redisTemplate.opsForValue().set("STUDENT_NAME_ERROR_TIME"+userId,count+1);
            }else {
                redisTemplate.opsForValue().set("STUDENT_NAME_ERROR_TIME"+userId,1);
            }

            AgencyException.raise(AgencyErrors.AGENCY_STUDENT_NOT_EXIST_ERROR);
        }

        List<AgencyUserStudent> userStudentList = agencyUserStudentMapper.
                selectByAgencyClassIdAndStudentId(invitationCode.getAgencyClassId(),list.get(0).getId());
        AgencyStudentRelationDto relationDto = new AgencyStudentRelationDto();

        List<String> relationList = new ArrayList<>();
        for(AgencyUserStudent userStudent:userStudentList){
            relationList.add(userStudent.getRelation());
        }
        relationDto.setRelationList(relationList);
        relationDto.setStudentId(list.get(0).getId());
        relationDto.setAgencyClassId(invitationCode.getAgencyClassId());
        return relationDto;
    }

    @Override
    public void userAddStudent(AddStudentForm addStudentForm){
        AgencyUserStudent agencyUserStudent = agencyUserStudentMapper.selectByStudentIdAndRelation(addStudentForm.getStudentId(),addStudentForm.getRelation());
        if(agencyUserStudent!=null){
            AgencyException.raise(AgencyErrors.AGENCY_ADD_STUDENT_RELATION_ERROR);
        }
        AgencyUser agencyUser = agencyUserMapper.selectByUserAndClassId(addStudentForm.getUserId(),addStudentForm.getAgencyClassId());
        AgencyUserStudent userStudent = new AgencyUserStudent();
        userStudent.setAgencyClassId(addStudentForm.getAgencyClassId());
        userStudent.setUserId(addStudentForm.getUserId());
        userStudent.setAgencyUserId(agencyUser.getId());
        userStudent.setStudentId(addStudentForm.getStudentId());
        userStudent.setStudentName(addStudentForm.getStudentName());
        userStudent.setRelation(addStudentForm.getRelation());
        userStudent.setState(true);
        userStudent.setUpdatedTime(DateUtil.currentTime());
        userStudent.setCreatedTime(DateUtil.currentTime());
        agencyUserStudentMapper.insert(userStudent);
    }


}
