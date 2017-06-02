package com.example.andrey.magnittest.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.andrey.magnittest.entities.Item;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "items.db";
    public static final String TABLE_NAME = "items_table";
    public static final String COL_NUM = "row_number";
    public static final String COL_RATE = "rate";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_NUM + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_RATE + " REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertRow(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NUM, item.getNumber());
        values.put(COL_RATE, item.getRate());
        long result = db.insert(TABLE_NAME, null, values);
        if(result==-1){
            return false;
        }else {
            System.out.println("success added");
            return true;
        }
    }


    public boolean updateRow(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NUM, item.getNumber());
        values.put(COL_RATE, item.getRate());
        long result = db.update(TABLE_NAME, values, COL_NUM + "=?", new String[]{String.valueOf(item.getNumber())});
        if(result==-1){
            return false;
        }else {
            System.out.println("success added");
            return true;
        }
    }

    public Cursor getAllItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }
}
