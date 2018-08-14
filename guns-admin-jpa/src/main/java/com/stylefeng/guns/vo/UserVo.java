package com.stylefeng.guns.vo;

import lombok.Data;

import java.util.Date;

/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
@Data
public class UserVo {
    private int id;
    private String avatar;
    private String account;
    private String salt;
    private String name;
    private Date birthday;
    private Integer sex;
    private String email;
    private String phone;
    private String roleid;
    private Integer deptid;
    private Integer status;
    private Date createtime;
    private Integer version;


    private String sexName;
    private String roleName;
    private String deptName;
    private String statusName;

}
