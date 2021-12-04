package com.example.alumnimanager.modal;

public class Chat {
    String cid;
    String msg;
    String fid;
    public Chat(){
    }

    public Chat(String cid, String msg, String fid) {
        this.cid = cid;
        this.msg = msg;
        this.fid = fid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}

