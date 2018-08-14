package com.stylefeng.guns.generator.engine;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.stylefeng.guns.generator.config.*;
import lombok.Data;

/**
 * 模板生成父类
 *
 * @author fengshuonan
 * @date 2017-05-08 20:17
 */
@Data
public class AbstractTemplateEngine {

    //全局配置
    protected ContextConfig contextConfig;
    //sql配置
    protected MenuSqlConfig menuSqlConfig;

    //Dao配置
    protected DaoConfig daoConfig;
    //Service配置
    protected ServiceConfig serviceConfig;
    //控制器的配置
    protected ControllerConfig controllerConfig;
    //页面的控制器
    protected PageConfig pageConfig;

    //表的信息
    protected TableInfo tableInfo;

    /**
     * 初始化模板所需数据
     */
    public void initConfig() {
        if (this.contextConfig == null) {
            this.contextConfig = new ContextConfig();
        }
        if (this.daoConfig == null) {
            this.daoConfig = new DaoConfig();
        }
        if (this.serviceConfig == null) {
            this.serviceConfig = new ServiceConfig();
        }
        if (this.controllerConfig == null) {
            this.controllerConfig = new ControllerConfig();
        }
        if (this.pageConfig == null) {
            this.pageConfig = new PageConfig();
        }
        if (this.menuSqlConfig == null) {
            this.menuSqlConfig = new MenuSqlConfig();
        }
        this.contextConfig.init();

        this.daoConfig.setContextConfig(this.contextConfig);
        this.daoConfig.init();
        this.serviceConfig.setContextConfig(this.contextConfig);
        this.serviceConfig.init();
        this.controllerConfig.setContextConfig(this.contextConfig);
        this.controllerConfig.init();
        this.menuSqlConfig.setContextConfig(this.contextConfig);
        this.menuSqlConfig.init();

        this.pageConfig.setContextConfig(this.contextConfig);
        this.pageConfig.init();
    }

}

