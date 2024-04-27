package com.easy.lokalsolution.Class;

public class LikeClass {

    String userid;
    Long time;

    public LikeClass() {
    }

    public LikeClass( String userid, Long time) {
        this.userid = userid;
        this.time = time;
    }



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
