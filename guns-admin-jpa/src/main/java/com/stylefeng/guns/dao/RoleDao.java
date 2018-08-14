package com.stylefeng.guns.dao;

import com.stylefeng.guns.core.base.dao.BaseDao;
import com.stylefeng.guns.po.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色Dao
 *
 * @author TC
 * @Date 2018-07-29 16:37:33
 */
@Repository
public interface RoleDao extends BaseDao<Role, Integer> {

    /**
     * 查询所有
     *
     * @return
     */
    //org.springframework.orm.jpa.JpaSystemException: could not prepare statement;
    // nested exception is org.hibernate.exception.GenericJDBCException: could not prepare statement
    //解决方案：最好给别名加上‘’，或者使用as
//    @Query(nativeQuery = true, value = "select id, pid, name," +
//            " (case when (pid=0 or pid is null) then 'true' else 'false' end) 'open'" +
//            " FROM sys_role")
    @Query("select o.id as id, o.pid as pid, o.name as name, (case when (o.pid=0 or o.pid is null) then 'true' else 'false' end) as open" +
            " FROM Role o")
    public List<Map<String, Object>> roleTree();

    /**
     * 已选中树
     *
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT id,pid,name," +
            " (CASE WHEN (pid = 0 OR pid IS NULL) THEN 'true' ELSE 'false' END) 'open'," +
            " (CASE WHEN id in ?1 THEN 'true' ELSE 'false' END ) 'checked'" +
            " from sys_role ORDER BY pid, num ASC")
    public List<Map<String, Object>> checkedRoleTree(List<String> roleids);

    /**
     * 通过IDS获取角色名list
     *
     * @param roleids
     * @return
     */
    @Query(nativeQuery = true, value = "select name from sys_role where id in (?1)")
    public List<String> nameList(List<String> roleids);
}
