package com.easy.lokalsolution.Class;

public class QueryClass {


    String type,subtype,money,address,disc,note,uname,contact,whatsapp,contactime,image,id;
    Long time;

    public QueryClass() {
    }

    public QueryClass(String type, String subtype, String money, String address, String disc, String note, String uname, String contact, String whatsapp, String contactime, String image, String id, Long time) {
        this.type = type;
        this.subtype = subtype;
        this.money = money;
        this.address = address;
        this.disc = disc;
        this.note = note;
        this.uname = uname;
        this.contact = contact;
        this.whatsapp = whatsapp;
        this.contactime = contactime;
        this.image = image;
        this.id = id;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getContactime() {
        return contactime;
    }

    public void setContactime(String contactime) {
        this.contactime = contactime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
