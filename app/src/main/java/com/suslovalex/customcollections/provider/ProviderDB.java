package com.suslovalex.customcollections.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.suslovalex.model.SongDatabase;

public class ProviderDB extends ContentProvider {

    private static final String NAME_DATA_BASE = "Songs Data Base";
    private static final String TABLE_NAME = "My_Song";
    private static final int VERSION_DB = 1;
    private static final String FIELD_ARTIST = "ARTIST";
    private static final String FIELD_GENRE = "GENRE";
    private static final String FIELD_TITLE = "TITLE";
    private static final String FIELD_PATH = "PATH";
    private static final String SONG_ID = "_id";
    private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private SQLiteDatabase mDatabase;
    private SongDatabase mSongDatabase;


    static final String DB_CREATE = "CREATE TABLE "+TABLE_NAME+" ("+ SONG_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +FIELD_ARTIST +" TEXT, "
            +FIELD_GENRE+" TEXT, "
            +FIELD_TITLE+" TEXT, "
            +FIELD_PATH+" INTEGER);";

    static String AUTHORITY = "com.suslovalex.model.SongDatabase";
    //static final String SONG_PATH = "";
    public static final Uri SONG_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + TABLE_NAME);

    static final String SONG_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + TABLE_NAME;

    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + TABLE_NAME;

    @Override
    public boolean onCreate() {
        mSongDatabase = new SongDatabase(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        mDatabase = mSongDatabase.getReadableDatabase();
        Cursor cursor = mDatabase.query(TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),
                SONG_CONTENT_URI);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        mDatabase = mSongDatabase.getWritableDatabase();
        long rowID = mDatabase.insert(TABLE_NAME, null, values);
        Uri resultUri = ContentUris.withAppendedId(SONG_CONTENT_URI, rowID);
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
