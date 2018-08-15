package com.stylefeng.guns.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class CouponVo {
    private Integer id;
    private Integer sellerid;
    @JSONField(format = "yyyy-MM-dd")
    private Date beginDay;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDay;
    private Date receptDay;
    private String userTel;
    private String useCode;
    private Integer used;
    private Date createtime;

    private String status;
    private int bg;
    private String sellername;
}
