package com.example.springbootdemo.model.hui;

public class USERS {
    private Integer id;

    private String name;

    private String password;

    private String pic;

    private Short ban;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Short getBan() {
        return ban;
    }

    public void setBan(Short ban) {
        this.ban = ban;
    }
}