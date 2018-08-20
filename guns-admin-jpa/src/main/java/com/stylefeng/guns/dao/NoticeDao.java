package com.stylefeng.guns.dao;

import com.stylefeng.guns.po.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tan.jpa.dao.BaseDao;

import java.util.List;

/**
 * 通知Dao
 *
 * @author TC
 * @Date 2018-07-29 16:40:15
 */
@Repository
public interface NoticeDao extends BaseDao<Notice, Integer> {

    /**
     * 搜索
     *
     * @param name
     * @return
     */
    @Query("select o from Notice o where o.title like %:name% or o.content like %:name% order by o.createtime DESC")
    List<Notice> listByName(@Param("name") String name);

    /**
     * 所有
     *
     * @return
     */
    @Query("select o from Notice o order by o.createtime DESC")
    List<Notice> list();
}
