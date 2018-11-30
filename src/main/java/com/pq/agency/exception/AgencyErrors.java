package com.pq.agency.exception;


import com.pq.common.exception.CommonErrors;
import com.pq.common.exception.ErrorCode;

/**
 * @author liutao
 */
public class AgencyErrors extends CommonErrors {

    public final static ErrorCode INVITATION_CODE_ERROR = new AgencyErrorCode("0001", "您输入的验证码有误");

    public final static ErrorCode AGENCY_STUDENT_NOT_EXIST_ERROR = new AgencyErrorCode("0002", "未找到孩子");

    public final static ErrorCode AGENCY_CLASS_NOT_EXIST_ERROR = new AgencyErrorCode("0003", "班级信息不存在");

    public final static ErrorCode AGENCY_CLASS_USER_NOT_EXIST_ERROR = new AgencyErrorCode("0004", "班级用户信息不存在");

    public final static ErrorCode AGENCY_USER_STUDENT_NOT_EXIST_ERROR = new AgencyErrorCode("0005", "学生信息不存在");

    public final static ErrorCode AGENCY_NOT_EXIST_ERROR = new AgencyErrorCode("0006", "机构信息不存在");

    public final static ErrorCode AGENCY_STUDENT_RELATION_IS_EXIST_ERROR = new AgencyErrorCode("0007", "该关系已存在");

    public final static ErrorCode AGENCY_CLASS_NOTICE_IS_RECEIPT_ERROR = new AgencyErrorCode("0008", "改通知已回复");

    public final static ErrorCode AGENCY_CLASS_NOTICE_NOT_EXIST_ERROR = new AgencyErrorCode("0009", "通知不存在");

}
