package com.example.alumnimanager.modal;

public class User {
    String uname;
    String fname;
    String password;

    public User(String uname, String fname, String password) {
        this.uname = uname;
        this.fname = fname;
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
