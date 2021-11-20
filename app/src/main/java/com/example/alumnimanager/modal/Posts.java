package com.example.alumnimanager.modal;

public class Posts {
    String wid;
    String uname;
    String content;
    int likeCount;
    String fname;

    public Posts() {

    }

    public Posts(String wid, String uname, String content, int likeCount, String fname) {
        this.wid = wid;
        this.uname = uname;
        this.content = content;
        this.likeCount = likeCount;
        this.fname = fname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
