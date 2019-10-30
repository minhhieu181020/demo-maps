package com.example.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MapsDAO {

    public static final String TABLE_MAPS = "MAPS";
    private SQLiteDatabase dbW,dbR;
    private SqliteHelper dbhelper;
    public static final String TAG = "MapsDAO";

    public static final String SQL_MAPS = "" +
            "CREATE TABLE " + TABLE_MAPS + " (title NVARCHAR primary key , " +
            "longtitude double, latitude double);";

public MapsDAO (Context context){
   dbhelper=new SqliteHelper(context);
   dbW=dbhelper.getReadableDatabase();
   dbR=dbhelper.getWritableDatabase();

}

public boolean insertMAPS(Maps maps){
    ContentValues values=new ContentValues();
    values.put("title",maps.getTitle());
    values.put("longtitude",maps.getLongtitude());
    values.put("latitude",maps.getLatitude());
long result=dbW.insert(TABLE_MAPS,null,values);
    try {
        if (result < 0) {
            return false;
        }
    } catch (Exception e) {
        Log.e(TAG, "INSERT MAPS" + e.toString());
        return false;
    }
    return true;

}
    public boolean updateMAPS(Maps maps){
        ContentValues values=new ContentValues();
        values.put("title",maps.getTitle());
        values.put("longtitude",maps.getLongtitude());
        values.put("latitude",maps.getLatitude());
        long result=dbW.update(TABLE_MAPS,values,"title" +" =?",new String[]{maps.getTitle()});
        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG,   e.toString());
            return false;
        }
        return true;

    }
    public boolean isDelete(String name){
        long result = dbW.delete(TABLE_MAPS, "title"    + " =?", new String[]{name});
        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
            return false;
        }
        return true;

    }

    public List<Maps> selectMAPS() {
        List<Maps> mapsList = new ArrayList<>();
        // b2 : viet cau lenh select

        String select = "SELECT * FROM " + TABLE_MAPS;

        // b3 : su dung cau lenh rawQuery
        Cursor cursor = dbR.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                Maps maps = new Maps();
                maps.setTitle(cursor.getString(0));
                maps.setLongtitude(cursor.getDouble(1));
                maps.setLatitude(cursor.getDouble(2));
                mapsList.add(maps);
            } while (cursor.moveToNext());

            // dong ket noi con tro
            cursor.close();
        }
        return mapsList;
    }
    public Maps selectMAPSByNameADR(String name) {
        // b2 : viet cau lenh select

        String select = "SELECT * FROM " + TABLE_MAPS + " WHERE title = ?";

        Maps maps = new Maps();
        // b3 : su dung cau lenh rawQuery
        Cursor cursor = dbR.rawQuery(select, new String[]{name});
        cursor.moveToFirst();

        maps.setTitle(cursor.getString(0));
        maps.setLongtitude(cursor.getDouble(1));
        maps.setLatitude(cursor.getDouble(2));


        // dong ket noi con tro
        cursor.close();
        return maps;
    }

}
