package com.lenovo.smarttraffic.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {
    private static  final String CAR05="create table car05(id integer primary key autoincrement,tem integer,hum integer,light integer,co2 integer,pm25 integer,status integer)";
    private static final String CAR09="create table car092(id integer primary key autoincrement,card text,money integer,balance integer,user text,date text)";
    private static final String CAR22="create table car22(id integer primary key autoincrement,carid text,money integer,balance integer,user text,date text)";
    private static final String CAR31="create table car31(id integer primary key autoincrement,biaoti text,date text,zhuangtai text,neirong text,tel text)";
    private static final String CAR34="create table car34(id integer primary key autoincrement,card text,money integer,date text)";
    public SQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CAR05);
        sqLiteDatabase.execSQL(CAR09);
        sqLiteDatabase.execSQL(CAR22);
        sqLiteDatabase.execSQL(CAR31);
        sqLiteDatabase.execSQL(CAR34);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
