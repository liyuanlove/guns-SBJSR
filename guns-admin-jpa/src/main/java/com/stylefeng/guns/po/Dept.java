package com.stylefeng.guns.po;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Proxy(lazy = false)
@Table(name = "sys_dept", schema = "guns", catalog = "")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private Integer num;
    private Integer pid;
    private String pids;
    @NotBlank
    private String simplename;
    private String fullname;
    private String tips;
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Basic
    @Column(name = "pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "pids")
    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    @Basic
    @Column(name = "simplename")
    public String getSimplename() {
        return simplename;
    }

    public void setSimplename(String simplename) {
        this.simplename = simplename;
    }

    @Basic
    @Column(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "tips")
    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Basic
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return id == dept.id &&
                Objects.equals(num, dept.num) &&
                Objects.equals(pid, dept.pid) &&
                Objects.equals(pids, dept.pids) &&
                Objects.equals(simplename, dept.simplename) &&
                Objects.equals(fullname, dept.fullname) &&
                Objects.equals(tips, dept.tips) &&
                Objects.equals(version, dept.version);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, num, pid, pids, simplename, fullname, tips, version);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Dept{");
        sb.append("id=").append(id);
        sb.append(", num=").append(num);
        sb.append(", pid=").append(pid);
        sb.append(", pids='").append(pids).append('\'');
        sb.append(", simplename='").append(simplename).append('\'');
        sb.append(", fullname='").append(fullname).append('\'');
        sb.append(", tips='").append(tips).append('\'');
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
