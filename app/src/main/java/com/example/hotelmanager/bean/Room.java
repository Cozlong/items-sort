package com.example.hotelmanager.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Room implements Serializable {
    private static final long serialVersionUID=1L;
    private String roomNo;
    private String roomType;
    private BigDecimal roomprice;
    private String isfree;
    private String roomPic;
    private String resident_name;
    private String start_time;
    private String during;

    public Room(){

    }

    public Room(String roomNo, String roomType, BigDecimal roomprice, String isfree, String roomPic,String resident_name,String start_time,String during) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.roomprice = roomprice;
        this.isfree = isfree;
        this.roomPic = roomPic;
        this.resident_name=resident_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getDuring() {
        return during;
    }

    public void setDuring(String during) {
        this.during = during;
    }

    public String getResident_name() {
        return resident_name;
    }

    public void setResident_name(String resident_name) {
        this.resident_name = resident_name;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getRoomprice() {
        return roomprice;
    }

    public void setRoomprice(BigDecimal roomprice) {
        this.roomprice = roomprice;
    }

    public String getIsfree() {
        return isfree;
    }

    public void setIsfree(String isfree) {
        this.isfree = isfree;
    }

    public String getRoomPic() {
        return roomPic;
    }

    public void setRoomPic(String roomPic) {
        this.roomPic = roomPic;
    }
}
