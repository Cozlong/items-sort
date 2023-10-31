package com.example.hotelmanager.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Hotel implements Serializable {
    private static final long serialVersionUID=1L;
    private int id;
    private String hotelPic;
    private String hotelType;
    private BigDecimal price;
    private String isfree;
    private String[] hotelnumList;

    public Hotel(int id,String hotelPic,String hotelType, BigDecimal price, String isfree, String[] hotelnumList) {
        this.id=id;
        this.hotelPic=hotelPic;
        this.hotelType = hotelType;
        this.price = price;
        this.isfree = isfree;
        this.hotelnumList = hotelnumList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelPic() {
        return hotelPic;
    }

    public void setHotelPic(String hotelPic) {
        this.hotelPic = hotelPic;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String shopType) {
        this.hotelType = shopType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsfree() {
        return isfree;
    }

    public void setIsfree(String isfree) {
        this.isfree = isfree;
    }

    public String[] getHotelnumList() {
        return hotelnumList;
    }

    public void setHotelnumList(String[] hotelnumList) {
        this.hotelnumList = hotelnumList;
    }
}
