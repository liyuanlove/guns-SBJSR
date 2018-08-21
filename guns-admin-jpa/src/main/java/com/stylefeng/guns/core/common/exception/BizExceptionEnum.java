package com.stylefeng.guns.core.common.exception;

import org.tc.jpa.enums.IExceptionEnum;

/**
 * @author fengshuonan
 * @Description 所有业务异常的枚举
 * @date 2016年11月12日 下午5:04:51
 */
public enum BizExceptionEnum implements IExceptionEnum {

    /**
     * 字典
     */
    DICT_EXISTED(400, "字典已经存在"),
    ERROR_CREATE_DICT(500, "创建字典失败"),
    ERROR_WRAPPER_FIELD(500, "包装字典属性失败"),
    ERROR_CODE_EMPTY(500, "字典类型不能为空"),

    /**
     * 文件上传
     */
    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),
    FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"),
    UPLOAD_ERROR(500, "上传图片出错"),

    /**
     * 权限和数据问题
     */
    DB_RESOURCE_NULL(400, "数据库中没有该资源"),
    NO_PERMITION(405, "权限异常"),
    REQUEST_INVALIDATE(400, "请求数据格式不正确"),
    INVALID_KAPTCHA(400, "验证码不正确"),
    CANT_DELETE_ADMIN(600, "不能删除超级管理员"),
    CANT_FREEZE_ADMIN(600, "不能冻结超级管理员"),
    CANT_CHANGE_ADMIN(600, "不能操作超级管理员"),

    /**
     * 账户问题
     */
    USER_ALREADY_REG(401, "该用户已经注册"),
    NO_THIS_USER(400, "没有此用户"),
    USER_NOT_EXISTED(400, "没有此用户"),
    ACCOUNT_FREEZED(401, "账号被冻结"),
    OLD_PWD_NOT_RIGHT(402, "原密码不正确"),
    TWO_PWD_NOT_MATCH(405, "两次输入密码不一致"),

    /**
     * 菜单问题
     */
    NO_THIS_MENU(400, "没有此菜单"),

    /**
     * 卖家商家
     */
    SELLER_ALREADY_EXIST(401, "该卖家已创建"),
    NO_SELLER_DATASCOPE(401, "还未配置管理卖家范围"),

    /**
     * 优惠券
     */
    COUPON_NUM_ERROR(400, "优惠券数量错误"),
    COUPON_RANGE_ERROR(400, ""),
    COUPON_RECEPTED(400, "重复领取"),
    OTHER_RECEPTED(400, "已被他人领取"),
    COUPON_NOT_ACTIVED(400, "还未被领取"),
    COUPON_CODE_ERROR(400, "验证码错误"),
    COUPON_EXPIRED(400, "该优惠券已过期"),
    COUPON_QRCODE_ERROR(400, "生成二维码出错"),
    COUPON_CANT_DELETE(600, "不能删除被领取的数据(存档数据)"),

    /**
     * 错误的请求
     */
    MENU_PCODE_COINCIDENCE(400, "菜单编号和父编号不能一致"),
    EXISTED_THE_MENU(400, "菜单编号重复，不能添加"),
    DICT_MUST_BE_NUMBER(400, "字典的值必须为数字"),
    REQUEST_NULL(400, "请求有错误：缺少必要的参数信息"),
    NO_THIS_RECORD(400, "没有此记录"),
    SESSION_TIMEOUT(400, "会话超时"),
    SERVER_ERROR(500, "服务器异常"),

    UPDATE_ERROR(400, "更新失败");

    BizExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    //错误编码
    private Integer code;
    //错误信息
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
