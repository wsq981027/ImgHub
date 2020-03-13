package com.example.springbootdemo.model;

public class COMPLAINS {
    private Integer id;

    private Integer comid;

    private Integer userid;

    private Integer comuid;

    private String content;

    private String comuname;

    private String comupic;

    private String uname;

    private String upic;


    public String getComupic() {
        return comupic;
    }

    public void setComupic(String comupic) {
        this.comupic = comupic;
    }

    public String getUpic() {
        return upic;
    }

    public void setUpic(String upic) {
        this.upic = upic;
    }

    public String getComuname() {
        return comuname;
    }

    public void setComuname(String comuname) {
        this.comuname = comuname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getComuid() {
        return comuid;
    }

    public void setComuid(Integer comuid) {
        this.comuid = comuid;
    }
}