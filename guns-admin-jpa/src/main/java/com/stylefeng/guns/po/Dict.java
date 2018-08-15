package com.stylefeng.guns.po;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Proxy(lazy = false)
@Table(name = "sys_dict", schema = "guns", catalog = "")
public class Dict {
    //NOTICE:ID AND GeneratedValue must be together
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private Integer num;
    private Integer pid;
    private String name;
    private String tips;
    private String code;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dict dict = (Dict) o;
        return id == dict.id &&
                Objects.equals(num, dict.num) &&
                Objects.equals(pid, dict.pid) &&
                Objects.equals(name, dict.name) &&
                Objects.equals(tips, dict.tips) &&
                Objects.equals(code, dict.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, num, pid, name, tips, code);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Dict{");
        sb.append("id=").append(id);
        sb.append(", num=").append(num);
        sb.append(", pid=").append(pid);
        sb.append(", name='").append(name).append('\'');
        sb.append(", tips='").append(tips).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
