package com.stylefeng.guns.po;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Proxy(lazy = false)
@Table(name = "sys_menu", schema = "guns", catalog = "")
public class Menu {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @NotBlank
    private String name;
    private String pcode;
    private String pcodes;
    private String icon;
    @NotBlank
    private String url;
    private Integer levels;
    private Integer num;
    private Integer ismenu;
    private Integer isopen;
    private Integer status;
    private String tips;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "pcode")
    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    @Basic
    @Column(name = "pcodes")
    public String getPcodes() {
        return pcodes;
    }

    public void setPcodes(String pcodes) {
        this.pcodes = pcodes;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "levels")
    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
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
    @Column(name = "ismenu")
    public Integer getIsmenu() {
        return ismenu;
    }

    public void setIsmenu(Integer ismenu) {
        this.ismenu = ismenu;
    }

    @Basic
    @Column(name = "isopen")
    public Integer getIsopen() {
        return isopen;
    }

    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
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
    @Column(name = "tips")
    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id == menu.id &&
                Objects.equals(code, menu.code) &&
                Objects.equals(name, menu.name) &&
                Objects.equals(pcode, menu.pcode) &&
                Objects.equals(pcodes, menu.pcodes) &&
                Objects.equals(icon, menu.icon) &&
                Objects.equals(url, menu.url) &&
                Objects.equals(levels, menu.levels) &&
                Objects.equals(num, menu.num) &&
                Objects.equals(ismenu, menu.ismenu) &&
                Objects.equals(isopen, menu.isopen) &&
                Objects.equals(status, menu.status) &&
                Objects.equals(tips, menu.tips);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code, name, pcode, pcodes, icon, url, levels, num, ismenu, isopen, status, tips);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Menu{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", pcode='").append(pcode).append('\'');
        sb.append(", pcodes='").append(pcodes).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", levels=").append(levels);
        sb.append(", num=").append(num);
        sb.append(", ismenu=").append(ismenu);
        sb.append(", isopen=").append(isopen);
        sb.append(", status=").append(status);
        sb.append(", tips='").append(tips).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
