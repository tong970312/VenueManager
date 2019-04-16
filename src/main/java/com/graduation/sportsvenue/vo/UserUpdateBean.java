package com.graduation.sportsvenue.vo;

import java.util.Date;

public class UserUpdateBean {
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private Integer role;

    private Integer ifdelete;

    private String updatetime;

    public UserUpdateBean() {
    }

    public UserUpdateBean(Integer id, String username, String password, String phone, Integer role, Integer ifdelete, String updatetime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.ifdelete = ifdelete;
        this.updatetime = updatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getIfdelete() {
        return ifdelete;
    }

    public void setIfdelete(Integer ifdelete) {
        this.ifdelete = ifdelete;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "UserUpdateBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", ifdelete=" + ifdelete +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }
}
