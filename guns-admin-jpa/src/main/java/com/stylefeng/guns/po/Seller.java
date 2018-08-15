package com.stylefeng.guns.po;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
//测试使用
@Proxy(lazy = false)
public class Seller {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    private String addr;
    @NotNull
    private Integer status;
    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "addr")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "createtime")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return id == seller.id &&
                Objects.equals(name, seller.name) &&
                Objects.equals(addr, seller.addr) &&
                Objects.equals(status, seller.status) &&
                Objects.equals(createtime, seller.createtime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, addr, status, createtime);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Seller{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", addr='").append(addr).append('\'');
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append('}');
        return sb.toString();
    }
}
