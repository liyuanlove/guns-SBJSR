package com.stylefeng.guns.core.ztree;

import lombok.Data;
import org.hibernate.annotations.Proxy;

/**
 * jquery ztree 插件的节点
 *
 * @author fengshuonan
 * @date 2017年2月17日 下午8:25:14
 */
@Data
@Proxy(lazy = false)
public class ZTreeNode {

    private Long id;    //节点id
    private String name;//节点名称
    private Boolean open;//是否打开节点
    private Boolean checked;//是否被选中
    private Long pid = 0L;//父节点id

    public static ZTreeNode createRoot() {
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setId(0L);
        zTreeNode.setName("顶级");
        zTreeNode.setChecked(false);
        zTreeNode.setOpen(true);
        zTreeNode.setPid(null);
        return zTreeNode;
    }
}
