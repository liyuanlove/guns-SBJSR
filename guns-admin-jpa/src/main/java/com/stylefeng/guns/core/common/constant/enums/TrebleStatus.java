package com.stylefeng.guns.core.common.constant.enums;

/**
 * 管理员的三状态
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午9:54:13
 */
public enum TrebleStatus {

    ACTIVED(1, "启用"),
    FREEZED(2, "冻结"),
    DELETED(3, "被删除");

    int code;
    String message;

    TrebleStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer value) {
        if (value == null) {
            return "";
        } else {
            for (TrebleStatus ms : TrebleStatus.values()) {
                if (ms.getCode() == value) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}
