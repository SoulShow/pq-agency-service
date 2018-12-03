package com.pq.agency.service;


import com.pq.agency.dto.*;
import com.pq.agency.entity.*;
import com.pq.agency.param.AgencyUserRegisterForm;
import com.pq.agency.param.NoticeFileCollectionForm;
import com.pq.agency.param.NoticeReceiptForm;

import java.util.List;

/**
 * 机构班级服务
 * @author liutao
 */
public interface AgencyClassService {


    /**
     * check邀请码、学生
     * @param studentName
     * @param invitationCode
     * @param phone
     * @return
     */
    void checkInvitationCodeAndStudent(String phone, String invitationCode,String studentName);


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
     * @param classId
     * @return
     */
    AgencyClass getAgencyClass(Long agencyId, Long gradeId,Long classId);

    /**
     * check学生
     * @param agencyId
     * @param gradeId
     * @param classId
     * @param studentId
     * @return
     */
    Boolean checkStudent(Long agencyId, Long gradeId, Long classId, Long studentId);

    /**
     * 获取学生信息
     * @param agencyId
     * @param gradeId
     * @param classId
     * @param studentName
     * @return
     */
    List<AgencyStudent> getStudentInfo(Long agencyId, Long gradeId, Long classId, String studentName);

    /**
     * 创建机构用户
     * @param registerForm
     */
    void createUser(AgencyUserRegisterForm registerForm);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    List<AgencyUserDto> getAgencyUserInfo(String userId);


    /**
     * 获取用户classId
     * @param userId
     * @return
     */
    List<Long> getUserClassId(String userId);

    /**
     * 班级风采
     * @param agencyClassId
     * @param offset
     * @param size
     * @return
     */
    AgencyClassShowDto getAgencyClassShowList(Long agencyClassId,int offset,int size);


    /**
     * 校园风采列表
     * @param agencyClassId
     * @param isBanner
     * @param offset
     * @param size
     * @return
     */
    List<AgencyShowDto> getAgencyShowList(Long agencyClassId,int isBanner,int offset,int size);

    /**
     * 获取班级通知
     * @param agencyClassId
     * @param userId
     * @param isReceipt
     * @param offset
     * @param size
     * @return
     */
    List<AgencyNoticeDto> getClassNoticeList(Long agencyClassId, String userId, int isReceipt, int offset, int size);


    /**
     * 通知详情
     * @param noticeId
     * @param userId
     * @return
     */
    AgencyNoticeDetailDto getClassNoticeDetail(Long noticeId,String userId);

    /**
     * 通知回执
     * @param noticeReceiptForm
     * @return
     */
    void receiptNotice(NoticeReceiptForm noticeReceiptForm);

    /**
     * 通知文件收藏列表
     * @param userId
     * @return
     */
    List<UserNoticeFileCollectionDto> getCollectList(String userId);

    /**
     * 收藏
     * @param noticeFileCollectionForm
     */
    void noticeFileCollection(NoticeFileCollectionForm noticeFileCollectionForm);

    /**
     * 删除收藏
     * @param id
     * @param userId
     */
    void deleteCollection(Long id,String userId);

    /**
     * 获取班级课程表
     * @param agencyClassId
     * @return
     */
    List<AgencyClassSchedule> getScheduleList(Long agencyClassId);


    /**
     * 获取任务列表
     * @param agencyClassId
     * @param userId
     * @param offset
     * @param size
     * @return
     */
    List<ClassTaskDto> getTaskList(Long agencyClassId,String userId, int offset, int size);

    /**
     * 获取班级任务详情
     * @param taskId
     * @param userId
     * @return
     */
    ClassTaskDetailDto getTaskDetail(Long taskId,String userId);


    /**
     * 获取关系列表
     * @param invitationCode
     * @param studentName
     * @return
     */
    List<String> getUserStudentRelation(String invitationCode,String studentName);


}
