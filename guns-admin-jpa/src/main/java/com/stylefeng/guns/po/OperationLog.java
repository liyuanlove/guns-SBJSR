package com.stylefeng.guns.po;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Proxy(lazy = false)
@Table(name = "sys_operation_log", schema = "guns", catalog = "")
public class OperationLog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String logtype;
    private String logname;
    private Integer userid;
    private String classname;
    private String method;
    private Date createtime;
    private String succeed;
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "logtype")
    public String getLogtype() {
        return logtype;
    }

    public void setLogtype(String logtype) {
        this.logtype = logtype;
    }

    @Basic
    @Column(name = "logname")
    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    @Basic
    @Column(name = "userid")
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "classname")
    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Basic
    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "createtime")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "succeed")
    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationLog that = (OperationLog) o;
        return id == that.id &&
                Objects.equals(logtype, that.logtype) &&
                Objects.equals(logname, that.logname) &&
                Objects.equals(userid, that.userid) &&
                Objects.equals(classname, that.classname) &&
                Objects.equals(method, that.method) &&
                Objects.equals(createtime, that.createtime) &&
                Objects.equals(succeed, that.succeed) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, logtype, logname, userid, classname, method, createtime, succeed, message);
    }
}
