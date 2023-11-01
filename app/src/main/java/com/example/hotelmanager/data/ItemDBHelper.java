package com.example.hotelmanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAEM="item_db";
    private static final String TABLE_NAEM="item";
    private static final int DATABASE_VERSION=1;

    ItemDBHelper(Context context){
        super(context,DATABASE_NAEM,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSQL="create table "+TABLE_NAEM
                +"(item_id integer primary key autoincrement,"
                +"stu_no varchar(100),name varchar(100),"
                +"class varchar(100),publish data)";
        db.execSQL(strSQL);

        String sql="insert into "+TABLE_NAEM+"(stu_no,name,class,publish)"
                +"values('202001','韩其','计算机1班','2020-06-13')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
