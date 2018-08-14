package com.stylefeng.guns.po;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Proxy(lazy = false)
public class Coupon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer sellerid;
    @NotNull
    @JSONField(format = "yyyy-MM-dd")
    private Date beginDay;
    @NotNull
    @Future
    @JSONField(format = "yyyy-MM-dd")
    private Date endDay;
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date receptDay;
    private String userTel;
    private String useCode;
    private Integer used;
    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sellerid")
    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    @Basic
    @Column(name = "begin_day")
    public Date getBeginDay() {
        return beginDay;
    }

    public void setBeginDay(Date beginDay) {
        this.beginDay = beginDay;
    }

    @Basic
    @Column(name = "end_day")
    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    @Basic
    @Column(name = "recept_day")
    public Date getReceptDay() {
        return receptDay;
    }

    public void setReceptDay(Date receptDay) {
        this.receptDay = receptDay;
    }

    @Basic
    @Column(name = "user_tel")
    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Basic
    @Column(name = "use_code")
    public String getUseCode() {
        return useCode;
    }

    public void setUseCode(String useCode) {
        this.useCode = useCode;
    }

    @Basic
    @Column(name = "used")
    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
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
        Coupon coupon = (Coupon) o;
        return id == coupon.id &&
                sellerid == coupon.sellerid &&
                Objects.equals(beginDay, coupon.beginDay) &&
                Objects.equals(endDay, coupon.endDay) &&
                Objects.equals(receptDay, coupon.receptDay) &&
                Objects.equals(userTel, coupon.userTel) &&
                Objects.equals(useCode, coupon.useCode) &&
                Objects.equals(used, coupon.used) &&
                Objects.equals(createtime, coupon.createtime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sellerid, beginDay, endDay, receptDay, userTel, useCode, used, createtime);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Coupon{");
        sb.append("id=").append(id);
        sb.append(", sellerid=").append(sellerid);
        sb.append(", beginDay=").append(beginDay);
        sb.append(", endDay=").append(endDay);
        sb.append(", receptDay=").append(receptDay);
        sb.append(", userTel='").append(userTel).append('\'');
        sb.append(", useCode='").append(useCode).append('\'');
        sb.append(", used=").append(used);
        sb.append(", createtime=").append(createtime);
        sb.append('}');
        return sb.toString();
    }

    public static List<Coupon> createList(Coupon coupon, int num) {
        coupon.setUsed(Integer.valueOf(0));
        Date date = new Date();
        coupon.setCreatetime(date);
        ArrayList<Coupon> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Coupon temp = new Coupon();
            BeanUtils.copyProperties(coupon, temp);
            list.add(temp);
        }
        return list;
    }
}
