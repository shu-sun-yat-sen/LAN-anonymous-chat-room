package com.example.demo1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity(name="user")
@Table(name="t_user")
public class User {

    @Id
    @NotNull
    private String Id;
    @NotEmpty
    private String fakeName;
    @JsonIgnore
    private String passwd;
    private String cookie;
    private String userpic;
    // 无参构造函数
    public User() {
    }

    // 构造函数
    public User( String id,String fakeName, String passwd) {
        this.Id=id;
        this.fakeName = fakeName;
        this.passwd = passwd;
        this.userpic ="userpic/little_bear.jpg";
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
    public void setUserpic(String pic){
        this.userpic=pic;
    }
    // Getter 方法

    public String getUserpic() {
        return userpic;
    }

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
        return "User[" + Id + " : "+fakeName+"]";
    }
}
