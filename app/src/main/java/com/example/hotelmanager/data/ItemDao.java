package com.example.hotelmanager.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

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

    /**
     * 查询，并将结果存入list中。
     * @param list
     * @param strSQL
     */
    public void execQuery(List<Map<String,Object>> list, final String strSQL) {
        db = dbHelper.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery(strSQL, null);
            cursor.moveToFirst();
            list.clear();
            while (!cursor.isAfterLast()) {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("sid", cursor.getInt(0));
                Log.e("StudentDao Query",map.get("sid")+"");
                map.put("stu_no", cursor.getString(1));
                map.put("name", cursor.getString(2));
                map.put("class", cursor.getString(3));
                map.put("publish", cursor.getString(4));
                list.add(map);
                cursor.moveToNext();
            }
        }catch (RuntimeException e) {
            e.printStackTrace();
        }
        db.close();
    }

    /**
     * 执行sql语句，
     * @param strSQL
     * @return
     */
    public boolean execSQL(final String strSQL) {
        db = dbHelper.getWritableDatabase();
        db.beginTransaction();  //
        try {
            db.execSQL(strSQL);
            db.setTransactionSuccessful();  //
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }finally {
            db.endTransaction();    //
            db.close();
        }
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
    public List query(List<Map<String,Object>> list, String sno, String sname, String sclazz, String orderBy){
        db = dbHelper.getWritableDatabase();
        String selection = "1=1";
        if (!TextUtils.isEmpty(sno))
            selection += " and stu_no like '%"+sno+"%'";
        if (sname != null && !sname.isEmpty())
            selection += " and name like '%"+sname+"%'";
        if (sclazz != null && !sclazz.isEmpty())
            selection += " and class like '%"+sclazz+"%'";
        Log.e("StudentDao",selection);
        Cursor cursor=db.query(TABLE_NAME,new String[]{"sid","stu_no","name","class","publish"},
                selection,null,null,null,orderBy);
        list.clear();
        while(cursor.moveToNext()){
            HashMap map=new HashMap();
            map.put("sid",cursor.getInt(cursor.getColumnIndex("sid")));
            map.put("stu_no",cursor.getString(cursor.getColumnIndex("stu_no")));
            map.put("name",cursor.getString(cursor.getColumnIndex("name")));
            map.put("class",cursor.getString(cursor.getColumnIndex("class")));
            map.put("publish",cursor.getString(cursor.getColumnIndex("publish")));
            Log.e("StudentDao",map.get("sid")+","+map.get("stu_no")+","+map.get("name")+","+map.get("class")+","+map.get("publish"));
            list.add(map);
        }
        cursor.close();
        db.close();
        return list;
    }
}
