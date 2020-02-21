package com.suslovalex.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SongDatabaseHelper extends SQLiteOpenHelper {

    public static final String NAME_DATA_BASE = "Songs Data Base";
    public static final String TABLE_NAME = "My_Songs";
    public static final int VERSION_DB = 1;
    public static final String FIELD_ID = "_id";
    public static final String FIELD_ARTIST = "ARTIST";
    public static final String FIELD_GENRE = "GENRE";
    public static final String FIELD_TITLE = "TITLE";
    public static final String FIELD_PATH = "PATH";

    public SongDatabaseHelper(Context context){
        super(context, NAME_DATA_BASE,null,VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+FIELD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
        +FIELD_ARTIST +" TEXT, "
        +FIELD_GENRE+" TEXT, "
        +FIELD_TITLE+" TEXT, "
        +FIELD_PATH+" INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
