package com.easy.lokalsolution.Class;

public class NewsClass {


    String type,title,disc,usrerid,id,image;
    Long time;

    public NewsClass() {
    }

    public NewsClass(String type, String title, String disc, String usrerid, String id, String image, Long time) {
        this.type = type;
        this.title = title;
        this.disc = disc;
        this.usrerid = usrerid;
        this.id = id;
        this.image = image;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getUsrerid() {
        return usrerid;
    }

    public void setUsrerid(String usrerid) {
        this.usrerid = usrerid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
