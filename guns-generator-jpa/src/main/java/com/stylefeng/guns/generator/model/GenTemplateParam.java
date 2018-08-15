package com.stylefeng.guns.generator.model;


import lombok.Data;

/**
 * 代码生成的查询参数
 *
 * @author fengshuonan
 * @date 2017-11-30-下午2:05
 */
@Data
public class GenTemplateParam {

    /**
     * 数据库信息
     */
    private String userName = "root";
    private String password = "123456";
    private String url = "jdbc:mysql://127.0.0.1:3306/guns?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=UTC";

    //项目地址
    private String projectPath;
    //项目的包
    private String projectPackage = "com.stylefeng.guns";
    //CORE模块的包
    private String corePackage = "com.stylefeng.guns.core";

    private String author = "TC";

    private String tableName;
    //剔除表的前缀
    private String ignoreTabelPrefix;

    //业务中文名称
    private String bizCnName;
    //所属模块英文名
    private String moduleEnName = "system";
    //父级菜单名称
    private String parentMenuName;

    private Boolean controllerSwitch = true;
    private Boolean daoSwitch = true;
    private Boolean serviceSwitch = true;
    private Boolean entitySwitch = true;

    private Boolean menuSqlSwitch = true;

    private Boolean indexPageSwitch = true;
    private Boolean addPageSwitch = true;
    private Boolean editPageSwitch = true;
    private Boolean indexJsSwitch = true;
    private Boolean infoJsSwitch = true;
}
