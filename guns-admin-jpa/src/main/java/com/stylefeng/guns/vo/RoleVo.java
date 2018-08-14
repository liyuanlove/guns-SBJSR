package com.stylefeng.guns.vo;

import lombok.Data;

/**
 * 角色列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日10:59:02
 */
@Data
public class RoleVo {

    private int id;
    private Integer num;
    private Integer pid;
    private String name;
    private Integer deptid;
    private String tips;
    private Integer version;

    private String pName;
    private String deptName;
}
