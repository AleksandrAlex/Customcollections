package com.suslovalex.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.suslovalex.model.SongDatabaseHelper;

public class ProviderDB extends ContentProvider {

   private static final String DB_CREATE = "CREATE TABLE "
            +SongDatabaseHelper.TABLE_NAME+" ("
            +SongDatabaseHelper.FIELD_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SongDatabaseHelper.FIELD_ARTIST +" TEXT, "
            +SongDatabaseHelper.FIELD_GENRE+" TEXT, "
            +SongDatabaseHelper.FIELD_TITLE+" TEXT, "
            +SongDatabaseHelper.FIELD_PATH+" INTEGER);";

    private static final String LOG_TAG = "MyLog";
    private static final String SCHEME = "content://";
    private static final String AUTHORITY = "com.suslovalex.provider";
    public static final Uri SONG_CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + "/"+SongDatabaseHelper.TABLE_NAME);
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int SONGS = 1;
    private static final int SONGS_ID = 2;
    static {
        uriMatcher.addURI(AUTHORITY, SongDatabaseHelper.TABLE_NAME, SONGS);
        uriMatcher.addURI(AUTHORITY, SongDatabaseHelper.TABLE_NAME + "/#", SONGS_ID);
    }
    private SQLiteDatabase mDatabase;
    private SongDatabaseHelper mSongDatabaseHelper;

    @Override
    public boolean onCreate() {
        mSongDatabaseHelper = new SongDatabaseHelper(getContext());
            return true;
        }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        mDatabase = mSongDatabaseHelper.getReadableDatabase();
        Cursor cursor = mDatabase.query(SongDatabaseHelper.TABLE_NAME,projection,selection,selectionArgs,null,null,null);
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
        Log.d(LOG_TAG, "insert, " + uri.toString());
        if (uriMatcher.match(uri) != SONGS)
            throw new IllegalArgumentException("Wrong URI: " + uri);
        mDatabase = mSongDatabaseHelper.getWritableDatabase();
        long rowID = mDatabase.insert(SongDatabaseHelper.TABLE_NAME, null, values);
        Uri resultUri = ContentUris.withAppendedId(SONG_CONTENT_URI, rowID);
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
