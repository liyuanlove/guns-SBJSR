package org.tc.jpa.exception;

import lombok.Data;
import org.tc.jpa.enums.IExceptionEnum;

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

    public GunsException(IExceptionEnum serviceExceptionEnum) {
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
