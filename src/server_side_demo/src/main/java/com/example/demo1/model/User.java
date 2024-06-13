package com.example.demo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="user")
@Table(name="t_user")
public class User {

    @Id
    private String Id;
    private String fakeName;
    private String passwd;
    private String cookie;

    // 无参构造函数
    public User() {
    }

    // 构造函数
    public User(String userId, String fakeName, String passwd, String cookie) {
        this.Id = userId;
        this.fakeName = fakeName;
        this.passwd = passwd;
        this.cookie = cookie;
    }

    // Getter 方法
    public String getUserId() {
        return Id;
    }

    public String getFakeName() {
        return fakeName;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getCookie() {
        return cookie;
    }

    @Override
    public String toString() {
        return "User[" + Id + "]";
    }
}
