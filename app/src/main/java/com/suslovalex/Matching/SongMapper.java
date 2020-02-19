package com.suslovalex.Matching;

import android.database.Cursor;

import com.suslovalex.model.Song;
import com.suslovalex.model.SongDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class SongMapper {
    public SongMapper() {
    }
    public List<Song> matchCursorToSongsList(Cursor cursor){
        List<Song> songList = new ArrayList<>();
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            songList.add(fromCursorToSong(cursor));
            while (cursor.moveToNext()){
                songList.add(fromCursorToSong(cursor));
            }
        }
        return songList;
    }

    private Song fromCursorToSong(Cursor cursor) {
            int columIdIndex = cursor.getColumnIndex(SongDatabaseHelper.FIELD_ID);
            int id = cursor.getInt(columIdIndex);
            int columIndexArtist = cursor.getColumnIndex(SongDatabaseHelper.FIELD_ARTIST);
            String artist = cursor.getString(columIndexArtist);
            int columIndexTitle = cursor.getColumnIndex(SongDatabaseHelper.FIELD_TITLE);
            String title = cursor.getString(columIndexTitle);
            int columIndexGenre = cursor.getColumnIndex(SongDatabaseHelper.FIELD_GENRE);
            String genre = cursor.getString(columIndexGenre);
            int columIndexPath = cursor.getColumnIndex(SongDatabaseHelper.FIELD_PATH);
            int path = cursor.getInt(columIndexPath);
            return new Song(id, artist, title, genre, path);
    }
}
