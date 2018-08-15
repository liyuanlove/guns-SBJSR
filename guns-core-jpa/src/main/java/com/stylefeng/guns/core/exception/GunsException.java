package com.stylefeng.guns.core.exception;

import com.stylefeng.guns.core.enums.ServiceExceptionEnum;
import lombok.Data;

/**
 * 封装guns的异常
 *
 * @author fengshuonan
 * @Date 2017/12/28 下午10:32
 */
@Data
public class GunsException extends RuntimeException {

    private Integer code;
    private String message;

    public GunsException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

    public GunsException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
