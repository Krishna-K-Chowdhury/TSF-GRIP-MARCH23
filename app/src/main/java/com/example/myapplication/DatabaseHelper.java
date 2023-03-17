package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1234500000,'Arnab',5000.00,'email1@gmail.com','XXXXXXXXXXXX0000','ABC00000000')");
        db.execSQL("insert into user_table values(1234511111,'Rohit',5000.00,'email2@gmail.com','XXXXXXXXXXXX1111','ABC11111111')");
        db.execSQL("insert into user_table values(1234522222,'Rahul',5000.00,'email3@gmail.com','XXXXXXXXXXXX2222','ABC22222222')");
        db.execSQL("insert into user_table values(1234533333,'Kritika',5000.00,'email4@gmail.com','XXXXXXXXXXXX3333','ABC33333333')");
        db.execSQL("insert into user_table values(1234544444,'Hardik',5000.00,'email5@gmail.com','XXXXXXXXXXXX4444','ABC44444444')");
        db.execSQL("insert into user_table values(1234555555,'Shyerash',5000.00,'email6@gmail.com','XXXXXXXXXXXX5555','ABC55555555')");
        db.execSQL("insert into user_table values(1234566666,'Ananya',5000.00,'email7@gmail.com','XXXXXXXXXXXX6666','ABC66666666')");
        db.execSQL("insert into user_table values(1234577777,'Parul',5000.00,'email8@gmail.com','XXXXXXXXXXXX7777','ABC77777777')");
        db.execSQL("insert into user_table values(1234588888,'Kartik',5000.00,'email9@gmail.com','XXXXXXXXXXXX8888','ABC88888888')");
        db.execSQL("insert into user_table values(1234599999,'Anusha',5000.00,'email10@gmail.com','XXXXXXXXXXXX9999','ABC99999999')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
