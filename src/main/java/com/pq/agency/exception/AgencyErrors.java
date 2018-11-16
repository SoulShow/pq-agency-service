package com.pq.agency.exception;


import com.pq.common.exception.CommonErrors;
import com.pq.common.exception.ErrorCode;

/**
 * @author liutao
 */
public class AgencyErrors extends CommonErrors {

    public final static ErrorCode INVITATION_CODE_ERROR = new AgencyErrorCode("0001", "邀请码错误");

    public final static ErrorCode AGENCY_USER_STUDENT_NOT_MATCH_ERROR = new AgencyErrorCode("0002", "用户和学生信息不匹配");


}
