package com.enercomn.util.constant;

public enum ResultStatusCode {

    OK(200, "操作成功"),

    REQUEST_FAIL(5002, "调用接口失败"),

   REQUEST_ERROR(5001, "调用接口错误"),

    NEED_INFO(201, "需要完善信息"),

    OPERATION_FAIL(-1, "Operation Failed"),

    INVALID_TOKEN(401, "Invalid token"),

    NAME_DOUBLE(20010,"该名称已存在"),

    PASSWORD_SAME(20010,"新密码和原密码相同"),

    NAME_SUB_DOUBLE(20013,"SUB名称已存在"),

    OPENID_FAIL(20011,"OPENID获取失败"),

    ADD_DATA_FAIL(5001,"新增数据失败"),

    UPDATE_DATA_FAIL(5002,"更新数据失败"),

    DELETE_DATA_FAIL(5004,"删除数据失败"),

    DATA_FIND_NULL(5003,"未找到数据"),

    ROLE_FIND_NULL(5003,"未找到角色"),

    PROJECT_CLOSED(5003,"项目已被禁用"),

    MENU_FIND_NULL(5003,"未分配菜单"),

    OCR_ERROR(30001,"OCR识别失败"),

    USER_ERROR(30002,"用户权限认证失败"),
    USER_TOKEN_ERROR(30002,"请完善Token信息后进行请求[请求被拦截]"),

    LOW_USERLEVER(30002,"用户等级不足,无法操作"),

    PHONE_ERROR(10011,"手机号错误"),

    CODE_EXPIRE(10012,"验证码过期"),

    SEND_ERROR(10013,"发送失败"),

    READ_NULL(10013,"读取数据失败"),

    CODE_ERROR(10010,"验证码错误");





    private int resultCode;
    private String resultMessage;

    ResultStatusCode(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
