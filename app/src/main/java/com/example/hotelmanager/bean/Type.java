package com.example.hotelmanager.bean;

public class Type {
    int id;//id号
    String icon;//图标
    String type_name;//名称
    int count;//数量

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Type(int id, String icon, String item_name, int count){
        this.id=id;
        this.icon=icon;
        this.type_name=item_name;
        this.count=count;
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
