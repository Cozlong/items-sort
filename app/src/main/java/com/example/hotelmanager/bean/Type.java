package com.example.hotelmanager.bean;

public class Type {
    int id;//id号
    String icon;//图标
    String type_name;//名称
    public Type(int id,String icon,String item_name){
        this.id=id;
        this.icon=icon;
        this.type_name=item_name;
    }
    public Type(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getIcon(){return icon;}

    public void setIcon(String icon){this.icon=icon;}


}
