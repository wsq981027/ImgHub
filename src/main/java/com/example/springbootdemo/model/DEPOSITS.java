package com.example.springbootdemo.model;

public class DEPOSITS {
    private Integer id;

    private Integer uid;

    private Integer point;

    private Integer money;

    private String aliname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getAliname() {
        return aliname;
    }

    public void setAliname(String aliname) {
        this.aliname = aliname;
    }
}