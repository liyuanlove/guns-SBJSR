package com.stylefeng.guns.dao;

import com.stylefeng.guns.core.base.dao.BaseDao;
import com.stylefeng.guns.po.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 菜单Dao
 *
 * @author TC
 * @Date 2018-07-29 16:39:05
 */
@Repository
public interface MenuDao extends BaseDao<Menu, Long> {

    /**
     * 是否已经存在该CODE
     *
     * @param code
     * @return
     */
    public boolean existsByCodeIgnoreCase(String code);

    /**
     * 通过code查询菜单
     *
     * @param code
     * @return
     */
    public Menu findByCodeEquals(String code);

    /**
     * 查询所有子菜单(包括禁用的)
     *
     * @param pcodes
     * @return
     */
    public List<Menu> findByPcodesLike(String pcodes);

    /**
     * 批量查询
     *
     * @param menuids 如"1,2,3,4"
     * @return
     */
    @Query("select o.name from Menu o where o.id in (?1)")
    public List<String> menuNameList(String menuids);

    /**
     * 获取所有可访问的API
     *
     * @param roleid
     * @return
     */
    @Query(nativeQuery = true, value = "select url from sys_relation rel " +
            "inner join sys_menu m on rel.menuid = m.id and m.status=1 where rel.roleid = ?1")
    public List<String> getUrlsByRoleId(Integer roleid);

    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
//    @Query(nativeQuery = true, value = "SELECT m1.id as id,m1.NAME as name, " +
//            " (CASE WHEN (m2.id = 0 OR m2.id IS NULL) THEN 0 ELSE m2.id END ) as pid, " +
//            " (CASE WHEN (m2.id = 0 OR m2.id IS NULL) THEN 'true' ELSE 'false' END ) as open " +
//            " FROM sys_menu m1 " +
//            " LEFT join sys_menu m2 ON m2.status = 1 and m1.pcode = m2.CODE " +
//            " WHERE m1.status = 1 " +
//            " order by m1.levels, m1.num")
    @Query("SELECT m1.id as id,m1.name as name, " +
            " (CASE WHEN (m2.id = 0 OR m2.id IS NULL) THEN 0 ELSE m2.id END ) as pid, " +
            " (CASE WHEN (m2.id = 0 OR m2.id IS NULL) THEN 'true' ELSE 'false' END ) as open " +
            " FROM Menu m1 " +
            " LEFT join Menu m2 ON m2.status = 1 and m1.pcode = m2.code " +
            " WHERE m1.status = 1 " +
            " order by m1.levels, m1.num")
    public List<Map<String, Object>> tree();

    /**
     * 获取菜单列表树
     * 注意：JPA会自动将List to (?,?)
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    @Query(nativeQuery = true, value = "SELECT m1.id as id,m1.name as name, " +
            " ( CASE WHEN (m2.id = 0 OR m2.id IS NULL) THEN 0 ELSE m2.id END ) as pid, " +
            " ( CASE WHEN (m2.id = 0 OR m2.id IS  NULL) THEN 'true' ELSE 'false' END  ) as open, " +
            " ( CASE WHEN m1.id in ?1 THEN 'true' ELSE 'false'  END ) as checked " +
            " FROM sys_menu m1 LEFT join sys_menu m2 ON m2.status = 1 and m1.pcode = m2.code " +
            " WHERE m1.status = 1 ORDER BY m1.url")
    public List<Map<String, Object>> treeByMenuIds(List<Long> menuIds);

    /**
     * 根据角色获取菜单(侧边栏)
     *
     * @return
     * @date 2017年2月19日 下午10:35:40
     */
    @Query(nativeQuery = true, value = "SELECT m1.id as id, m1.icon as icon, m2.id as parentId, m1.NAME as name," +
            " m1.url as url, m1.levels as levels,  m1.ismenu as ismenu, m1.num as num" +
            " FROM sys_menu m1 LEFT join sys_menu m2 ON m2.status=1 AND m1.pcode=m2.code" +
            " INNER JOIN( SELECT id FROM sys_menu" +
            " WHERE id IN( SELECT menuid FROM sys_relation t3 WHERE t3.roleid IN ?1 ) )m3 ON m1.id=m3.id" +
            " where m1.ismenu=1 and m1.status=1 order by levels,num asc")
    public List<Map<String, Object>> getMenusByRoleIds(List<Integer> roleIds);
}
