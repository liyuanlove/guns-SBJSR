package com.stylefeng.guns.generator.service;

import java.util.List;
import java.util.Map;

public interface ITableService {

    /**
     * 获取数据库所有的表
     *
     * @author fengshuonan
     * @date 2017-12-04-下午1:37
     */
    List<Map<String, Object>> getTablesOfDb(String dbName);
}
