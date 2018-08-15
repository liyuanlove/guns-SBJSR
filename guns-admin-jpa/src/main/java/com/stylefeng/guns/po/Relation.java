package com.stylefeng.guns.po;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Proxy(lazy = false)
@Table(name = "sys_relation", schema = "guns", catalog = "")
public class Relation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long menuid;
    private Integer roleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "menuid")
    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    @Basic
    @Column(name = "roleid")
    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation that = (Relation) o;
        return id == that.id &&
                Objects.equals(menuid, that.menuid) &&
                Objects.equals(roleid, that.roleid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, menuid, roleid);
    }
}
