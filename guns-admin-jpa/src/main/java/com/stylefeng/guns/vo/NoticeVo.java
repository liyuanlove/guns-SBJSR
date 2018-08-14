package com.stylefeng.guns.vo;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeVo {
    private int id;
    private String title;
    private Integer type;
    private String content;
    private Date createtime;
    private Integer creater;

    private String createrName;
}
