package com.stylefeng.guns.core.common.constant.enums;

/**
 * 业务是否成功的日志记录
 *
 * @author fengshuonan
 * @Date 2017年1月22日 下午12:14:59
 */
public enum LogResult {

    SUCCESS("成功"),
    FAIL("失败");

    String message;

    LogResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
