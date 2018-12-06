package com.pq.agency.exception;


import com.pq.common.exception.CommonErrors;
import com.pq.common.exception.ErrorCode;

/**
 * @author liutao
 */
public class AgencyErrors extends CommonErrors {

    public final static ErrorCode INVITATION_CODE_ERROR = new AgencyErrorCode("0001", "请输入正确的班级识别码");

    public final static ErrorCode AGENCY_STUDENT_NOT_EXIST_ERROR = new AgencyErrorCode("0002", "请输入正确的孩子姓名");

    public final static ErrorCode AGENCY_CLASS_NOT_EXIST_ERROR = new AgencyErrorCode("0003", "班级信息不存在");

    public final static ErrorCode AGENCY_CLASS_USER_NOT_EXIST_ERROR = new AgencyErrorCode("0004", "班级用户信息不存在");

    public final static ErrorCode AGENCY_USER_STUDENT_NOT_EXIST_ERROR = new AgencyErrorCode("0005", "学生信息不存在");

    public final static ErrorCode AGENCY_NOT_EXIST_ERROR = new AgencyErrorCode("0006", "机构信息不存在");

    public final static ErrorCode AGENCY_STUDENT_RELATION_IS_EXIST_ERROR = new AgencyErrorCode("0007", "该关系已存在");

    public final static ErrorCode AGENCY_CLASS_NOTICE_IS_RECEIPT_ERROR = new AgencyErrorCode("0008", "该通知已回复");

    public final static ErrorCode AGENCY_CLASS_NOTICE_NOT_EXIST_ERROR = new AgencyErrorCode("0009", "通知不存在");

    public final static ErrorCode AGENCY_COLLECTION_NOT_EXIST_ERROR = new AgencyErrorCode("0010", "该收藏不存在");

    public final static ErrorCode AGENCY_STUDENT_NOT_NULL_ERROR = new AgencyErrorCode("0011", "请输入孩子姓名");

    public final static ErrorCode AGENCY_STUDENT_NAME_MORE_THREE_ERROR = new AgencyErrorCode("0012", "学生姓名输入错误次数大于三次");

    public final static ErrorCode AGENCY_CLASS_VOTE_EXIST_ERROR = new AgencyErrorCode("0013", "请不要重复投票");

}
