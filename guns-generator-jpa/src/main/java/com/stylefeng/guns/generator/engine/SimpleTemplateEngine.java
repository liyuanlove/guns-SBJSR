package com.stylefeng.guns.generator.engine;


import com.stylefeng.guns.core.util.ToolUtil;

/**
 * 通用的模板生成引擎
 *
 * @author fengshuonan
 * @date 2017-05-09 20:32
 */
public class SimpleTemplateEngine extends GunsTemplateEngine {

    @Override
    protected void generatePageIndexHtml() {
        String path = ToolUtil.format(this.getContextConfig().getProjectPath() + getPageConfig().getPageIndexPathTemplate(),
                this.getContextConfig().getBizEnName(), this.getContextConfig().getBizEnName());
        generateFile(this.getContextConfig().getTemplatePrefixPath() + "/page.html.btl", path);
        System.out.println("生成页面成功!");
    }

    @Override
    protected void generatePageAddHtml() {
        String path = ToolUtil.format(this.getContextConfig().getProjectPath() + getPageConfig().getPageAddPathTemplate(),
                this.getContextConfig().getBizEnName(), this.getContextConfig().getBizEnName());
        generateFile(this.getContextConfig().getTemplatePrefixPath() + "/page_add.html.btl", path);
        System.out.println("生成添加页面成功!");
    }

    @Override
    protected void generatePageEditHtml() {
        String path = ToolUtil.format(this.getContextConfig().getProjectPath() + getPageConfig().getPageEditPathTemplate(),
                this.getContextConfig().getBizEnName(), this.getContextConfig().getBizEnName());
        generateFile(this.getContextConfig().getTemplatePrefixPath() + "/page_edit.html.btl", path);
        System.out.println("生成编辑页面成功!");
    }

    @Override
    protected void generateInfoPageJs() {
        String path = ToolUtil.format(this.getContextConfig().getProjectPath() + getPageConfig().getPageInfoJsPathTemplate(),
                this.getContextConfig().getBizEnName(), this.getContextConfig().getBizEnName());
        generateFile(this.getContextConfig().getTemplatePrefixPath() + "/page_info.js.btl", path);
        System.out.println("生成页面详情js成功!");
    }

    @Override
    protected void generateIndexPageJs() {
        String path = ToolUtil.format(this.getContextConfig().getProjectPath() + getPageConfig().getPageIndexJsPathTemplate(),
                this.getContextConfig().getBizEnName(), this.getContextConfig().getBizEnName());
        generateFile(this.getContextConfig().getTemplatePrefixPath() + "/page.js.btl", path);
        System.out.println("生成页面js成功!");
    }

    @Override
    protected void generateController() {
        String controllerPath = ToolUtil.format(this.getContextConfig().getProjectPath() + super.getControllerConfig().getControllerPathTemplate(),
                ToolUtil.firstLetterToUpper(this.getContextConfig().getBizEnName()));
        generateFile(this.getContextConfig().getTemplatePrefixPath() + "/Controller.java.btl", controllerPath);
        System.out.println("生成控制器成功!");
    }

    @Override
    protected void generateService() {
        String servicePath = ToolUtil.format(this.getContextConfig().getProjectPath() + super.getServiceConfig().getServicePathTemplate(),
                ToolUtil.firstLetterToUpper(this.getContextConfig().getBizEnName()));
        generateFile(this.getContextConfig().getTemplatePrefixPath() + "/Service.java.btl", servicePath);
        System.out.println("生成service成功!");
        String implPath = ToolUtil.format(this.getContextConfig().getProjectPath() + super.getServiceConfig().getServiceImplPathTemplate(),
                ToolUtil.firstLetterToUpper(this.getContextConfig().getBizEnName()));
        generateFile(this.getContextConfig().getTemplatePrefixPath() + "/ServiceImpl.java.btl", implPath);
        System.out.println("生成impl成功!");
    }

    @Override
    protected void generateDao() {
        String path = ToolUtil.format(this.getContextConfig().getProjectPath() + super.getDaoConfig().getDaoPathTemplate(),
                ToolUtil.firstLetterToUpper(this.getContextConfig().getBizEnName()));
        generateFile(this.getContextConfig().getTemplatePrefixPath() + "/Dao.java.btl", path);
        System.out.println("生成dao成功!");
    }

    @Override
    protected void generateMenuSqls() {
        String path = ToolUtil.format(this.getContextConfig().getProjectPath() + super.menuSqlConfig.getSqlPathTemplate(),
                ToolUtil.firstLetterToUpper(this.getContextConfig().getBizEnName()));
        generateFile(this.getContextConfig().getTemplatePrefixPath() + "/menu_sql.sql.btl", path);
        System.out.println("生成sql成功!");
    }
}
