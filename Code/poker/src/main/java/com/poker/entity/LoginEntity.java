package com.poker.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by rjt on 16/8/11.
 */
public class LoginEntity extends ReturnEntity{
    @JsonInclude(JsonInclude.Include.NON_NULL) private String uid;

    @JsonInclude(JsonInclude.Include.NON_NULL) private String nickname;

    @JsonInclude(JsonInclude.Include.NON_NULL) private String sessionid;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

}
