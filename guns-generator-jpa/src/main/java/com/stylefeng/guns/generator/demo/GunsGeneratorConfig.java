package com.stylefeng.guns.generator.demo;

import com.stylefeng.guns.generator.model.GenTemplateParam;

/**
 * 默认的代码生成的配置
 *
 * @author fengshuonan
 * @date 2017-10-28-下午8:27
 */
public class GunsGeneratorConfig extends AbstractGeneratorConfig {

    public GunsGeneratorConfig(GenTemplateParam genTemplateParam) {
        this.genTemplateParam = genTemplateParam;
    }

    /**
     * 全局配置
     */
    @Override
    protected void globalConfig() {
        //写自己项目的绝对路径,注意具体到java目录
        globalConfig.setOutputDir("D:\\code");
        globalConfig.setFileOverride(true);
        globalConfig.setOpen(false);
        globalConfig.setEnableCache(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setAuthor(genTemplateParam.getAuthor());
    }

}
