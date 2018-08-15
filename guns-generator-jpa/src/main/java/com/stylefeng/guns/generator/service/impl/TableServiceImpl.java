package com.stylefeng.guns.generator.service.impl;

import com.stylefeng.guns.core.base.service.impl.BaseServiceImpl;
import com.stylefeng.guns.generator.dao.TablesDao;
import com.stylefeng.guns.generator.po.Tables;
import com.stylefeng.guns.generator.service.ITableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class TableServiceImpl extends BaseServiceImpl<Tables, String, TablesDao> implements ITableService {

    /**
     * 获取当前数据库所有的表信息
     */
    @Override
    public List<Map<String, Object>> getTablesOfDb(String dbName) {
        return baseDao.getAllTables(dbName);
    }
}
