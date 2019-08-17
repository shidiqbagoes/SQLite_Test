package com.w3learnteam.mobile_project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final int VERSION = 3;
    static final String DB_NAME = "pemesanan_makanan.db";


    //Definisi Tabel Makanan
    static  final String TABLE_SEMBAKO = "sembako";

    static final String S_ID = "_id";
    static final String S_NAME = "sname";
    static final String S_HARGA = "sharga";
    static final String S_PRICE = "sprice";
    static final String S_JUMSEM = "sjumsem";
    //----------------------------------------------

    //Definisi Tabel Beli
    static  final String TABLE_BELI = "beli";

    static final String B_ID = "_id";
    static final String B_NAME = "bname";
    static final String B_PRICE = "bprice";
    static final String B_JUMBEL = "bjumbel";
    static final String B_JUMSEM = "bjumsem";

    //-----------------------------------------------

    //Definisi Tabel User

    static  final String TABLE_USER = "user";

    static final String U_ID = "_id";
    static final String U_USERNAME = "uusername";
    static final String U_PASSWORD = "upassword";
    static final String U_NAME = "uname";
    static final String U_BIO = "ubio";
    static final String U_BALANCE = "ubalance";

    //------------------------------------------------

    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE IF NOT EXISTS "+TABLE_SEMBAKO+"("+
                    S_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    S_NAME+" TEXT,"+S_HARGA+" TEXT,"+
                    S_PRICE+" TEXT,"+
                    S_JUMSEM+" TEXT);";
        sqLiteDatabase.execSQL(sql);

        String sql1="CREATE TABLE IF NOT EXISTS "+TABLE_BELI+"("+
                B_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                B_NAME+" TEXT,"+B_PRICE+" TEXT,"+
                B_JUMBEL+" TEXT,"+
                B_JUMSEM+" TEXT);";
        sqLiteDatabase.execSQL(sql1);

        String sql2="CREATE TABLE IF NOT EXISTS "+TABLE_USER+"("+
                U_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                U_USERNAME+" TEXT,"+U_PASSWORD+" TEXT,"+
                U_NAME+" TEXT,"+
                U_BIO+" TEXT,"+
                U_BALANCE+" TEXT);";
        sqLiteDatabase.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_SEMBAKO);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_BELI);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        onCreate(sqLiteDatabase);
    }

}
