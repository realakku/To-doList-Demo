package com.db1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="tbl_Student";
    public static final String S_COL1="ID";
    public static final String S_COL2="Name";
    public static final String S_COL3="Marks";

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertData(String name,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(S_COL2,name);
        contentValues.put(S_COL3,marks);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return true;
        else
            return false;
    }

    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null); //to select data
        return res;
    }

    public void delData(int id) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME, S_COL1 + "=" + id, null);
    }

    public boolean updateData(String id,String name,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(S_COL1,id);
        contentValues.put(S_COL2,name);
        contentValues.put(S_COL3,marks);
        db.update(TABLE_NAME,contentValues,S_COL1+"=?",new String[] {id});
        return true;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" ("+S_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+S_COL2+" TEXT,"+S_COL3+" INTEGER)");

    }
}
