package com.example.hotelmanager.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {
    private static final long serialVersionUID=1L;
    private String order_id;
    private String order_create_time;
    private String order_state;
    private String order_homenum;
    private BigDecimal book_days;
    private String resident_name;
    private String resident_id;
    private String resident_sex;
    private String resident_phone;
    private String resident_remark;
    private BigDecimal order_price;
    public Order() {
    }

    public Order(String order_id, String order_create_time,String order_state,String order_homenum, BigDecimal book_days, String resident_name, String resident_id, String resident_sex, String resident_phone, String resident_remark, BigDecimal order_price) {
        this.order_id = order_id;
        this.order_create_time = order_create_time;
        this.order_state=order_state;
        this.order_homenum = order_homenum;
        this.book_days = book_days;
        this.resident_name = resident_name;
        this.resident_id = resident_id;
        this.resident_sex = resident_sex;
        this.resident_phone = resident_phone;
        this.resident_remark = resident_remark;
        this.order_price=order_price;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public void setBook_days(BigDecimal book_days) {
        this.book_days = book_days;
    }

    public BigDecimal getBook_days() {
        return book_days;
    }

    public BigDecimal getOrder_price() {
        return order_price;
    }

    public void setOrder_price(BigDecimal order_price) {
        this.order_price = order_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_create_time() {
        return order_create_time;
    }

    public void setOrder_create_time(String order_create_time) {
        this.order_create_time = order_create_time;
    }

    public String getOrder_homenum() {
        return order_homenum;
    }

    public void setOrder_homenum(String order_homenum) {
        this.order_homenum = order_homenum;
    }

    public String getResident_name() {
        return resident_name;
    }

    public void setResident_name(String resident_name) {
        this.resident_name = resident_name;
    }

    public String getResident_id() {
        return resident_id;
    }

    public void setResident_id(String resident_id) {
        this.resident_id = resident_id;
    }

    public String getResident_sex() {
        return resident_sex;
    }

    public void setResident_sex(String resident_sex) {
        this.resident_sex = resident_sex;
    }

    public String getResident_phone() {
        return resident_phone;
    }

    public void setResident_phone(String resident_phone) {
        this.resident_phone = resident_phone;
    }

    public String getResident_remark() {
        return resident_remark;
    }

    public void setResident_remark(String resident_remark) {
        this.resident_remark = resident_remark;
    }
}
