package com.pq.agency.utils;

/**
 * 用户常量类
 *
 * @author liutao
 */
public class Constants {
    /**
     * 1-正常  2-锁定
     */
    public static final int USER_STATUS_NORMAL = 1;
    public static final int USER_STATUS_LOCKED = 2;
    
    /**
     *  用户登录后使用SESSION_USER_ID_KEY记录用户id
     **/
    public static final String SESSION_USER_ID_KEY = "user_id";

    /**
     * 日志类型：1、重置密码， 2-修改密码, 3-修改用户信息 4-设置密码
     **/
    public static final Integer USER_MODIFY_TYPE_REST_PASSWORD = 1;
    public static final Integer USER_MODIFY_TYPE_PASSWORD = 2;
    public static final Integer USER_MODIFY_TYPE_USERINFO = 3;
    public static final Integer USER_MODIFY_TYPE_SET_PASSWORD = 4;


}
