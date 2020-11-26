package com.github.library.result;

/**
 * 响应状态码 和 描述
 * @author zwl
 * @date 2020/11/25 20:15
 */
public enum ResultEnum {

    /* 成功状态码 */
    SUCCESS(200, "success"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"), PARAM_IS_BLANK(10002, "参数为空"), PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"), REQUEST_BODY_NULL(10005, "缺少请求体"), TOKEN_IS_NULL(10006, "unauthorized，请重新登陆"),
    TOKEN_IS_TIMEOUT(10009, "登陆过期，请重新登陆"), TOKEN_CHECKED_SUCCESS(10007, "token 验证成功"),
    TOKEN_CHECKED_FAILURE(10008, "token 验证失败"), CREATE_TOKEN_ERROR(10010, "生成token错误"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"), USER_LOGIN_ERROR(20002, "账号或密码错误"), USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"), USER_HAS_EXISTED(20005, "用户已存在"), USERNAME_NOT_EMPTY(20006, "用户名不能为空"),
    PASSWORD_NOT_EMPTY(20007, "密码不能为空"), CODE_NOT_EMPTY(20008, "验证码不能为空"),
    SYSTEM_ERROR_LOGIN_NOT_UNIQUE(20008,"数据库存在相同账户"),

    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"), DATA_NOT_EXIST(30002, "未查询到记录！"),
    SAME_CATEGORY_EXIST(30003, "有同级同名的分类！"), ORDER_NOT_EXIST_ERROR(30004, "订单不存在！"),
    NULL_ADDRESS_ERROR(30005, "地址不能为空！"), ORDER_PRICE_ERROR(30006, "订单价格异常！"), ORDER_GENERATE_ERROR(30007, "生成订单异常！"),
    SHOPPING_ITEM_ERROR(30008, "购物车数据异常！"), SHOPPING_ITEM_COUNT_ERROR(30009, "库存不足！"),
    ORDER_STATUS_ERROR(30010, "订单状态异常！"),

    /*文件上传错误*/
    UPLOAD_FILE_ERROR(40000, "文件上传失败，请稍后重试"),
    /*文件下载错误*/
    DOWNLOAD_FILE_ERROR(40002, "文件下载失败，请稍后重试"),

    /* 系统错误：40003-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /* 数据错误：50001-599999 */
    RESULT_DATA_NONE(50001, "数据未找到"), DATA_IS_WRONG(50002, "数据有误"), DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"), INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"), INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"), INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),
    INTERFACE_WX_CODE2SESSION_ERROR(60007, "调用微信端授权认证接口错误"),

    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限"), RESOURCE_EXISTED(70002, "资源已存在"), RESOURCE_NOT_EXISTED(70003, "资源不存在"),

    /* 数据库错误：80001-89999 */
    NO_RESULT(80001, "查询无结果"),

    /* 服务器内部错误 */
    INTERNAL_SERVER_ERROR( 500, "Internal Server Error");

    private final int code;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private final String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
