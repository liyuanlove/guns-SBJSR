package com.stylefeng.guns.generator.demo;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.stylefeng.guns.core.support.StrKit;
import com.stylefeng.guns.core.util.FileUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.generator.config.ContextConfig;
import com.stylefeng.guns.generator.config.MenuSqlConfig;
import com.stylefeng.guns.generator.engine.GunsTemplateEngine;
import com.stylefeng.guns.generator.engine.SimpleTemplateEngine;
import com.stylefeng.guns.generator.model.GenTemplateParam;

import java.io.File;
import java.util.List;

/**
 * 代码生成的抽象配置
 */
public abstract class AbstractGeneratorConfig {

    protected GenTemplateParam genTemplateParam;

    public AbstractGeneratorConfig() {
    }

    /**
     * mybatis-plus代码生成器配置
     */
    GlobalConfig globalConfig = new GlobalConfig();
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    StrategyConfig strategyConfig = new StrategyConfig();
    PackageConfig packageConfig = new PackageConfig();
    TableInfo tableInfo = null;

    /**
     * Guns代码生成器配置
     */
    ContextConfig contextConfig = new ContextConfig();
    MenuSqlConfig menuSqlConfig = new MenuSqlConfig();

    /**
     * 初始化配置
     */
    public void init() {
        globalConfig();
        dataSourceConfig();
        strategyConfig();
        contextConfig();

        packageConfig.setParent("\\src\\main\\java\\" + genTemplateParam.getProjectPackage().replaceAll("\\.", "\\\\"));
        packageConfig.setEntity("po");
        packageConfig.setMapper("TTT");
        packageConfig.setService("TTT");
        packageConfig.setServiceImpl("TTT");
        packageConfig.setController("TTT");
        packageConfig.setXml("TTT");
    }

    protected abstract void globalConfig();

    /**
     * 配置数据源
     */
    protected void dataSourceConfig() {
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUrl(genTemplateParam.getUrl());
        dataSourceConfig.setUsername(genTemplateParam.getUserName());
        dataSourceConfig.setPassword(genTemplateParam.getPassword());
    }

    /**
     * 生成策略配置
     */
    protected void strategyConfig() {
        // 此处可以修改为您的表前缀
        if (genTemplateParam.getIgnoreTabelPrefix() != null) {
            strategyConfig.setTablePrefix(new String[]{genTemplateParam.getIgnoreTabelPrefix()});
        }
        //需要处理的表
        strategyConfig.setInclude(new String[]{genTemplateParam.getTableName()});
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
    }

    protected void contextConfig() {
        contextConfig.setProPackage(genTemplateParam.getProjectPackage());
        contextConfig.setCoreBasePackage(genTemplateParam.getCorePackage());

        /**
         * 业务代码配置
         */
        contextConfig.setBizChName(genTemplateParam.getBizCnName());
        contextConfig.setModuleEnName(genTemplateParam.getModuleEnName());
        contextConfig.setProjectPath(genTemplateParam.getProjectPath());//写自己项目的绝对路径
        if (ToolUtil.isEmpty(genTemplateParam.getIgnoreTabelPrefix())) {
            String entityName = StrKit.toCamelCase(genTemplateParam.getTableName());
            contextConfig.setPoClassName(StrKit.firstCharToUpperCase(entityName));
            contextConfig.setBizEnName(StrKit.firstCharToLowerCase(entityName));
        } else {
            String entiyName = StrKit.toCamelCase(StrKit.removePrefix(genTemplateParam.getTableName(), genTemplateParam.getIgnoreTabelPrefix()));
            contextConfig.setPoClassName(StrKit.firstCharToUpperCase(entiyName));
            contextConfig.setBizEnName(StrKit.firstCharToLowerCase(entiyName));
        }
        //这里写已有菜单的名称,当做父节点
        menuSqlConfig.setParentMenuName(genTemplateParam.getParentMenuName());

        /**
         * mybatis-plus 生成器开关
         */
        contextConfig.setEntitySwitch(genTemplateParam.getEntitySwitch());
        contextConfig.setDaoSwitch(genTemplateParam.getDaoSwitch());
        contextConfig.setServiceSwitch(genTemplateParam.getServiceSwitch());

        /**
         * guns 生成器开关
         */
        contextConfig.setControllerSwitch(genTemplateParam.getControllerSwitch());
        contextConfig.setIndexPageSwitch(genTemplateParam.getIndexPageSwitch());
        contextConfig.setAddPageSwitch(genTemplateParam.getAddPageSwitch());
        contextConfig.setEditPageSwitch(genTemplateParam.getEditPageSwitch());
        contextConfig.setIndexJsSwitch(genTemplateParam.getIndexJsSwitch());
        contextConfig.setInfoJsSwitch(genTemplateParam.getInfoJsSwitch());
    }

    /**
     * 删除MP生成的代码
     */
    public void destory() {
        String outputDir = globalConfig.getOutputDir() + "\\src\\main\\java\\" + genTemplateParam.getProjectPackage().replaceAll("\\.", "\\\\") + "/TTT";
        FileUtil.deleteDir(new File(outputDir));
    }

    /**
     * 调用MybatisPlus生成功能
     */
    public void genTableInfo() {
        init();
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.execute();
        destory();

        //获取table信息,用于guns代码生成
        List<TableInfo> tableInfoList = autoGenerator.getConfig().getTableInfoList();
        if (!ToolUtil.isEmpty(tableInfoList)) {
            this.tableInfo = tableInfoList.get(0);
        }
    }

    /**
     * 生成页面
     */
    public void genGunsTemplate() {
        GunsTemplateEngine templateEngine = new SimpleTemplateEngine();
        templateEngine.setContextConfig(contextConfig);
        menuSqlConfig.setConnection(dataSourceConfig.getConn());
        templateEngine.setMenuSqlConfig(menuSqlConfig);
        templateEngine.setTableInfo(tableInfo);
        templateEngine.start();
    }
}
