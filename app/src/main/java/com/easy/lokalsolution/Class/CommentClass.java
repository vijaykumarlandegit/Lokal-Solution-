package com.easy.lokalsolution.Class;

public class CommentClass {


String newsid,userid,comid,comment;
Long time;

    public CommentClass() {
    }

    public CommentClass(String newsid, String userid, String comid, String comment, Long time) {
        this.newsid = newsid;
        this.userid = userid;
        this.comid = comid;
        this.comment = comment;
        this.time = time;
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
