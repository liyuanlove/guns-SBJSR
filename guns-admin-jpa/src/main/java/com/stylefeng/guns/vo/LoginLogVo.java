package com.stylefeng.guns.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LoginLogVo {
    private Integer id;
    private String logname;
    private Integer userid;
    private Date createtime;
    private String succeed;
    private String message;
    private String ip;

    private String userName;
    private Object regularMessage;

}
