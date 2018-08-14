package com.stylefeng.guns.generator.config;

import lombok.Data;

/**
 * 控制器模板生成的配置
 *
 * @author fengshuonan
 * @date 2017-05-07 22:12
 */
@Data
public class ControllerConfig {

    private ContextConfig contextConfig;

    //文件路径模板
    private String controllerPathTemplate;
    //包名称
    private String packageName;

    public void init() {
        this.packageName = contextConfig.getProPackage() + ".controller";
        this.controllerPathTemplate = "\\src\\main\\java\\" + contextConfig.getProPackage().replaceAll("\\.", "\\\\") + "\\controller\\{}Controller.java";
    }

}
