package com.stylefeng.guns.generator.config;

import lombok.Data;

/**
 * Dao模板生成的配置
 *
 * @author fengshuonan
 * @date 2017-05-07 22:12
 */
@Data
public class DaoConfig {

    private ContextConfig contextConfig;

    private String daoPathTemplate;
    private String packageName;

    public void init() {
        this.daoPathTemplate = "\\src\\main\\java\\" + contextConfig.getProPackage().replaceAll("\\.", "\\\\") + "\\dao\\{}Dao.java";
        this.packageName = contextConfig.getProPackage() + ".dao";
    }

}
