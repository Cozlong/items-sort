package com.example.hotelmanager.bean;

public class User {
    String img;//头像图片
    String id;//账号
    String pwd;//密码
    String phone_number;//电话号码
    String qq;//qq号
    String wechat;//微信号
    public User(String img,String id,String pwd,String phone_number,String qq,String wechat){
        this.img=img;
        this.id=id;
        this.pwd=pwd;
        this.phone_number=phone_number;
        this.qq=qq;
        this.wechat=wechat;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

}
