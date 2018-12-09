package com.dev.hieu.map.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.hieu.map.Constant;
import com.dev.hieu.map.database.DatabaseManager;
import com.dev.hieu.map.model.Maps;

import java.util.ArrayList;
import java.util.List;

public class MapsDAO  implements Constant {
    private DatabaseManager databaseManager;

    public MapsDAO(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
    public void insertMaps(Maps maps) {
        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_ID,maps.id);
        contentValues.put(COLUMN_LAT, maps.lat);
        contentValues.put(COLUMN_LNG,maps.lng);


        long id = sqLiteDatabase.insert(TABLE_Maps, null, contentValues);

        Log.e("insertMaps", "insert : " + id);

        sqLiteDatabase.close();

    }
    public Maps getmapsbyName(String map) {

        Maps maps = null;

        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_Maps,
                new String[]{ COLUMN_LAT, COLUMN_LNG},
                COLUMN_LAT + "=?",
                new String[]{map}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

//            String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
            double lat = cursor.getDouble(cursor.getColumnIndex(COLUMN_LAT));
            double lng = cursor.getDouble(cursor.getColumnIndex(COLUMN_LNG));

            maps = new Maps();
//            maps.setId(id);
            maps.setLat(lat);
            maps.setLng(lng);
        }

        return maps;
    }
    public List<Maps> getAllMaps() {
        List<Maps> mapsList = new ArrayList<>();

        String SELECT_ALL_Maps = "SELECT * FROM " + TABLE_Maps;

        Log.e("getAllMaps", SELECT_ALL_Maps);

        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_Maps, null);


        if (cursor != null &&  cursor.moveToFirst()) {


            do {
//                String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                double lat = cursor.getDouble(cursor.getColumnIndex(COLUMN_LAT));
                double lng = cursor.getDouble(cursor.getColumnIndex(COLUMN_LNG));

                Maps maps = new Maps();
//                maps.setId(id);
                maps.setLat(lat);
                maps.setLng(lng);

                mapsList.add(maps);


            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return mapsList;
    }
}
