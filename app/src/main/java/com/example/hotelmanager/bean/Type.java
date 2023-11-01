package com.example.hotelmanager.bean;

public class Type {
    String icon;//图标
    String item_name;//物品名称
    public Type(String icon,String item_name){
        this.icon=icon;
        this.item_name=item_name;
    }

    public String getIcon(){return icon;}

    public void setIcon(String icon){this.icon=icon;}

    public String getItem_name(){return item_name;}

    public void setItem_name(String item_name){this.item_name=item_name;}
}
