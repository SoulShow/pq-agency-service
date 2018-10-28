package com.pq.agency.exception;


import com.pq.common.exception.ErrorCode;

public class AgencyErrorCode extends ErrorCode {

    private final static String PRE = "2";

    public AgencyErrorCode(String errorCode, String errorMsg) {
        super(PRE+errorCode, errorMsg);
    }
}

