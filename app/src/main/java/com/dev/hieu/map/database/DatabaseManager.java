package com.dev.hieu.map.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dev.hieu.map.Constant;

public class DatabaseManager extends SQLiteOpenHelper implements Constant {
    public DatabaseManager(Context context) {
        super(context, "Maps", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constant.CREATE_TABLE_Maps);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Maps);
        onCreate(db);
    }
}
