package com.pq.agency.service;


import com.pq.agency.dto.*;
import com.pq.agency.entity.*;
import com.pq.agency.param.*;

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
     * 查询机构列表
     * @param name
     * @return
     */
    List<Agency> getAgencyList(String name);

    /**
     * 根据机构id查询机构年级
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
     * 获取用户信息
     * @param userId
     * @return
     */
    List<AgencyClassDto> getUserClassInfo(String userId);

    /**
     * 班级风采
     * @param agencyClassId
     * @param userId
     * @param offset
     * @param size
     * @return
     */
    AgencyClassShowDto getAgencyClassShowList(Long agencyClassId,String userId,int offset,int size);


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
    List<AgencyNoticeDto> getClassNoticeList(Long agencyClassId, String userId, Long studentId, int isReceipt, int offset, int size);


    /**
     * 通知详情
     * @param noticeId
     * @param userId
     * @param studentId
     * @return
     */
    AgencyNoticeDetailDto getClassNoticeDetail(Long noticeId,String userId, Long studentId);

    /**
     * 通知回执
     * @param noticeReceiptForm
     * @return
     */
    void receiptNotice(NoticeReceiptForm noticeReceiptForm);

    /**
     * 通知文件收藏列表
     * @param userId
     * @param studentId
     * @param offset
     * @param size
     * @return
     */
    List<UserNoticeFileCollectionDto> getCollectList(String userId,Long studentId,int offset,int size);

    /**
     * 收藏
     * @param noticeFileCollectionForm
     */
    void noticeFileCollection(NoticeFileCollectionForm noticeFileCollectionForm);

    /**
     * 删除收藏
     * @param id
     * @param userId
     * @param studentId
     */
    void deleteCollection(Long id,String userId, Long studentId);

    /**
     * 获取班级课程表
     * @param agencyClassId
     * @return
     */
    List<AgencyClassSchedule> getScheduleList(Long agencyClassId);

    /**
     * 老师创建班级任务
     * @param taskCreateForm
     * @return
     */
    void createTask(TaskCreateForm taskCreateForm);

    /**
     * 家长任务列表
     * @param agencyClassId
     * @param userId
     * @param studentId
     * @param offset
     * @param size
     * @return
     */
    List<ClassTaskDto> getTaskList(Long agencyClassId,String userId,Long studentId ,int offset, int size);

    /**
     * 老师班级任务
     * @param userId
     * @param offset
     * @param size
     * @return
     */
    List<ClassTaskDto> getTaskList(String userId, int offset, int size);


    /**
     * 获取班级任务详情
     * @param taskId
     * @param userId
     * @param studentId
     * @return
     */
    ClassTaskDetailDto getTaskDetail(Long taskId,Long studentId,String userId);


    /**
     * 获取任务未阅读信息
     * @param taskId
     * @return
     */
    List<AgencyStudentDto> getTaskReadInfo(Long taskId);

    /**
     * 获取关系列表
     * @param invitationCode
     * @param studentName
     * @return
     */
    List<String> getUserStudentRelation(String invitationCode,String studentName);


    /**
     * 获取投票列表
     * @param agencyClassIdList
     * @param userId
     * @param studentId
     * @param offset
     * @param size
     * @return
     */
    List<AgencyVoteDto> getVoteList(List<Long> agencyClassIdList,String userId,Long studentId,int offset,int size);

    /**
     * 获取投票详情
     * @param voteId
     * @param userId
     * @param studentId
     * @return
     */
    AgencyVoteDetailDto getVoteDetail(Long voteId,String userId,Long studentId);

    /**
     * 投票
     * @param voteSelectedForm
     */
    void voteSelected(VoteSelectedForm voteSelectedForm);

    /**
     * 投票详情
     * @param voteId
     * @return
     */
    List<VoteOptionDto> getVoteStatistics(Long voteId);


    /**
     * 获取班级信息
     * @param userId
     * @param studentId
     * @return
     */
    List<AgencyClassInfoDto> getClassInfo(String userId,Long studentId);


    /**
     * 获取班级成员信息
     * @param groupId
     * @param studentId
     * @param userId
     * @param isCreate
     * @return
     */
    AgencyClassInfoDto getClassUserInfo(Long groupId,Long studentId,String userId,int isCreate);

    /**
     * 获取老师班级
     * @param userId
     * @return
     */
    List<AgencyClassGroupDto> getTeacherClassList(String userId);

    /**
     * 开启/关闭消息免打扰
     * @param groupId
     * @param userId
     * @param studentId
     * @param status
     */
    void groupDisturb(Long groupId,String userId,Long studentId,Integer status);

    /**
     * 获取用户禁言状态
     * @param groupId
     * @param userId
     * @return
     */
    Integer getClassChatStatus(Long groupId,String userId);

    /**
     * 禁言
     * @param groupId
     * @param userId
     * @param status
     */
    void groupKeepSilent(Long groupId,String userId,int status);

    /**
     * 获取免打扰群组信息
     * @param userId
     * @param studentId
     * @return
     */
    List<AgencyClassInfoDto> getDisturbGroup(String userId,Long studentId);


    /**
     * 获取群组成员信息
     * @param groupId
     * @param name
     * @return
     */
    AgencyClassInfoDto getGroupSearchUserInfo(Long groupId,String name);

    /**
     * 获取某人的老师和班级信息
     * @param userId
     * @param studentId
     * @param role
     * @param name
     * @return
     */
    ClassAndTeacherInfoDto getClassAndTeacherInfo(String userId,Long studentId,int role,String name);


    /**
     * 创建投票信息
     * @param voteForm
     */
    void createVote(AgencyClassVoteForm voteForm);

    /**
     * 投票删除
     * @param voteId
     */
    void deleteVote(Long voteId);


    /**
     * 创建老师
     * @param teacherRegisterForm
     */
    void createTeacher(AgencyTeacherRegisterForm teacherRegisterForm);

    /**
     * 验证班主任是否存在
     * @param classList
     */
    void checkTeacherHeader(List<AgencyClassDto> classList);


    /**
     * 修改班级信息
     * @param scheduleUpdateForm
     */
    void updateSchedule(ScheduleUpdateForm scheduleUpdateForm);

    /**
     * 获取老师的班级课程
     * @param userId
     * @return
     */
    List<ClassCourseDto> getTeacherClassCourse(String userId);

    /**
     * 获取老师职务
     * @param userId
     * @return
     */
    UserCourseDto getTeacherCourse(String userId);


    /**
     * 添加、更改老师科目
     * @param teacherCourseForm
     */
    void createTeacherCourse(TeacherCourseForm teacherCourseForm);

    /**
     * 创建班级相册
     * @param classShowCreateForm
     */
    void createClassShow(ClassShowCreateForm classShowCreateForm);

    /**
     * 删除班级相册
     * @param showDelForm
     */
    void deleteClassShow(ShowDelForm showDelForm);

    /**
     * 创建群组
     * @param groupCreateForm
     */
    void createGroup(GroupCreateForm groupCreateForm);

    /**
     * 修改群组名称
     * @param groupUpdateForm
     */
    void updateGroupName(GroupUpdateForm groupUpdateForm);

    /**
     * 修改群组头像
     * @param groupUpdateForm
     */
    void updateGroupImg(GroupUpdateForm groupUpdateForm);

    /**
     * 添加群组成员
     * @param addGroupMemberForm
     */
    void addGroupMember(AddGroupMemberForm addGroupMemberForm);

    /**
     * 添加群组成员
     * @param delGroupMemberForm
     */
    void delGroupMember(DelGroupMemberForm delGroupMemberForm);

    /**
     * 解散群组
     * @param groupDeleteForm
     */
    void delGroup(GroupDeleteForm groupDeleteForm);

    /**
     * check群组名称是否存在
     * @param name
     */
    void checkGroupName(String name);

    /**
     * 查询班级用户
     * @param name
     * @param userId
     * @return
     */
    List<ClassUserInfoDto> searchClassUser(String name,String userId);

    /**
     * 获取班级成员
     * @param agencyClassId
     * @param type
     * @return
     */
    List<MemberDto> getClassMemberList(Long agencyClassId,int type);

    /**
     * 获取班级用户
     * @param groupId
     * @param userId
     * @return
     */
    List<ClassUserDto> getClassUserList(Long groupId,String userId);


    /**
     * 获取教师端通知
     * @param classId
     * @param userId
     * @param isMine
     * @param offset
     * @param size
     * @return
     */
    List<AgencyNoticeDto> getTeacherNoticeList(Long classId,String userId,int isMine,int offset,int size);

    /**
     * 创建通知
     * @param classNoticeDto
     */
    void createClassNotice(ClassNoticeDto classNoticeDto);


    /**
     * 获取回执用户信息
     * @param noticeId
     * @param status
     * @return
     */
    List<ReceiptUserDto> getReceiptStudentList(Long noticeId,int status);

    /**
     * 提醒推送
     * @param notice
     * @param userId
     */
    void noticePush(Long notice,String userId);

    /**
     * 获取class信息
     * @param classId
     * @return
     */
    AgencyClass getClassInfoByClassId(Long classId);


    /**
     * 获取最新通知
     * @param agencyClassId
     * @param userId
     * @param studentId
     * @param role
     * @return
     */
    AgencyNoticeDto getLastNotice(Long agencyClassId,String userId,Long studentId,int role);

    /**
     * 获取班级全部用户
     * @param classId
     * @return
     */
    List<UserDto> getClassUsers(Long classId);

    /**
     * 获取所在班级的学校的全部班级
     * @param classId
     * @return
     */
    List<Long> getAgencyClassIds(Long classId);

    /**
     * 获取回执详情
     * @param noticeId
     * @param userId
     * @param studentId
     * @return
     */
    AgencyNoticeReceiptDetailDto getNoticeReceiptDetail(Long noticeId,String userId,Long studentId);
}
