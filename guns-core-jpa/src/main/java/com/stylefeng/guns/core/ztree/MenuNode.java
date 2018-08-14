package com.stylefeng.guns.core.ztree;

import com.stylefeng.guns.core.constant.IsMenu;
import lombok.Data;

import java.util.*;

/**
 * @author fengshuonan
 * @Description 菜单的节点
 * @date 2016年12月6日 上午11:34:17
 */
@Data
public class MenuNode implements Comparable {

    //节点id
    private Long id;

    private Long parentId;

    private String name;

    private Integer levels;

    private Integer ismenu;

    private Integer num;

    private String url;

    private String icon;

    private List<MenuNode> children;

    /**
     * 查询子节点时候的临时集合
     */
    private List<MenuNode> linkedList = new ArrayList<MenuNode>();

    public MenuNode() {
        super();
    }

    public MenuNode(Long id, Long parentId) {
        super();
        this.id = id;
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MenuNode{");
        sb.append("id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", levels=").append(levels);
        sb.append(", ismenu=").append(ismenu);
        sb.append(", num=").append(num);
        sb.append(", url='").append(url).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", children=").append(children);
        sb.append(", linkedList=").append(linkedList);
        sb.append('}');
        return sb.toString();
    }

    /**
     * 重写排序比较接口，首先根据等级排序，然后更具排序字段排序
     *
     * @param obj
     * @return
     */
    @Override
    public int compareTo(Object obj) {
        MenuNode menuNode = (MenuNode) obj;
        Integer num = menuNode.getNum();
        Integer levels = menuNode.getLevels();
        if (num == null) {
            num = 0;
        }
        if (levels == null) {
            levels = 0;
        }
        //先级别后排序
        if (this.levels.compareTo(levels) == 0) {
            return this.num.compareTo(num);
        } else {
            return this.levels.compareTo(levels);
        }

    }


    public static MenuNode createRoot() {
        return new MenuNode(0L, -1L);
    }

    /**
     * 构建页面菜单列表
     */
    public static List<MenuNode> buildTitle(List<MenuNode> nodes) {
        if (nodes.size() <= 0) {
            return nodes;
        }
        //剔除非菜单
        nodes.removeIf(node -> node.getIsmenu() != IsMenu.YES.getCode());
        //对菜单排序，返回列表按菜单等级，序号的排序方式排列
        Collections.sort(nodes);
        return mergeList(nodes, nodes.get(nodes.size() - 1).getLevels(), null);
    }

    /**
     * 递归合并数组为子数组，最后返回第一层
     *
     * @param menuList 需要处理的结点
     * @param rank     当前级别
     * @param listMap
     * @return
     */
    private static List<MenuNode> mergeList(List<MenuNode> menuList, int rank, Map<Long, List<MenuNode>> listMap) {
        //保存当次调用总共合并了多少元素
        int n;
        //保存当次调用总共合并出来的list
        Map<Long, List<MenuNode>> currentMap = new HashMap<>();
        //由于按等级从小到大排序，需要从后往前排序
        //判断该节点是否属于当前循环的等级,不等于则跳出循环
        for (n = menuList.size() - 1; n >= 0 && menuList.get(n).getLevels() == rank; n--) {
            //判断之前的调用是否有返回以该节点的id为key的map，有则设置为children列表。
            Long id = menuList.get(n).getId();
            if (listMap != null && listMap.get(id) != null) {
                menuList.get(n).setChildren(listMap.get(id));
            }
            Long parentId = menuList.get(n).getParentId();
            if (parentId != null && parentId != 0) {
                //判断当前节点所属的pid是否已经创建了以该pid为key的键值对，没有则创建新的链表
                currentMap.computeIfAbsent(parentId, k -> new LinkedList<>());
                //将该节点插入到对应的list的头部（KEY值为PID）
                currentMap.get(parentId).add(0, menuList.get(n));
            }
        }
        //边界处理
        if (n < 0) {
            return menuList;
        }
        //递归处理剩余结点(将处理过的结点合并到上级)
        else {
            return mergeList(menuList.subList(0, n + 1), menuList.get(n).getLevels(), currentMap);
        }
    }


}
