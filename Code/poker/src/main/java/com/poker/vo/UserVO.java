package com.poker.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rjt on 16/8/10.
 */

@Entity(name="users")
public class UserVO {

    public UserVO(){
        super();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="username",unique = true,nullable = false)
    private String username;

    @Column(name="pwd",nullable = false)
    private String pwd;

    @Column(name="nickname",length=16,nullable = false)
    private String nickname;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}