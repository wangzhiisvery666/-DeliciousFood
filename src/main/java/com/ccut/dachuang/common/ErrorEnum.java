package com.ccut.dachuang.common;


public enum ErrorEnum {

    //TODO : 用户名和密码参数分开
    PARAMETER_EXCEPTION_NULL("100","错误：参数为空",""),
    USER_REGISTRATION_ERROR("101","用户名长度小于4",""),
    USERNAME_EXIST("102","用户名已存在",""),
    PHONE_NUMBER_ALREADY_BOUND("103","该手机已经绑定用户",""),
    SHORT_PASSWORD_LENGTH("104","密码长度小于6",""),
    USERNAME_CONTAINS_SENSITIVE_WORDS("105","用户名包含敏感词",""),
    SQL_ERROR("106","数据库插入失败",""),
    VERIFICATION_CODE_ERROR("107","验证码错误",""),
    USERNAME_IS_EMPTY("108","用户名为空",""),
    PASSWORD_IS_EMPTY("109","密码为空",""),
    WRONG_USER_NAME_OR_PASSWORD("110","用户名或密码错误",""),
    WRONG_FILE_TYPE("111","文件类型错误",""),
    AVATAR_OVER_5MB("112","头像超过5mb",""),
    AVATAR_UPLOAD_FAILED("113","头像上传失败",""),
    SESSION_EXPIRED("114","会话过期请重新登陆",""),
    NOT_LOGGED_IN("115","未登录",""),
    THE_USER_IS_LOGGED_IN("116","该用户已登陆",""),
    PLEASE_ENTER_A_NEW_NICKNAME("117","请输入新昵称",""),
    TOKEN_ERROR("118","token出错",""),
    ADDRESS_ADD_FAILED("119","地址添加失败",""),
    ADDRESS_DELETE_FAILED("120","地址删除失败",""),
    FAIL_TO_EDIT("121","地址修改失败",""),


    USERNAME_VERIFICATION_FAILED("A0110","用户名校验失败",""),
    PASSWORD_VERIFICATION_FAILED("A0120","密码校验失败",""),
    INTERNAL_ERROR("501","内部异常",""),
    ;
    private String statusCode;
    private String describe;
    private String message;

    ErrorEnum(String statusCode,  String message,String describe) {
        this.statusCode = statusCode;
        this.describe = describe;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
