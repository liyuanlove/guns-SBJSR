package com.stylefeng.guns.main;


import com.stylefeng.guns.generator.demo.GunsGeneratorConfig;
import com.stylefeng.guns.generator.model.GenTemplateParam;
import com.stylefeng.guns.generator.service.ITableService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器,可以生成po,mapper,xml,service,controller,html,js
 *
 * @author stylefeng
 * @Date 2017/5/21 12:38
 */
public class GunsCodeGenerator {

    public static void main(String[] args) {

        GenTemplateParam genTemplateParam = new GenTemplateParam();
        //配置输出路径
        genTemplateParam.setProjectPath("D:\\code");
        genTemplateParam.setModuleEnName("system");

        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-jpa.xml");
        ITableService tableService = ctx.getBean(ITableService.class);
        List<Map<String, Object>> allTables = tableService.getTablesOfDb("guns");
        for (Map<String, Object> map : allTables) {
            String tableName = (String) map.get("tableName");
            genTemplateParam.setTableName(tableName);
            //reset
            genTemplateParam.setIgnoreTabelPrefix("");
            if (tableName.contains("sys_")) {
                genTemplateParam.setIgnoreTabelPrefix("sys_");
            }
            String tableComment = (String) map.get("tableComment");
            tableComment = tableComment.replaceAll("表", "");
            genTemplateParam.setBizCnName(tableComment);

            GunsGeneratorConfig gunsGeneratorConfig = new GunsGeneratorConfig(genTemplateParam);
            gunsGeneratorConfig.genTableInfo();
            gunsGeneratorConfig.genGunsTemplate();
        }
//        单独生成示例
//        genTemplateParam.setTableName("sys_user");
//        genTemplateParam.setIgnoreTabelPrefix("sys_");
//        genTemplateParam.setBizCnName("用户");
//        genTemplateParam.setTableName("sys_role");
//        genTemplateParam.setIgnoreTabelPrefix("sys_");
//        genTemplateParam.setBizCnName("角色");
//        genTemplateParam.setTableName("sys_relation");
//        genTemplateParam.setIgnoreTabelPrefix("sys_");
//        genTemplateParam.setBizCnName("角色权限配置");
//        genTemplateParam.setTableName("sys_menu");
//        genTemplateParam.setIgnoreTabelPrefix("sys_");
//        genTemplateParam.setBizCnName("菜单");
//        genTemplateParam.setTableName("sys_dict");
//        genTemplateParam.setIgnoreTabelPrefix("sys_");
//        genTemplateParam.setBizCnName("字典");
//        genTemplateParam.setTableName("sys_dept");
//        genTemplateParam.setIgnoreTabelPrefix("sys_");
//        genTemplateParam.setBizCnName("部门");
//        genTemplateParam.setTableName("sys_notice");
//        genTemplateParam.setIgnoreTabelPrefix("sys_");
//        genTemplateParam.setBizCnName("通知");
//        genTemplateParam.setTableName("sys_login_log");
//        genTemplateParam.setIgnoreTabelPrefix("sys_");
//        genTemplateParam.setBizCnName("登录日志");
//        genTemplateParam.setTableName("sys_operation_log");
//        genTemplateParam.setIgnoreTabelPrefix("sys_");
//        genTemplateParam.setBizCnName("操作日志");
//        genTemplateParam.setTableName("sys_user_seller");
//        genTemplateParam.setIgnoreTabelPrefix("sys_");
//        genTemplateParam.setBizCnName("卖家范围");
//        genTemplateParam.setTableName("seller");
//        genTemplateParam.setBizCnName("商家");
//        genTemplateParam.setTableName("coupon");
//        genTemplateParam.setBizCnName("优惠券");

    }

}