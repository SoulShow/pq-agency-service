package com.pq.agency.exception;


import com.pq.common.exception.BaseException;
import com.pq.common.exception.ErrorCode;

/**
 * @author liutao
 */
public class AgencyException extends BaseException {

    public AgencyException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public AgencyException(ErrorCode errorCode) {
        super(errorCode);
    }


    /**
     * 抛UserException异常
     * @param errorCode
     */
    public static void raise(ErrorCode errorCode){
        throw new AgencyException(errorCode);
    }


    /**
     * 抛UserException异常
     * @param errorCode
     * @param cause 异常
     */
    public static void raise(ErrorCode errorCode, Throwable cause){
        throw new AgencyException(errorCode, cause);
    }
}
