package com.ltf.dao;

public class Reg {

    private int id;
    private String tpl;
    private String nickname;
    private String password;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTpl() {
        return tpl;
    }

    public void setTpl(String tpl) {
        this.tpl = tpl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Reg(int id, String tpl, String nickname, String password, String email) {
        this.id = id;
        this.tpl = tpl;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public Reg() {
    }
}
