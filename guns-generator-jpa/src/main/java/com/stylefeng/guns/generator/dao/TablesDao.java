package com.stylefeng.guns.generator.dao;

import com.stylefeng.guns.generator.po.Tables;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tc.jpa.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 优惠券Dao
 *
 * @author TC
 * @Date 2018-07-29 16:44:14
 */
@Repository
public interface TablesDao extends BaseDao<Tables, String> {

    @Query(nativeQuery = true, value = "select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.`TABLES` where TABLE_SCHEMA = ?1")
    public List<Map<String, Object>> getAllTables(String dbname);
}
