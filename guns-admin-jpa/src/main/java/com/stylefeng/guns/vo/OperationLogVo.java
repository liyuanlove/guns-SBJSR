package com.stylefeng.guns.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OperationLogVo {
    private int id;
    private String logtype;
    private String logname;
    private Integer userid;
    private String classname;
    private String method;
    private Date createtime;
    private String succeed;
    private String message;

    private String userName;
    private Object regularMessage;
}
