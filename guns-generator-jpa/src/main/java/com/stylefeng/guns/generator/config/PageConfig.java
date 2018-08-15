package com.stylefeng.guns.generator.config;

import lombok.Data;

/**
 * 页面 模板生成的配置
 *
 * @author fengshuonan
 * @date 2017-05-07 22:12
 */
@Data
public class PageConfig {

    private ContextConfig contextConfig;

    //页面文件路径模板
    private String pageIndexPathTemplate;
    private String pageAddPathTemplate;
    private String pageEditPathTemplate;
    private String pageInfoJsPathTemplate;
    private String pageIndexJsPathTemplate;

    //初始化数据
    public void init() {
        pageIndexPathTemplate = "\\src\\main\\resources\\templates\\" + contextConfig.getModuleEnName() + "\\{}\\{}.html";
        pageAddPathTemplate = "\\src\\main\\resources\\templates\\" + contextConfig.getModuleEnName() + "\\{}\\{}_add.html";
        pageEditPathTemplate = "\\src\\main\\resources\\templates\\" + contextConfig.getModuleEnName() + "\\{}\\{}_edit.html";
        pageIndexJsPathTemplate = "\\src\\main\\resources\\static\\modular\\" + contextConfig.getModuleEnName() + "\\{}\\{}.js";
        pageInfoJsPathTemplate = "\\src\\main\\resources\\static\\modular\\" + contextConfig.getModuleEnName() + "\\{}\\{}_info.js";
    }
}
