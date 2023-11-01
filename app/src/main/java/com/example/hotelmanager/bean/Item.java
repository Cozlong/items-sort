package com.example.hotelmanager.bean;

import java.util.Date;

public class Item {
    String img;//图片
    String item_name;//物品名称
    String item_type;//物品类型
    String item_position;//存放位置
    String term_type;//期限类型
    int item_number;//物品数量,

    Date date_now;//登记日期
    Date date_of_manufacture;//生产日期
    int quality_guarantee_period;//保质期
    int remind_days;//提醒日期
    public Item(String img,String item_name,String item_type,String item_position,String term_type,int item_number,Date date_now,Date date_of_manufacture,int quality_guarantee_period,int remind_days){
        this.img=img;
        this.item_name=item_name;
        this.item_type=item_type;
        this.item_position=item_position;
        this.term_type=term_type;
        this.item_number=item_number;
        this.date_now=date_now;
        this.date_of_manufacture=date_of_manufacture;
        this.quality_guarantee_period=quality_guarantee_period;
        this.remind_days=remind_days;
    }
    public String getImg() {return img;}

    public void setImg(String img) {this.img=img;}
    public String getItem_name() {return item_name;}

    public void setItem_name(String item_name) {this.item_name=item_name;}

    public String getItem_type() {return item_type;}

    public void setItem_type(String item_type) {this.item_type=item_type;}

    public String getItem_position() {return item_position;}

    public void setItem_position(String item_position) {this.item_position=item_position;}

    public String getTerm_type() {return term_type;}

    public void setTerm_type(String term_type) {this.term_type=term_type;}

    public int getItem_number() {return item_number;}

    public void setItem_number(int item_number) {this.item_number=item_number;}
    public Date getDate_now() {return date_now;}

    public void setDate_now(Date date_now) {this.date_now=date_now;}

    public Date getDate_of_manufacture() {return date_of_manufacture;}

    public void setDate_of_manufacture(Date date_of_manufacture) {this.date_of_manufacture=date_of_manufacture;}

    public int getQuality_guarantee_period() {return quality_guarantee_period;}

    public void setQuality_guarantee_period(int quality_guarantee_period) {this.quality_guarantee_period=quality_guarantee_period;}

    public int getRemind_days() {return remind_days;}

    public void setRemind_days(int remind_days) {this.remind_days=remind_days;}
}
