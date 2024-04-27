package com.easy.lokalsolution.Class;

public class UserClass {

    String image,name,email,number,token,userid,city;

    public UserClass() {
    }

    public UserClass(String userid) {
        this.userid = userid;
    }

    public UserClass(String image, String name, String email, String number, String token, String userid, String city) {
        this.image = image;
        this.name = name;
        this.email = email;
        this.number = number;
        this.token = token;
        this.userid = userid;
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
