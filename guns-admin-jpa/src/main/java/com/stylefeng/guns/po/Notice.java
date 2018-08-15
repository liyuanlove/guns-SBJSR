package com.stylefeng.guns.po;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Proxy(lazy = false)
@Table(name = "sys_notice", schema = "guns", catalog = "")
public class Notice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Integer type;
    private String content;
    private Date createtime;
    private Integer creater;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "creater")
    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return id == notice.id &&
                Objects.equals(title, notice.title) &&
                Objects.equals(type, notice.type) &&
                Objects.equals(content, notice.content) &&
                Objects.equals(createtime, notice.createtime) &&
                Objects.equals(creater, notice.creater);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, type, content, createtime, creater);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Notice{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", type=").append(type);
        sb.append(", content='").append(content).append('\'');
        sb.append(", createtime=").append(createtime);
        sb.append(", creater=").append(creater);
        sb.append('}');
        return sb.toString();
    }
}
