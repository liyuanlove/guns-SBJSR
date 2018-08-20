package com.stylefeng.guns.dao;

import com.stylefeng.guns.po.Dept;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tan.jpa.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 部门Dao
 *
 * @author TC
 * @Date 2018-07-30 16:11:11
 */
@Repository
public interface DeptDao extends BaseDao<Dept, Integer> {

    /**
     * 删除所有子部门
     *
     * @param pids
     */
    @Transactional
    @Modifying
    @Query("delete from Dept where pids like ?1")
    public void delByPidsLike(String pids);

    /**
     * 查询所有
     *
     * @return
     */
    @Query("select o from Dept o order by o.num ASC")
    List<Dept> list();

    /**
     * name过滤
     *
     * @param name
     * @return
     */
    @Query("select o from Dept o where o.simplename like %:name% or o.fullname like %:name% order by o.num ASC")
    List<Dept> listByName(@Param("name") String name);

    /**
     * 树形展示
     *
     * @return
     */
//    @Query(nativeQuery = true, value = "select id,pid,simplename as name," +
//            " ( CASE WHEN (pid = 0 OR pid IS NULL) THEN 'true' ELSE 'false' END ) 'open'" +
//            " from sys_dept")
    @Query("select o.id as id, o.pid as pid,o.simplename as name, ( CASE WHEN (o.pid = 0) THEN 'true' ELSE 'false' END ) as open" +
            " from Dept o")
    public List<Map<String, Object>> tree();

    /**
     * 查询子部门
     *
     * @param pids
     * @return
     */
    @Query("select id from Dept where pids like ?1")
    public List<Integer> subDeptIdList(String pids);

    /**
     * 部门范围
     *
     * @param pids
     * @return
     */
    @Query("select id from Dept where id = ?1 or pids like ?2")
    public List<Integer> deptAndSubDept(Integer id, String pids);

}
