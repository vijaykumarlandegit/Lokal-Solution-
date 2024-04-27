package com.easy.lokalsolution.Class;

public class OwnQueryClass {

    String id;
    Long time;

    public OwnQueryClass() {
    }

    public OwnQueryClass(String id, Long time) {
        this.id = id;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
