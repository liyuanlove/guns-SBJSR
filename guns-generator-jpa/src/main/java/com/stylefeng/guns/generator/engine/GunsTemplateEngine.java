package com.stylefeng.guns.generator.engine;

import com.stylefeng.guns.core.util.ToolUtil;
import com.sun.javafx.PlatformUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ADI项目模板生成 引擎
 *
 * @author fengshuonan
 * @date 2017-05-07 22:15
 */
public abstract class GunsTemplateEngine extends AbstractTemplateEngine {

    private GroupTemplate groupTemplate;

    public GunsTemplateEngine() {
        initBeetlEngine();
    }

    /**
     * 初始化Beetl引擎
     */
    protected void initBeetlEngine() {
        Properties properties = new Properties();
        properties.put("RESOURCE.root", "");
        properties.put("DELIMITER_STATEMENT_START", "<%");
        properties.put("DELIMITER_STATEMENT_END", "%>");
        properties.put("HTML_TAG_FLAG", "##");
        Configuration cfg = null;
        try {
            cfg = new Configuration(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        groupTemplate = new GroupTemplate(resourceLoader, cfg);
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
    }

    /**
     * 配置模板参数
     *
     * @param template
     */
    protected void configTemplate(Template template) {
        template.binding("controller", super.controllerConfig);
        template.binding("service", super.serviceConfig);
        template.binding("dao", super.daoConfig);
        template.binding("sqls", super.menuSqlConfig);

        template.binding("context", super.contextConfig);
        template.binding("mapper", super.daoConfig);
        template.binding("table", super.tableInfo);
    }

    /**
     * 生成文件
     *
     * @param templateFilePath 模板文件
     * @param genfilePath      生成位置
     */
    protected void generateFile(String templateFilePath, String genfilePath) {
        Template pageTemplate = groupTemplate.getTemplate(templateFilePath);
        configTemplate(pageTemplate);
        if (PlatformUtil.isWindows()) {
            genfilePath = genfilePath.replaceAll("/+|\\\\+", "\\\\");
        } else {
            genfilePath = genfilePath.replaceAll("/+|\\\\+", "/");
        }
        File file = new File(genfilePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            //将渲染页面写入文件
            pageTemplate.renderTo(fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 开始生成文件
     */
    public void start() {
        //配置之间的相互依赖
        super.initConfig();
        //生成模板
        if (super.contextConfig.getControllerSwitch()) {
            generateController();
        }
        if (super.contextConfig.getServiceSwitch()) {
            generateService();
        }
        if (super.contextConfig.getDaoSwitch()) {
            generateDao();
        }
        if (super.contextConfig.getMenuSqlSwitch()) {
            generateMenuSqls();
        }

        if (super.contextConfig.getIndexPageSwitch()) {
            generatePageIndexHtml();
        }
        if (super.contextConfig.getAddPageSwitch()) {
            generatePageAddHtml();
        }
        if (super.contextConfig.getEditPageSwitch()) {
            generatePageEditHtml();
        }
        if (super.contextConfig.getIndexJsSwitch()) {
            generateIndexPageJs();
        }
        if (super.contextConfig.getInfoJsSwitch()) {
            generateInfoPageJs();
        }

    }

    //页面部分
    protected abstract void generatePageIndexHtml();

    protected abstract void generatePageAddHtml();

    protected abstract void generatePageEditHtml();

    protected abstract void generateInfoPageJs();

    protected abstract void generateIndexPageJs();

    //后台部分
    protected abstract void generateController();

    protected abstract void generateService();

    protected abstract void generateDao();

    protected abstract void generateMenuSqls();
}
