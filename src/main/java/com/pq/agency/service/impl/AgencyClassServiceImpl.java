package com.pq.agency.service.impl;

import com.alibaba.fastjson.JSON;
import com.pq.agency.dto.*;
import com.pq.agency.entity.*;
import com.pq.agency.exception.AgencyErrorCode;
import com.pq.agency.exception.AgencyErrors;
import com.pq.agency.exception.AgencyException;
import com.pq.agency.feign.UserFeign;
import com.pq.agency.mapper.*;
import com.pq.agency.param.*;
import com.pq.agency.service.AgencyClassService;
import com.pq.agency.utils.AgencyResult;
import com.pq.agency.utils.Constants;
import com.pq.common.constants.CommonConstants;
import com.pq.common.exception.CommonErrors;
import com.pq.common.util.DateUtil;
import com.pq.common.util.HttpUtil;
import com.pq.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    private AgencyClassScheduleMapper classScheduleMapper;
    @Autowired
    private ClassTaskMapper classTaskMapper;
    @Autowired
    private ClassTaskImgMapper classTaskImgMapper;
    @Autowired
    private ClassNoticeReadLogMapper noticeReadLogMapper;
    @Autowired
    private ClassTaskReadLogMapper taskReadLogMapper;
    @Autowired
    private AgencyClassVoteMapper classVoteMapper;
    @Autowired
    private ClassVoteImgMapper voteImgMapper;
    @Autowired
    private ClassVoteOptionMapper voteOptionMapper;
    @Autowired
    private ClassVoteSelectedMapper voteSelectedMapper;
    @Autowired
    private ClassVoteSelectedOptionMapper voteSelectedOptionMapper;
    @Autowired
    private AgencyGroupMapper agencyGroupMapper;
    @Autowired
    private AgencyGroupMemberMapper groupMemberMapper;
    @Value("${php.url}")
    private String phpUrl;

    @Override
    public void checkInvitationCodeAndStudent(String phone, String invitationCode, String studentName){

        AgencyClassInvitationCode agencyClassInvitationCode = invitationCodeMapper.selectByCode(invitationCode);
        if(agencyClassInvitationCode==null){
            AgencyException.raise(AgencyErrors.INVITATION_CODE_ERROR);
        }
        if(studentName==null){
            AgencyException.raise(AgencyErrors.AGENCY_STUDENT_NOT_NULL_ERROR);
        }
        List<AgencyStudent> studentList = agencyStudentMapper.selectByAgencyClassIdAndName(agencyClassInvitationCode.getAgencyClassId(),studentName);
        if(studentList==null||studentList.size()==0){
            AgencyException.raise(AgencyErrors.AGENCY_STUDENT_NOT_EXIST_ERROR);
        }
    }
    @Override
    public List<String> getUserStudentRelation(String invitationCode,String studentName){
        AgencyClassInvitationCode agencyClassInvitationCode = invitationCodeMapper.selectByCode(invitationCode);
        if(agencyClassInvitationCode==null){
            AgencyException.raise(AgencyErrors.INVITATION_CODE_ERROR);
        }
        List<AgencyStudent> studentList = agencyStudentMapper.selectByAgencyClassIdAndName(agencyClassInvitationCode.getAgencyClassId(),studentName);
        if(studentList==null||studentList.size()==0){
            AgencyException.raise(AgencyErrors.AGENCY_STUDENT_NOT_EXIST_ERROR);
        }
        List<AgencyUserStudent> userStudentList = agencyUserStudentMapper.selectByAgencycClassIdAndStudentId(agencyClassInvitationCode.getAgencyClassId(),
                studentList.get(0).getId());
        List<String> relationList = new ArrayList<>();
        for(AgencyUserStudent userStudent : userStudentList){
            relationList.add(userStudent.getRelation());
        }
        return relationList;
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
        AgencyClassInvitationCode agencyClassInvitationCode = invitationCodeMapper.selectByCode(registerForm.getInvitationCode());
        if(agencyClassInvitationCode==null){
            AgencyException.raise(AgencyErrors.INVITATION_CODE_ERROR);
        }
        List<AgencyStudent> studentList = agencyStudentMapper.selectByAgencyClassIdAndName(agencyClassInvitationCode.getAgencyClassId(),
                registerForm.getStudentName());

        if(studentList==null||studentList.size()==0){
            AgencyException.raise(AgencyErrors.AGENCY_STUDENT_NOT_EXIST_ERROR);
        }
        AgencyUser agencyUser = new AgencyUser();
        agencyUser.setAgencyClassId(agencyClassInvitationCode.getAgencyClassId());
        agencyUser.setUserId(registerForm.getUserId());
        agencyUser.setRole(registerForm.getRole());
        agencyUser.setState(true);
        agencyUser.setCreatedTime(DateUtil.currentTime());
        agencyUser.setUpdatedTime(DateUtil.currentTime());
        agencyUserMapper.insert(agencyUser);

        AgencyUserStudent userStudent = new AgencyUserStudent();
        userStudent.setAgencyClassId(agencyClassInvitationCode.getAgencyClassId());
        userStudent.setUserId(registerForm.getUserId());
        userStudent.setAgencyUserId(agencyUser.getId());
        userStudent.setStudentId(studentList.get(0).getId());
        userStudent.setStudentName(registerForm.getStudentName());
        userStudent.setState(true);
        userStudent.setRelation(registerForm.getRelation());
        userStudent.setCreatedTime(DateUtil.currentTime());
        userStudent.setUpdatedTime(DateUtil.currentTime());
        agencyUserStudentMapper.insert(userStudent);

        HashMap<String, String> paramMap = new HashMap<>();
        AgencyResult<UserDto> result = userFeign.getUserInfo(registerForm.getUserId());
        if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
            throw new AgencyException(new AgencyErrorCode(result.getStatus(),result.getMessage()));
        }
        UserDto userDto = result.getData();
        paramMap.put("hxGroupId", agencyClassMapper.selectByPrimaryKey(agencyClassInvitationCode.getAgencyClassId()).getGroupId().toString());
        paramMap.put("userHxId", userDto.getUsername());
        try {
            String huanxResult = HttpUtil.sendJson(phpUrl+"addHxGroup",new HashMap<>(),JSON.toJSONString(paramMap));
            AgencyResult userResult = JSON.parseObject(huanxResult,AgencyResult.class);
            if(userResult==null||!CommonErrors.SUCCESS.getErrorCode().equals(userResult.getStatus())){
                AgencyException.raise(AgencyErrors.AGENCY_USER_ADD_GROUP_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AgencyException.raise(AgencyErrors.AGENCY_USER_ADD_GROUP_ERROR);
        }
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
           if(agencyClass==null){
               AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOT_EXIST_ERROR);
           }
           agencyUserDto.setHxGroupId(agencyClass.getGroupId());
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
    public List<AgencyNoticeDto> getClassNoticeList(Long agencyClassId, String userId, Long studentId, int isReceipt, int offset, int size){
        List<AgencyClassNotice> list = noticeMapper.selectByClassIdAndIsReceipt(agencyClassId,isReceipt,offset,size);
        List<AgencyNoticeDto> agencyNoticeDtoList = new ArrayList<>();
        for(AgencyClassNotice agencyClassNotice:list){
            AgencyNoticeDto agencyNoticeDto = new AgencyNoticeDto();
            agencyNoticeDto.setId(agencyClassNotice.getId());
            agencyNoticeDto.setCreatedTime(DateUtil.formatDate(agencyClassNotice.getCreatedTime(),DateUtil.DEFAULT_TIME_MINUTE));
            agencyNoticeDto.setContent(agencyClassNotice.getContent());
            agencyNoticeDto.setTitle(agencyClassNotice.getTitle());
            ClassNoticeReadLog readLog = noticeReadLogMapper.selectByUserIdAndNoticeId(userId,agencyClassNotice.getId());
            agencyNoticeDto.setReadStatus(readLog==null?0:1);
            ClassNoticeReceipt noticeReceipt = noticeReceiptMapper.selectByNoticeIdAndUserIdAndStudentId(agencyClassNotice.getId(),
                    userId,studentId);
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
    public AgencyNoticeDetailDto getClassNoticeDetail(Long noticeId,String userId,Long studentId){
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
        agencyNoticeDetailDto.setGroupId(agencyClassMapper.selectByPrimaryKey(agencyClassNotice.getAgencyClassId()).getGroupId().toString());
        List<ClassNoticeFile> list = noticeFileMapper.selectByNoticeId(agencyClassNotice.getId());
        List<String> imgList = new ArrayList<>();
        for(ClassNoticeFile classNoticeFile:list){
            if(classNoticeFile.getType()==2){
                agencyNoticeDetailDto.setFileUrl(classNoticeFile.getFile());
                agencyNoticeDetailDto.setFileName(classNoticeFile.getFileName());
                agencyNoticeDetailDto.setFileSize(classNoticeFile.getFileSize());
                agencyNoticeDetailDto.setSuffix(classNoticeFile.getSuffix());
            }else {
                imgList.add(classNoticeFile.getFile());
            }
        }
        ClassNoticeReceipt noticeReceipt = noticeReceiptMapper.selectByNoticeIdAndUserIdAndStudentId(agencyClassNotice.getId(),
                userId, studentId);
        if(noticeReceipt==null){
            agencyNoticeDetailDto.setReceiptStatus(Constants.CLASS_NOTICE_RECEIPT_STATUS_NO);
        }else {
            agencyNoticeDetailDto.setReceiptStatus(Constants.CLASS_NOTICE_RECEIPT_STATUS_YES);

        }
        agencyNoticeDetailDto.setImgList(imgList);
        ClassNoticeReadLog readLog = noticeReadLogMapper.selectByUserIdAndNoticeId(userId,noticeId);
        if(readLog==null){
            readLog = new ClassNoticeReadLog();
            readLog.setUserId(userId);
            readLog.setNoticeId(noticeId); readLog.setState(true);
            readLog.setUpdatedTime(DateUtil.currentTime());
            readLog.setCreatedTime(DateUtil.currentTime());
            noticeReadLogMapper.insert(readLog);
        }
        return agencyNoticeDetailDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void receiptNotice(NoticeReceiptForm noticeReceiptForm){
        AgencyClassNotice classNotice = noticeMapper.selectByPrimaryKey(noticeReceiptForm.getNoticeId());
        if(classNotice==null){
            AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOTICE_NOT_EXIST_ERROR);
        }
        ClassNoticeReceipt noticeReceipt = noticeReceiptMapper.selectByNoticeIdAndUserIdAndStudentId(classNotice.getId(),
                noticeReceiptForm.getUserId(), noticeReceiptForm.getStudentId());
        if(noticeReceipt!=null){
            AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOTICE_IS_RECEIPT_ERROR);
        }
        noticeReceipt = new ClassNoticeReceipt();
        noticeReceipt.setNoticeId(noticeReceiptForm.getNoticeId());
        noticeReceipt.setUserId(noticeReceiptForm.getUserId());
        noticeReceipt.setName(noticeReceiptForm.getName());
        noticeReceipt.setReceiptContent(noticeReceiptForm.getReceiptContent());
        noticeReceipt.setStudentId(noticeReceiptForm.getStudentId());
        noticeReceipt.setState(true);
        noticeReceipt.setUpdatedTime(DateUtil.currentTime());
        noticeReceipt.setCreatedTime(DateUtil.currentTime());
        noticeReceiptMapper.insert(noticeReceipt);

        classNotice.setIsReceipt(true);
        classNotice.setUpdatedTime(DateUtil.currentTime());
        noticeMapper.updateByPrimaryKey(classNotice);
    }
    @Override
    public List<UserNoticeFileCollectionDto> getCollectList(String userId,Long studentId, int offset,int size){
        List<UserNoticeFileCollection> list = collectionMapper.selectByUserIdAndStudentId(userId,studentId,offset,size);
        List<UserNoticeFileCollectionDto> collectionDtoList = new ArrayList<>();
        for(UserNoticeFileCollection collection:list){
            UserNoticeFileCollectionDto collectionDto = new UserNoticeFileCollectionDto();
            collectionDto.setId(collection.getId());
            AgencyClassNotice notice = noticeMapper.selectByPrimaryKey(collection.getNoticeId());
            AgencyResult<UserDto> result = userFeign.getUserInfo(notice.getUserId());
            if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
                throw new AgencyException(new AgencyErrorCode(result.getStatus(),result.getMessage()));
            }
            collectionDto.setUserId(notice.getUserId());
            collectionDto.setUserName(result.getData().getName());
            collectionDto.setNoticeId(collection.getNoticeId());
            collectionDto.setFile(collection.getFile());
            collectionDto.setFileName(collection.getFileName());
            collectionDto.setFileSize(collection.getFileSize());
            collectionDto.setSuffix(collection.getSuffix());
            collectionDto.setCreateTime(DateUtil.formatDate(collection.getCreatedTime(),DateUtil.DEFAULT_DATETIME_FORMAT));
            collectionDtoList.add(collectionDto);
        }
        return collectionDtoList;
    }

    @Override
    public void noticeFileCollection(NoticeFileCollectionForm noticeFileCollectionForm){
        UserNoticeFileCollection userNoticeFileCollection = new UserNoticeFileCollection();
        userNoticeFileCollection.setNoticeId(noticeFileCollectionForm.getNoticeId());
        userNoticeFileCollection.setFile(noticeFileCollectionForm.getFileUrl());
        userNoticeFileCollection.setFileName(noticeFileCollectionForm.getFileName());
        userNoticeFileCollection.setFileSize(noticeFileCollectionForm.getFileSize());
        userNoticeFileCollection.setUserId(noticeFileCollectionForm.getUserId());
        userNoticeFileCollection.setStudentId(noticeFileCollectionForm.getStudentId());
        userNoticeFileCollection.setUserName(noticeFileCollectionForm.getName());
        userNoticeFileCollection.setSuffix(noticeFileCollectionForm.getSuffix());
        userNoticeFileCollection.setState(true);
        userNoticeFileCollection.setCreatedTime(DateUtil.currentTime());
        userNoticeFileCollection.setUpdatedTime(DateUtil.currentTime());
        collectionMapper.insert(userNoticeFileCollection);
    }
    @Override
    public void deleteCollection(Long id,String userId,Long studentId){
        UserNoticeFileCollection collection = collectionMapper.selectByPrimaryKey(id);
        if(collection==null||!userId.equals(collection.getUserId())|| !studentId.equals(collection.getStudentId())){
            AgencyException.raise(AgencyErrors.AGENCY_COLLECTION_NOT_EXIST_ERROR);
        }
        collection.setState(false);
        collection.setUpdatedTime(DateUtil.currentTime());
        collectionMapper.updateByPrimaryKey(collection);
    }

    @Override
    public List<AgencyClassSchedule> getScheduleList(Long agencyClassId){
        return classScheduleMapper.selectByClassId(agencyClassId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public  void createTask(TaskCreateForm taskCreateForm){
        ClassTask classTask = new ClassTask();
        classTask.setUserId(taskCreateForm.getUserId());
        classTask.setAgencyClassId(taskCreateForm.getClassId());
        classTask.setTitle(taskCreateForm.getTitle());
        classTask.setContent(taskCreateForm.getContent());
        classTask.setState(true);
        classTask.setCreatedTime(DateUtil.currentTime());
        classTask.setUpdatedTime(DateUtil.currentTime());
        classTaskMapper.insert(classTask);
        for(String img : taskCreateForm.getImgList()){
            ClassTaskImg taskImg = new ClassTaskImg();
            taskImg.setTaskId(classTask.getId());
            taskImg.setImg(img);
            taskImg.setState(true);
            taskImg.setCreatedTime(DateUtil.currentTime());
            taskImg.setUpdatedTime(DateUtil.currentTime());
            classTaskImgMapper.insert(taskImg);
        }


    }


    @Override
    public List<ClassTaskDto> getTaskList(Long agencyClassId,String userId, Long studentId,int offset, int size){
        List<ClassTask> list = classTaskMapper.selectByClassId(agencyClassId,offset,size);
        List<ClassTaskDto> taskDtoList = new ArrayList<>();
        for(ClassTask classTask:list){
            taskDtoList.add(getClassTaskDto(classTask,userId,studentId));
        }
        return taskDtoList;
    }
    @Override
    public List<ClassTaskDto> getTaskList(String userId, int offset, int size){
        List<Long> classIdList = agencyUserMapper.selectClassIdByUserId(userId);
        List<ClassTaskDto> taskDtoList = new ArrayList<>();
        List<ClassTask> taskList = classTaskMapper.selectByClassIdList(classIdList,offset,size);
        for(ClassTask classTask:taskList){
            taskDtoList.add(getClassTaskDto(classTask,userId,null));
        }
        return taskDtoList;
    }

    private ClassTaskDto getClassTaskDto(ClassTask classTask,String userId,Long studentId){
        ClassTaskDto classTaskDto = new ClassTaskDto();
        classTaskDto.setId(classTask.getId());
        AgencyResult<UserDto> result = userFeign.getUserInfo(classTask.getUserId());
        if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
            AgencyException.raise(new AgencyErrorCode(result.getStatus(),result.getMessage()));
        }
        UserDto userDto = result.getData();
        classTaskDto.setUsername(userDto.getName());
        classTaskDto.setUserId(userDto.getUserId());
        classTaskDto.setAvatar(userDto.getAvatar());
        AgencyClass agencyClass = agencyClassMapper.selectByPrimaryKey(classTask.getAgencyClassId());
        if(agencyClass==null) {
            AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOT_EXIST_ERROR);
        }
        classTaskDto.setClassName(agencyClass.getName());
        classTaskDto.setTitle(classTask.getTitle());
        classTaskDto.setCreateTime(DateUtil.formatDate(classTask.getCreatedTime(),DateUtil.DEFAULT_DATETIME_FORMAT));
        if(studentId !=null){
            ClassTaskReadLog readLog = taskReadLogMapper.selectByUserIdAndStudentIdAndTaskId(userId,studentId,classTask.getId());
            classTaskDto.setIsRead(readLog==null?0:1);
        }
        return classTaskDto;
    }

    @Override
    public ClassTaskDetailDto getTaskDetail(Long taskId,Long studentId,String userId){
        ClassTask classTask = classTaskMapper.selectByPrimaryKey(taskId);
        ClassTaskDetailDto classTaskDetailDto = new ClassTaskDetailDto();
        classTaskDetailDto.setId(classTask.getId());
        AgencyResult<UserDto> result = userFeign.getUserInfo(classTask.getUserId());
        if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
            AgencyException.raise(new AgencyErrorCode(result.getStatus(),result.getMessage()));
        }
        UserDto userDto = result.getData();
        classTaskDetailDto.setUsername(userDto.getUsername());
        classTaskDetailDto.setUserId(userDto.getUserId());
        classTaskDetailDto.setAvatar(userDto.getAvatar());
        classTaskDetailDto.setTitle(classTask.getTitle());
        classTaskDetailDto.setContent(classTask.getContent());
        classTaskDetailDto.setCreateTime(DateUtil.formatDate(classTask.getCreatedTime(),
                DateUtil.DEFAULT_DATETIME_FORMAT));

        List<String> list = new ArrayList<>();
        List<ClassTaskImg> taskImgList = classTaskImgMapper.selectByTaskId(taskId);
        for(ClassTaskImg taskImg:taskImgList){
            list.add(taskImg.getImg());
        }
        classTaskDetailDto.setImgList(list);

        if(studentId!=null){
            ClassTaskReadLog readLog = taskReadLogMapper.selectByUserIdAndStudentIdAndTaskId(userId,studentId,classTask.getId());
            if(readLog==null){
                readLog = new ClassTaskReadLog();
                readLog.setTaskId(taskId);
                readLog.setUserId(userId);
                readLog.setStudentId(studentId);
                readLog.setState(true);
                readLog.setCreatedTime(DateUtil.currentTime());
                readLog.setUpdatedTime(DateUtil.currentTime());
                taskReadLogMapper.insert(readLog);
            }
        }
        return classTaskDetailDto;
    }
    @Override
    public List<AgencyStudentDto> getTaskReadInfo(Long taskId){
        ClassTask classTask = classTaskMapper.selectByPrimaryKey(taskId);
        List<AgencyStudentDto> studentDtos = new ArrayList<>();
        List<AgencyStudent> list = agencyStudentMapper.selectByAgencyClassId(classTask.getAgencyClassId());
        for(AgencyStudent student: list){

            List<ClassTaskReadLog> readLogList = taskReadLogMapper.selectByStudentIdAndTaskId(student.getId(),classTask.getId());
            if(readLogList!=null&&readLogList.size()>0){
                continue;
            }
            AgencyStudentDto agencyStudentDto = new AgencyStudentDto();
            agencyStudentDto.setStudentId(student.getId());
            agencyStudentDto.setName(student.getName());
            agencyStudentDto.setAvatar(student.getAvatar());
            agencyStudentDto.setSex(student.getSex());
            List<ParentDto> parentList = new ArrayList<>();

            List<AgencyUserStudent> userStudentList = agencyUserStudentMapper.
                    selectByAgencycClassIdAndStudentId(classTask.getAgencyClassId(),student.getId());
            for(AgencyUserStudent userStudent : userStudentList){
                ParentDto parentDto = new ParentDto();
                parentDto.setUserId(userStudent.getUserId());
                parentDto.setName(student.getName()+userStudent.getRelation());
                AgencyResult<UserDto> result = userFeign.getUserInfo(userStudent.getUserId());
                if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
                    throw new AgencyException(new AgencyErrorCode(result.getStatus(),result.getMessage()));
                }
                UserDto userDto = result.getData();
                parentDto.setPhone(userDto.getUsername());
                parentDto.setHuanxinId(userDto.getUsername()+userDto.getRole());
                parentList.add(parentDto);
            }
            agencyStudentDto.setParentList(parentList);
            studentDtos.add(agencyStudentDto);
        }
        return studentDtos;
    }

    @Override
    public List<AgencyVoteDto> getVoteList(Long agencyClassId,String userId,Long studentId,int offset,int size){
        List<AgencyClassVote> voteList = classVoteMapper.selectByClassId(agencyClassId,offset,size);
        List<AgencyVoteDto> list = new ArrayList<>();
        for(AgencyClassVote classVote: voteList){
            AgencyVoteDto agencyVoteDto = new AgencyVoteDto();

            AgencyResult<UserDto> result = userFeign.getUserInfo(classVote.getUserId());
            if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
                throw new AgencyException(new AgencyErrorCode(result.getStatus(),result.getMessage()));
            }

            UserDto userDto = result.getData();
            agencyVoteDto.setUserId(classVote.getUserId());
            agencyVoteDto.setAvatar(userDto.getAvatar());
            agencyVoteDto.setUsername(userDto.getName());
            AgencyClass agencyClass = agencyClassMapper.selectByPrimaryKey(agencyClassId);
            if(agencyClass==null){
                AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOT_EXIST_ERROR);
            }
            agencyVoteDto.setClassName(agencyClass.getName());
            agencyVoteDto.setContent(classVote.getTitle());
            agencyVoteDto.setCreateTime(DateUtil.formatDate(classVote.getCreatedTime(),DateUtil.DEFAULT_DATETIME_FORMAT));
            agencyVoteDto.setIsVoted(0);
            agencyVoteDto.setIsSecret(classVote.getIsSecret());
            agencyVoteDto.setId(classVote.getId());
            Integer count = voteSelectedMapper.selectCountByVoteId(classVote.getId());
            agencyVoteDto.setVotedCount(count);

            ClassVoteSelected voteSelected = voteSelectedMapper.selectByVoteIdAndUserIdAndStudentId(classVote.getId(), userId, studentId);
            if(voteSelected != null){
                //已经投过票
                agencyVoteDto.setIsVoted(1);
            }
            if(DateUtil.currentTimeMillis()>classVote.getDeadline().getTime()){
                //未过期
                agencyVoteDto.setVoteStatus(0);
            }else {
                //过期
                agencyVoteDto.setVoteStatus(1);
            }
            List<VoteOptionDetailDto> detailDtos = sortOptions(getOptions(classVote.getId(),voteSelected.getId()));
            if(detailDtos!=null && detailDtos.size()>3){
                agencyVoteDto.setList(detailDtos.subList(0,3));
            }else {
                agencyVoteDto.setList(detailDtos);
            }
            list.add(agencyVoteDto);
        }
        return list;
    }

    @Override
    public AgencyVoteDetailDto getVoteDetail(Long voteId,String userId,Long studentId){
        AgencyClassVote classVote = classVoteMapper.selectByPrimaryKey(voteId);
        AgencyVoteDetailDto  agencyVoteDetailDto = new AgencyVoteDetailDto();

        AgencyResult<UserDto> result = userFeign.getUserInfo(classVote.getUserId());
        if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
            throw new AgencyException(new AgencyErrorCode(result.getStatus(),result.getMessage()));
        }
        UserDto userDto = result.getData();
        agencyVoteDetailDto.setId(classVote.getId());
        agencyVoteDetailDto.setUserId(classVote.getUserId());
        agencyVoteDetailDto.setAvatar(userDto.getAvatar());
        agencyVoteDetailDto.setUsername(userDto.getName());
        AgencyClass agencyClass = agencyClassMapper.selectByPrimaryKey(classVote.getAgencyClassId());
        if(agencyClass==null){
            AgencyException.raise(AgencyErrors.AGENCY_CLASS_NOT_EXIST_ERROR);
        }
        agencyVoteDetailDto.setContent(classVote.getTitle());
        agencyVoteDetailDto.setDeadLine(DateUtil.formatDate(classVote.getDeadline(),DateUtil.DEFAULT_DATETIME_FORMAT));
        agencyVoteDetailDto.setIsVoted(0);
        agencyVoteDetailDto.setIsSecret(classVote.getIsSecret());
        agencyVoteDetailDto.setType(classVote.getType());
        Integer totalCount = voteSelectedMapper.selectCountByVoteId(classVote.getId());
        agencyVoteDetailDto.setVotedCount(totalCount==null?0:totalCount);

        ClassVoteSelected voteSelected = voteSelectedMapper.selectByVoteIdAndUserIdAndStudentId(classVote.getId(), userId, studentId);
        if(voteSelected != null){
            //已经投过票
            agencyVoteDetailDto.setIsVoted(1);
        }
        if(DateUtil.currentTimeMillis()>classVote.getDeadline().getTime()){
            //未过期
            agencyVoteDetailDto.setVoteStatus(0);
        }else {
            //过期
            agencyVoteDetailDto.setVoteStatus(1);
        }
        List<ClassVoteImg> imgList = voteImgMapper.selectByVoteId(voteId);
        List<String> imgs = new ArrayList<>();
        for(ClassVoteImg voteImg: imgList){
            imgs.add(voteImg.getImg());
        }
        agencyVoteDetailDto.setImgList(imgs);

        agencyVoteDetailDto.setOptionList(getOptions(voteId,voteSelected.getId()));
        return agencyVoteDetailDto;
    }

    private List<VoteOptionDetailDto> getOptions(Long voteId,Long selectedId){
        List<ClassVoteOption> optionList = voteOptionMapper.selectByVoteId(voteId);
        List<VoteOptionDetailDto> options = new ArrayList<>();
        for(ClassVoteOption option : optionList){
            VoteOptionDetailDto optionDto = new VoteOptionDetailDto();
            optionDto.setOption(option.getItem());
            Integer count = voteSelectedOptionMapper.selectCountByVoteIdAndOption(voteId,option.getItem());
            optionDto.setCount(count==null?0:count);
            ClassVoteSelectedOption selectedOption = voteSelectedOptionMapper.selectByVoteIdAndSelectedId(voteId,selectedId);
            if(selectedOption!=null&&selectedOption.getItem().equals(option.getItem())){
                optionDto.setIsVoted(1);
            }else {
                optionDto.setIsVoted(0);
            }
            options.add(optionDto);
        }
        return options;
    }

    private List<VoteOptionDetailDto> sortOptions(List<VoteOptionDetailDto> list){
        Collections.sort(list, new Comparator<VoteOptionDetailDto>() {
            @Override
            public int compare(VoteOptionDetailDto arg0, VoteOptionDetailDto arg1) {
                int hits0 = arg0.getCount();
                int hits1 = arg1.getCount();
                if (hits1 > hits0) {
                    return 1;
                } else if (hits1 == hits0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return list;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void voteSelected(VoteSelectedForm voteSelectedForm){
        ClassVoteSelected classVoteSelected = voteSelectedMapper.selectByVoteIdAndUserIdAndStudentId(voteSelectedForm.getVoteId(),
                voteSelectedForm.getUserId(),voteSelectedForm.getStudentId());
        if(classVoteSelected!=null){
            AgencyException.raise(AgencyErrors.AGENCY_CLASS_VOTE_EXIST_ERROR);
        }
        classVoteSelected = new ClassVoteSelected();
        classVoteSelected.setVoteId(voteSelectedForm.getVoteId());
        classVoteSelected.setUserId(voteSelectedForm.getUserId());
        classVoteSelected.setStudentId(voteSelectedForm.getStudentId());
        classVoteSelected.setState(true);
        classVoteSelected.setName(voteSelectedForm.getName());
        classVoteSelected.setCreatedTime(DateUtil.currentTime());
        classVoteSelected.setUpdatedTime(DateUtil.currentTime());
        voteSelectedMapper.insert(classVoteSelected);
        for(String option:voteSelectedForm.getOptions()){
            ClassVoteSelectedOption selectedOption = new ClassVoteSelectedOption();
            selectedOption.setItem(option);
            selectedOption.setSelectedId(classVoteSelected.getId());
            selectedOption.setVoteId(voteSelectedForm.getVoteId());
            selectedOption.setState(true);
            selectedOption.setCreatedTime(DateUtil.currentTime());
            selectedOption.setUpdatedTime(DateUtil.currentTime());
            voteSelectedOptionMapper.insert(selectedOption);
        }
    }

    @Override
    public List<VoteOptionDto> getVoteStatistics(Long voteId){
        List<VoteOptionDto> list = new ArrayList<>();
        List<ClassVoteOption> optionList = voteOptionMapper.selectByVoteId(voteId);
        for(ClassVoteOption option : optionList){
            VoteOptionDto optionDto = new VoteOptionDto();
            optionDto.setOption(option.getItem());
            Integer count = voteSelectedOptionMapper.selectCountByVoteIdAndOption(voteId,option.getItem());
            optionDto.setCount(count==null?0:count);

            List<OptionUserDto> userDtos = new ArrayList<>();
            List<ClassVoteSelected> selectedList = voteSelectedMapper.selectByOptionAndVoteId(option.getItem(),option.getVoteId());
            for(ClassVoteSelected voteSelected: selectedList){
                OptionUserDto optionUserDto = new OptionUserDto();
                optionUserDto.setUserId(voteSelected.getUserId());
                optionUserDto.setUsername(voteSelected.getName());
                AgencyResult<UserDto> result = userFeign.getUserInfo(voteSelected.getUserId());
                if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
                    throw new AgencyException(new AgencyErrorCode(result.getStatus(),result.getMessage()));
                }
                UserDto userDto = result.getData();
                optionUserDto.setAvatar(userDto.getAvatar());
                userDtos.add(optionUserDto);
            }
            optionDto.setList(userDtos);
            list.add(optionDto);
        }
        return list;
    }

    @Override
    public List<AgencyClassInfoDto> getClassInfo(String userId,Long studentId){
        List<AgencyClassInfoDto> classInfoDtos = new ArrayList<>();
        List<AgencyGroupMember> memberList = groupMemberMapper.selectByStudentIdOrUserId(studentId,userId);
        for(AgencyGroupMember groupMember:memberList){
            AgencyGroup agencyGroup = agencyGroupMapper.selectByPrimaryKey(groupMember.getGroupId());
            AgencyClassInfoDto agencyClassInfoDto = new AgencyClassInfoDto();
            agencyClassInfoDto.setId(agencyGroup.getId());
            agencyClassInfoDto.setGroupId(agencyGroup.getHxGroupId());
            agencyClassInfoDto.setImg(agencyGroup.getImg());
            agencyClassInfoDto.setName(agencyGroup.getName());
            agencyClassInfoDto.setType(Constants.AGENCY_GROUP_TYPE_GROUP);
            if(agencyGroup.getClassId()!=null){
                agencyClassInfoDto.setType(Constants.AGENCY_GROUP_TYPE_CLASS);
            }
            Integer count = groupMemberMapper.selectCountByGroupId(groupMember.getGroupId());
            agencyClassInfoDto.setCount(count);
            classInfoDtos.add(agencyClassInfoDto);
        }
        return classInfoDtos;
    }
    @Override
    public  AgencyClassInfoDto getClassUserInfo(Long groupId){

        AgencyClassInfoDto agencyClassInfoDto = new AgencyClassInfoDto();
        AgencyGroup agencyGroup = agencyGroupMapper.selectByPrimaryKey(groupId);

        agencyClassInfoDto.setId(agencyGroup.getId());
        agencyClassInfoDto.setName(agencyGroup.getName());
        agencyClassInfoDto.setImg(agencyGroup.getImg());

        List<ClassUserInfoDto> list = new ArrayList<>();
        List<AgencyGroupMember> memberList = groupMemberMapper.selectByGroupId(groupId);

        for(AgencyGroupMember groupMember : memberList){
            ClassUserInfoDto classUserInfoDto = new ClassUserInfoDto();
            if(!StringUtil.isEmpty(groupMember.getUserId()) && groupMember.getStudentId()==null){
                AgencyResult<UserDto> result = userFeign.getUserInfo(groupMember.getUserId());
                if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
                    throw new AgencyException(new AgencyErrorCode(result.getStatus(),result.getMessage()));
                }
                UserDto userDto = result.getData();
                classUserInfoDto.setAvatar(userDto.getAvatar());
                classUserInfoDto.setName(userDto.getName());
                classUserInfoDto.setRole(userDto.getRole());
                classUserInfoDto.setSex(userDto.getGender());
                classUserInfoDto.setUserId(groupMember.getUserId());
                classUserInfoDto.setHuanxinId(userDto.getUsername()+userDto.getRole());
            }
            if(StringUtil.isEmpty(groupMember.getUserId()) && groupMember.getStudentId()!=null){
                AgencyStudent agencyStudent =agencyStudentMapper.selectByPrimaryKey(groupMember.getStudentId());
                classUserInfoDto.setStudentId(agencyStudent.getId());
                classUserInfoDto.setAvatar(agencyStudent.getAvatar());
                classUserInfoDto.setName(agencyStudent.getName());
                classUserInfoDto.setSex(agencyStudent.getSex()==1?"男":"女");

                List<ParentDto> parentList = new ArrayList<>();
                List<AgencyUserStudent> userStudentList = agencyUserStudentMapper.
                        selectByAgencycClassIdAndStudentId(agencyStudent.getAgencyClassId(),agencyStudent.getId());
                for(AgencyUserStudent userStudent : userStudentList){
                    ParentDto parentDto = new ParentDto();
                    parentDto.setUserId(userStudent.getUserId());
                    parentDto.setName(agencyStudent.getName()+userStudent.getRelation());

                    AgencyResult<UserDto> result = userFeign.getUserInfo(userStudent.getUserId());
                    if(!CommonErrors.SUCCESS.getErrorCode().equals(result.getStatus())){
                        throw new AgencyException(new AgencyErrorCode(result.getStatus(),result.getMessage()));
                    }
                    UserDto userDto = result.getData();
                    parentDto.setHuanxinId(userDto.getUsername()+userDto.getRole());
                    parentList.add(parentDto);
                }
                classUserInfoDto.setParentList(parentList);
            }
            list.add(classUserInfoDto);
        }
        agencyClassInfoDto.setList(list);
        return agencyClassInfoDto;
    }

    @Override
    public List<AgencyClass> getTeacherClassList(String userId){
        List<Long> list = agencyUserMapper.selectClassIdByUserId(userId);
        List<AgencyClass> classList = new ArrayList<>();
        for(Long classId : list){
            AgencyClass agencyClass = agencyClassMapper.selectByPrimaryKey(classId);
            classList.add(agencyClass);
        }
        return classList;
    }


}
