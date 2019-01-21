package com.pq.agency.service.impl;

import com.pq.agency.dto.*;
import com.pq.agency.entity.*;
import com.pq.agency.exception.AgencyErrorCode;
import com.pq.agency.exception.AgencyErrors;
import com.pq.agency.exception.AgencyException;
import com.pq.agency.feign.UserFeign;
import com.pq.agency.mapper.*;
import com.pq.agency.param.StudentLifeForm;
import com.pq.agency.service.AgencyStudentService;
import com.pq.agency.utils.AgencyResult;
import com.pq.common.constants.CommonConstants;
import com.pq.common.exception.CommonErrors;
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
    private UserFeign userFeign;
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
        return agencyStudentDto;
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





}
