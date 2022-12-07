package com.example.messagingapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBUserHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myuser.db";
    private static final int DATABASE_VERSION = 2;

    private static final String CREATE_TABLE_USER =
            "create table user (_id integer primary key autoincrement, username text, "
            + "password text, emailaddress text, firstname text, lastname text, phonenumber text, birthday text, " +
                    "birthmonth text, birthyear text)";
    public DBUserHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(DBUserHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
        db.execSQL("Drop Table IF EXISTS user");
        onCreate(db);
    }
}
