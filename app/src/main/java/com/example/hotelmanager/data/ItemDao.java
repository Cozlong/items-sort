package com.example.hotelmanager.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import com.example.hotelmanager.bean.Item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDao {
    private static final String TABLE_NAME = "item";
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;
    public SQLiteDatabase getDB(){
        return db;
    }
    public ItemDao(SQLiteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    //获取当前日期，格式化为yyyy年MM月dd日的字符串
    public static final String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    //向数据库插入一条记录
    public boolean insert(String sno,String sname,String sclazz){
        //获取数据库对象
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("stu_no",sno);
        contentValues.put("name",sname);
        contentValues.put("class",sclazz);
        contentValues.put("publish",getTime());
        //向表中插入记录
        long resv = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        if(resv == -1)
            return false;
        else
            return true;

    }
    //根据sid删除记录
    public int delete(int sid){
        db = dbHelper.getWritableDatabase();
        int count = db.delete(TABLE_NAME,"sid=?",new String[]{sid+""});
        db.close();
        return count;
    }
    //修改数据
    public int update(String sno,String sname,String sclazz,int sid){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("stu_no",sno);
        contentValues.put("name",sname);
        contentValues.put("class",sclazz);
        contentValues.put("publish",getTime());
        int count = db.update(TABLE_NAME,contentValues,"sid=?",new String[]{sid+""});
        db.close();
        return  count;
    }

    //查询所有数据
    @SuppressLint("Range")
    public List query(List<Item> list,String orderBy){
        db = dbHelper.getWritableDatabase();
        String selection = "1=1";
        Log.e("StudentDao",selection);
        Cursor cursor=db.query(TABLE_NAME,new String[]{"item_name","item_type","item_position","term_type","item_nmber","date_now","date_of_manufacture","quality_guarantee_period","remind_days"},
                selection,null,null,null,orderBy);
        list.clear();
        while(cursor.moveToNext()){
            Item item=new Item();
            map.put("sid",cursor.getInt(cursor.getColumnIndex("sid")));
            map.put("stu_no",cursor.getString(cursor.getColumnIndex("stu_no")));
            map.put("name",cursor.getString(cursor.getColumnIndex("name")));
            map.put("class",cursor.getString(cursor.getColumnIndex("class")));
            map.put("publish",cursor.getString(cursor.getColumnIndex("publish")));
            Log.e("StudentDao",map.get("sid")+","+map.get("stu_no")+","+map.get("name")+","+map.get("class")+","+map.get("publish"));
            list.add(item);
        }
        cursor.close();
        db.close();
        return list;
    }
}
