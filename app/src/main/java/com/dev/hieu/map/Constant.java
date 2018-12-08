package com.dev.hieu.map;

public interface Constant {

    String TABLE_Maps = "Maps";

    String COLUMN_ID = "IDC";
    String COLUMN_LAT = "Lat";
    String COLUMN_LNG = "Lng";


    String CREATE_TABLE_Maps = "CREATE TABLE " + TABLE_Maps + "(" +

            COLUMN_ID + " NVARCHAR PRIMARY KEY," +

            COLUMN_LAT + " NVARCHAR," +

            COLUMN_LNG + " NVARCHAR," +

            ")";
}
