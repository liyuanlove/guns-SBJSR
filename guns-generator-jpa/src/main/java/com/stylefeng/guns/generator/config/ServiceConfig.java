package com.stylefeng.guns.generator.config;

import lombok.Data;

/**
 * Service模板生成的配置
 *
 * @author fengshuonan
 * @date 2017-05-07 22:12
 */
@Data
public class ServiceConfig {

    private ContextConfig contextConfig;

    private String servicePathTemplate;
    private String serviceImplPathTemplate;

    private String packageName;

    public void init() {
        this.servicePathTemplate = "\\src\\main\\java\\" + contextConfig.getProPackage().replaceAll("\\.", "\\\\") + "\\service\\I{}Service.java";
        this.serviceImplPathTemplate = "\\src\\main\\java\\" + contextConfig.getProPackage().replaceAll("\\.", "\\\\") + "\\service\\impl\\{}ServiceImpl.java";
        this.packageName = contextConfig.getProPackage() + ".service";
    }

}
