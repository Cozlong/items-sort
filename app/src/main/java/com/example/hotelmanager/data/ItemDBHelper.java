package com.example.hotelmanager.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="item_db";

    private static final String TABLE_NAME1="item";
    private static final String TABLE_NAME2="user";
    private static final String TABLE_NAME3="type";
    private static final int DATABASE_VERSION=1;

    public ItemDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSQL1="create table "+TABLE_NAME1
                +"(item_id integer primary key autoincrement,"
                +"img varchar(100),item_name varchar(100),"
                +"item_type varchar(100),item_position varchar(100),"
                +"term_type varchar(100),item_number integer,"
                +"date_now varchar(100),date_of_manufacture date,"
                +"quality_guarantee_period integer,remind_days interger)";
        db.execSQL(strSQL1);
        String sql="insert into "+TABLE_NAME1+"(item_name,item_type,item_position,term_type,item_number,date_now,date_of_manufacture,quality_guarantee_period,remind_days)"
                +"values('牛奶','食品','抽屉','未到期','10','"+getTime()+"','2023-03-12',60,20)";
        db.execSQL(sql);
         sql="insert into "+TABLE_NAME1+"(item_name,item_type,item_position,term_type,item_number,date_now,date_of_manufacture,quality_guarantee_period,remind_days)"
                +"values('消炎药','医药','桌子上','过期','1','"+getTime()+"','2023-02-12',360,180)";
        db.execSQL(sql);
         sql="insert into "+TABLE_NAME1+"(item_name,item_type,item_position,term_type,item_number,date_now,date_of_manufacture,quality_guarantee_period,remind_days)"
                +"values('洗面奶','洗漱','桶里','临期','1','"+getTime()+"','2023-02-12',180,80)";
        db.execSQL(sql);
        String strSQL2="create table "+TABLE_NAME2
                +"(id varchar(100),pwd varchar(100),"
                +"user_name varchar(100),img varchar(100),"
                +"phone_number varchar(100),"
                +"qq varchar(100),wechat varchar(100))";
        db.execSQL(strSQL2);
        sql="insert into "+TABLE_NAME2+"(id,pwd,img,user_name,phone_number,qq,wechat)"
                +"values('czl','123','czlong','a.png','1234','11','22')";
        db.execSQL(sql);
        sql="insert into "+TABLE_NAME2+"(img,id,user_name,pwd,phone_number,qq,wechat)"
                +"values('zmj','456','zmjia','b.png','5678','33','44')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static final String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
}
