package com.stylefeng.guns.generator.demo;

import com.stylefeng.guns.generator.model.GenTemplateParam;

import java.io.File;

/**
 * 默认的代码生成的配置
 *
 * @author fengshuonan
 * @date 2017-10-28-下午8:27
 */
public class WebGeneratorConfig extends AbstractGeneratorConfig {

    public WebGeneratorConfig(GenTemplateParam genTemplateParam) {
        this.genTemplateParam = genTemplateParam;
    }

    /**
     * 全局配置
     */
    @Override
    protected void globalConfig() {
        globalConfig.setOutputDir(genTemplateParam.getProjectPath() + File.separator + "src" + File.separator + "main" + File.separator + "java");
        globalConfig.setFileOverride(true);
        globalConfig.setOpen(false);
        globalConfig.setEnableCache(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setAuthor(genTemplateParam.getAuthor());
    }

}
