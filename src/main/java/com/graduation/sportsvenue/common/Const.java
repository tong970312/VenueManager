package com.graduation.sportsvenue.common;

/**
 * 定义常量
 */
public class Const {
    //成功时的状态码
    public static final Integer SUCCESS_STATUS = 0;
    //失败时的状态码
    public static final Integer ERROR_STATUS = 1;

    //用户跟管理员的权限
    public static final Integer USERROLE = 0;
    public static final Integer ADMINROLE = 1;

    //保存session使用的KEY 当前用户或者管理员
    public static final String CURRENTUSER = "CURRENTUSER";
    public static final String CURRENTADMIN = "CURRENTADMIN";
    //校验用户名
    public static final Integer ACCOUNT_EXIT = 1;
    public static final Integer ACCOUNT_NOT_EXIT = 0;
    //修改用户字段
    public static final Integer ACCOUNT_DELETE = 1;
    //图片路径
    public static final String IMAGEHOST = "http://192.168.163.129/img";
    public static final String IMAGEURL = "D:\\webstormspace\\demo\\src\\picture\\";
}
