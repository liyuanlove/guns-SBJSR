package com.stylefeng.guns.vo;

import lombok.Data;

@Data
public class MenuVo {
    private long id;
    private String code;
    private String name;
    private String pcode;
    private String pcodes;
    private String icon;
    private String url;
    private Integer levels;
    private Integer num;
    private Integer ismenu;
    private Integer isopen;
    private Integer status;
    private String tips;


    private String statusName;
    private String isMenuName;

}
