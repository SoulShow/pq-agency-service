package com.pq.agency.service.impl;

import com.pq.agency.dto.*;
import com.pq.agency.entity.*;
import com.pq.agency.exception.AgencyErrorCode;
import com.pq.agency.exception.AgencyErrors;
import com.pq.agency.exception.AgencyException;
import com.pq.agency.feign.UserFeign;
import com.pq.agency.mapper.*;
import com.pq.agency.param.AgencyUserRegisterForm;
import com.pq.agency.param.NoticeFileCollectionForm;
import com.pq.agency.param.NoticeReceiptForm;
import com.pq.agency.service.AgencyClassService;
import com.pq.agency.utils.AgencyResult;
import com.pq.agency.utils.Constants;
import com.pq.common.constants.CommonConstants;
import com.pq.common.constants.ParentRelationTypeEnum;
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
    @Autowired
    private ClassShowMapper classShowMapper;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private ClassShowImgMapper classShowImgMapper;
    @Autowired
    private AgencyShowMapper agencyShowMapper;
    @Autowired
    private AgencyClassNoticeMapper noticeMapper;
    @Autowired
    private ClassNoticeReceiptMapper noticeReceiptMapper;
    @Autowired
    private ClassNoticeFileMapper noticeFileMapper;
    @Autowired
    private UserNoticeFileCollectionMapper collectionMapper;

    @Override
    public void checkInvitationCodeAndStudent(String invitationCode,Long studentId, String studentName,String relation){

        AgencyClassInvitationCode agencyClassInvitationCode = invitationCodeMapper.selectByCode(invitationCode);
        if(agencyClassInvitationCode==null){
            AgencyException.raise(AgencyErrors.INVITATION_CODE_ERROR);
        }
        List<AgencyStudent> studentList = agencyStudentMapper.selectByAgencyClassIdAndName(agencyClassInvitationCode.getAgencyClassId(),studentName);
        if(studentList==null||studentList.size()==0){
            AgencyException.raise(AgencyErrors.AGENCY_STUDENT_NOT_EXIST_ERROR);
        }
        if(studentId==null){
            studentId = studentList.get(0).getId();
        }
        AgencyUserStudent agencyUserStudent = agencyUserStudentMapper.selectByAgencycClassIdAndStudentIdAndRelation(agencyClassInvitationCode.getAgencyClassId(),
                studentId,relation);
        if(agencyUserStudent!=null){
            AgencyException.raise(AgencyErrors.AGENCY_STUDENT_RELATION_IS_EXIST_ERROR);
        }
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
        AgencyStudent agencyStudent = agencyStudentMapper.selectByPrimaryKey(registerForm.getStudentId());

        AgencyUser agencyUser = new AgencyUser();
        agencyUser.setAgencyClassId(agencyStudent.getAgencyClassId());
        agencyUser.setUserId(registerForm.getUserId());
        agencyUser.setRole(registerForm.getRole());
        agencyUser.setState(true);
        agencyUser.setCreatedTime(DateUtil.currentTime());
        agencyUser.setUpdatedTime(DateUtil.currentTime());
        agencyUserMapper.insert(agencyUser);

        AgencyUserStudent userStudent = new AgencyUserStudent();
        userStudent.setAgencyClassId(agencyStudent.getAgencyClassId());
        userStudent.setUserId(registerForm.getUserId());
        userStudent.setAgencyUserId(agencyUser.getId());
        userStudent.setStudentId(registerForm.getStudentId());
        userStudent.setStudentName(agencyStudentMapper.selectByPrimaryKey(registerForm.getStudentId()).getName());
        userStudent.setState(true);
        userStudent.setRelation(registerForm.getRelation());
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

    @Override
    public AgencyClassShowDto getAgencyClassShowList(Long agencyClassId,int offset,int size){
        List<ClassShow> showList = classShowMapper.selectByClassId(agencyClassId, offset, size);
        AgencyClassShowDto showDto = new AgencyClassShowDto();
        List<AgencyClassShowDetailDto> list = new ArrayList<>();
        for(ClassShow classShow:showList){
            AgencyClassShowDetailDto showDetailDto = new AgencyClassShowDetailDto();
            showDetailDto.setId(classShow.getId());
            AgencyResult<UserDto> result = userFeign.getUserInfo(classShow.getUserId());
            if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
                throw new AgencyException(new AgencyErrorCode(result.getStatus(),result.getMessage()));
            }
            UserDto userDto = result.getData();
            showDetailDto.setAvatar(userDto.getAvatar());
            showDetailDto.setName(userDto.getName());
            showDetailDto.setCreatedTime(DateUtil.formatDate(classShow.getCreatedTime(),DateUtil.DEFAULT_DATE_FORMAT));
            showDetailDto.setContent(classShow.getContent());
            List<ClassShowImg> classShowImgList = classShowImgMapper.selectByShowId(classShow.getId());
            List<String> imgList = new ArrayList<>();
            String movieUrl = null;
            for(ClassShowImg classShowImg:classShowImgList){
                if(classShowImg.getType()==1){
                    imgList.add(classShowImg.getImg());
                }else {
                    movieUrl = classShowImg.getImg();
                }
            }
            showDetailDto.setMovieUrl(movieUrl);
            showDetailDto.setImgList(imgList);
            list.add(showDetailDto);
        }
        showDto.setShowList(list);
        showDto.setClassName(agencyClassMapper.selectByPrimaryKey(agencyClassId).getName());

        return showDto;
    }

    @Override
    public List<AgencyShowDto> getAgencyShowList(Long agencyClassId, int isBanner,int offset,int size){
        AgencyClass agencyClass = agencyClassMapper.selectByPrimaryKey(agencyClassId);
        if(agencyClass==null){
            AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOT_EXIST_ERROR);
        }
        List<AgencyShow> list = agencyShowMapper.selectByAgencyId(agencyClass.getAgencyId(), isBanner, offset, size);
        List<AgencyShowDto> showDtoList = new ArrayList<>();
        for(AgencyShow agencyShow : list){
            AgencyShowDto agencyShowDto = new AgencyShowDto();
            agencyShowDto.setId(agencyShow.getId());
            agencyShowDto.setTitle(agencyShow.getTitle());
            agencyShowDto.setAuthor(agencyShow.getAuthor());
            agencyShowDto.setContent(agencyShow.getContent());
            agencyShowDto.setImg(agencyShow.getImg());
            agencyShowDto.setReadCount(agencyShow.getReadCount());
            agencyShowDto.setCreateTime(DateUtil.formatDate(agencyShow.getCreatedTime(),DateUtil.DEFAULT_DATE_FORMAT));
            showDtoList.add(agencyShowDto);
        }
        return showDtoList;
    }

    @Override
    public List<AgencyNoticeDto> getClassNoticeList(Long agencyClassId, int isReceipt, int offset, int size){
        List<AgencyClassNotice> list = noticeMapper.selectByClassIdAndIsReceipt(agencyClassId,isReceipt,offset,size);
        List<AgencyNoticeDto> agencyNoticeDtoList = new ArrayList<>();
        for(AgencyClassNotice agencyClassNotice:list){
            AgencyNoticeDto agencyNoticeDto = new AgencyNoticeDto();
            agencyNoticeDto.setId(agencyClassNotice.getId());
            agencyNoticeDto.setCreatedTime(DateUtil.formatDate(agencyClassNotice.getCreatedTime(),DateUtil.DEFAULT_TIME_MINUTE));
            agencyNoticeDto.setContent(agencyClassNotice.getContent());
            agencyNoticeDto.setTitle(agencyClassNotice.getTitle());
            agencyNoticeDto.setReadStatus(agencyClassNotice.getIsRead()?1:0);
            ClassNoticeReceipt noticeReceipt = noticeReceiptMapper.selectByNoticeId(agencyClassNotice.getId());
            if(noticeReceipt==null){
                agencyNoticeDto.setReceiptStatus(Constants.CLASS_NOTICE_RECEIPT_STATUS_NO);
            }else {
                agencyNoticeDto.setReceiptStatus(Constants.CLASS_NOTICE_RECEIPT_STATUS_YES);

            }
            int fileStatus = 0;
            int imgStatus = 0;
            List<ClassNoticeFile> fileList = noticeFileMapper.selectByNoticeId(agencyClassNotice.getId());
            for(ClassNoticeFile classNoticeFile:fileList){
                if(classNoticeFile.getType()==2){
                    fileStatus = 1;
                    continue;
                }else {
                    imgStatus = 1;
                    continue;
                }
            }
            agencyNoticeDto.setFileStatus(fileStatus);
            agencyNoticeDto.setImgStatus(imgStatus);
            agencyNoticeDtoList.add(agencyNoticeDto);
        }
        return agencyNoticeDtoList;
    }

    @Override
    public AgencyNoticeDetailDto getClassNoticeDetail(Long noticeId){
        AgencyNoticeDetailDto agencyNoticeDetailDto = new AgencyNoticeDetailDto();
        AgencyClassNotice agencyClassNotice = noticeMapper.selectByPrimaryKey(noticeId);
        agencyNoticeDetailDto.setId(agencyClassNotice.getId());
        agencyNoticeDetailDto.setCreatedTime(DateUtil.formatDate(agencyClassNotice.getCreatedTime(),DateUtil.DEFAULT_TIME_MINUTE));
        agencyNoticeDetailDto.setContent(agencyClassNotice.getContent());
        agencyNoticeDetailDto.setTitle(agencyClassNotice.getTitle());

        AgencyResult<UserDto> result = userFeign.getUserInfo(agencyClassNotice.getUserId());
        if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
            throw new AgencyException(new AgencyErrorCode(result.getStatus(),result.getMessage()));
        }
        UserDto userDto = result.getData();
        agencyNoticeDetailDto.setName(userDto.getName());
        agencyNoticeDetailDto.setUserId(userDto.getUserId());
        agencyNoticeDetailDto.setAvatar(userDto.getAvatar());
        agencyNoticeDetailDto.setGroupId(agencyClassMapper.selectByPrimaryKey(agencyClassNotice.getAgencyClassId()).getGroupId());
        List<ClassNoticeFile> list = noticeFileMapper.selectByNoticeId(agencyClassNotice.getId());
        List<String> imgList = new ArrayList<>();
        for(ClassNoticeFile classNoticeFile:list){
            if(classNoticeFile.getType()==2){
                agencyNoticeDetailDto.setFileUrl(classNoticeFile.getFile());
            }else {
                imgList.add(classNoticeFile.getFile());
            }
        }
        agencyNoticeDetailDto.setImgList(imgList);
        agencyClassNotice.setIsRead(true);
        agencyClassNotice.setUpdatedTime(DateUtil.currentTime());
        noticeMapper.updateByPrimaryKey(agencyClassNotice);
        return agencyNoticeDetailDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void receiptNotice(NoticeReceiptForm noticeReceiptForm){
        AgencyClassNotice classNotice = noticeMapper.selectByPrimaryKey(noticeReceiptForm.getNoticeId());
        if(classNotice==null){
            AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOTICE_NOT_EXIST_ERROR);
        }
        ClassNoticeReceipt noticeReceipt = noticeReceiptMapper.selectByNoticeId(classNotice.getId());
        if(noticeReceipt!=null){
            AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOTICE_IS_RECEIPT_ERROR);
        }
        noticeReceipt = new ClassNoticeReceipt();
        noticeReceipt.setNoticeId(noticeReceiptForm.getNoticeId());
        noticeReceipt.setUserId(noticeReceiptForm.getUserId());
        noticeReceipt.setName(noticeReceiptForm.getName());
        noticeReceipt.setReceiptContent(noticeReceiptForm.getReceiptContent());
        noticeReceipt.setState(true);
        noticeReceipt.setUpdatedTime(DateUtil.currentTime());
        noticeReceipt.setCreatedTime(DateUtil.currentTime());
        noticeReceiptMapper.insert(noticeReceipt);

        classNotice.setIsReceipt(true);
        classNotice.setUpdatedTime(DateUtil.currentTime());
        noticeMapper.updateByPrimaryKey(classNotice);
    }
    @Override
    public List<UserNoticeFileCollection> getCollectList(String userId){
        return collectionMapper.selectByUserId(userId);
    }

    @Override
    public void noticeFileCollection(NoticeFileCollectionForm noticeFileCollectionForm){
        UserNoticeFileCollection userNoticeFileCollection = new UserNoticeFileCollection();
        userNoticeFileCollection.setNoticeId(noticeFileCollectionForm.getNoticeId());
        userNoticeFileCollection.setFile(noticeFileCollectionForm.getFileUrl());
        userNoticeFileCollection.setUserId(noticeFileCollectionForm.getUserId());
        userNoticeFileCollection.setUserName(noticeFileCollectionForm.getName());
        userNoticeFileCollection.setState(true);
        userNoticeFileCollection.setCreatedTime(DateUtil.currentTime());
        userNoticeFileCollection.setUpdatedTime(DateUtil.currentTime());
        collectionMapper.insert(userNoticeFileCollection);
    }

}
