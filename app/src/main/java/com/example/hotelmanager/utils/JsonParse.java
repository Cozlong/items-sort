package com.example.hotelmanager.utils;

import com.example.hotelmanager.bean.Hotel;
import com.example.hotelmanager.bean.Order;
import com.example.hotelmanager.bean.Room;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonParse {
    private static JsonParse instance;

    private JsonParse(){}

    public static JsonParse getInstance(){
        if(instance==null)
            instance=new JsonParse();
        return instance;
    }

    public List<Hotel> getHotelList(String json){
        Gson gson=new Gson();
        return gson.fromJson(json,new TypeToken<List<Hotel>>(){}.getType());
    }

    public List<Order> getOrdersList(String json){
        Gson gson=new Gson();
        return gson.fromJson(json,new TypeToken<List<Order>>(){}.getType());
    }
}
