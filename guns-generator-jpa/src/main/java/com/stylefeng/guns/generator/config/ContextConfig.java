package com.stylefeng.guns.generator.config;


import com.stylefeng.guns.core.util.ToolUtil;
import lombok.Data;

/**
 * 全局配置
 *
 * @author fengshuonan
 * @date 2017-05-08 20:21
 */
@Data
public class ContextConfig {

    //资源路径前缀
    private String templatePrefixPath = "gunsTemplate";
    //模板输出的项目目录
    private String projectPath = "D:\\code";
    //业务名称（注释和页面使用）
    private String bizChName;
    //业务英文名称（文件等命名使用）
    private String bizEnName;
    //业务英文名称(大写)
    private String bizEnBigName;
    //所属模块名称(英文)
    private String moduleEnName = "system";

    //项目包
    private String proPackage = "com.stylefeng.guns";
    //导包使用
    private String coreBasePackage = "com.stylefeng.guns.core";
    //po包名
    private String modelPackageName = "com.stylefeng.guns.po";
    //po的dao
    private String modelMapperPackageName = "com.stylefeng.guns.mapper";
    //实体的名称
    private String poClassName;

    private Boolean indexPageSwitch = true;     //主页
    private Boolean addPageSwitch = true;       //添加页面
    private Boolean editPageSwitch = true;      //编辑页面
    private Boolean indexJsSwitch = true;            //js
    private Boolean infoJsSwitch = true;        //详情页面js

    private Boolean controllerSwitch = true;    //是否生成控制器代码开关
    private Boolean daoSwitch = true;           //mapper
    private Boolean serviceSwitch = true;       //service
    private Boolean entitySwitch = true;        //生成实体的开关
    private Boolean menuSqlSwitch = true;           //生成sql的开关

    public void init() {
        if (poClassName == null) {
            poClassName = bizEnBigName;
        }
        modelPackageName = proPackage + ".po";
        modelMapperPackageName = proPackage + ".mapper";
    }

    public void setBizEnName(String bizEnName) {
        this.bizEnName = bizEnName;
        this.bizEnBigName = ToolUtil.firstLetterToUpper(this.bizEnName);
    }

}
