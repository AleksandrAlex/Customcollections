package com.suslovalex.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.suslovalex.customcollections.R;

public class SongDatabase extends SQLiteOpenHelper {

    private static final String NAME_DATA_BASE = "Songs Data Base";
    private static final String TABLE_NAME = "My_Song";
    private static final int VERSION_DB = 1;
    private static final String FIELD_ARTIST = "ARTIST";
    private static final String FIELD_GENRE = "GENRE";
    private static final String FIELD_TITLE = "TITLE";
    private static final String FIELD_PATH = "PATH";

    SongDatabase (Context context){
        super(context, NAME_DATA_BASE,null,VERSION_DB);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +FIELD_ARTIST +" TEXT, "
        +FIELD_GENRE+" TEXT, "
        +FIELD_TITLE+" TEXT, "
        +FIELD_PATH+" INTEGER);");
        insertSong(db, "The Beatles", "Pop", "Let it be", R.raw.beatles__let_it_be);
        insertSong(db, "Scorpions", "Rock", "We were born to fly", R.raw.scorpions__we_were_born_to_fly);
        insertSong(db, "Red Hot Chili Peppers", "Rock", "Cant Stop", R.raw.red_hot_chili_pappers__cant_stop);
        insertSong(db, "Metallica", "Rock", "Unforgiven", R.raw.metallica__the_unforgiven);
        insertSong(db, "Linkin Park", "Rock", "Numb", R.raw.linkin_park__numb);
        insertSong(db, "Korn", "Rock", "Hater", R.raw.korn__hater);
        insertSong(db, "Kassabian", "Rock", "Club Foot", R.raw.kasabian__club_foot);
        insertSong(db, "Imagine Dragons", "Rock", "Polaroid", R.raw.imagine_dragons__polaroid);
        insertSong(db, "Ozzy Osbourne", "Rock", "I Just Want You", R.raw.ozzy_osbourne__i_just_want_you);
        insertSong(db, "Imagine Dragons", "Rock", "Demons", R.raw.imagine_dragons__demons);
        insertSong(db, "Coldplay", "Pop", "Christmas light", R.raw.coldplay__christmas_lights);
        insertSong(db, "Coldplay", "Pop", "Adventure of a lifetime", R.raw.coldplay__adventure_of_a_lifetime);
        insertSong(db, "Kanye West", "Pop", "All Of The Lights", R.raw.kanye_west__all_of_the_lights);
        insertSong(db, "Kassabian", "Rock", "L.S.F", R.raw.kassabian__l_s_f);
        insertSong(db, "Linkin Park", "Rock", "What I've done", R.raw.linkin_park__what_i_have_done);
        insertSong(db, "Ozzy Osbourne", "Rock", "Let It Die", R.raw.ozzy_osbourne__let_it_die);
        insertSong(db, "Linkin Park", "Rock", "By Myself", R.raw.linkin_park__by_yself);
        insertSong(db, "Red Hot Chili Peppers", "Rock", "Otherside", R.raw.red_hot_chili_peppers__otherside);
        insertSong(db, "Red Hot Chili Peppers", "Rock", "Snow", R.raw.red_hot_chilli_peppers__snow);
        insertSong(db, "Rihanna", "Pop", "Dancing In the Dark", R.raw.rihanna__dancing_in_the_dark);
        insertSong(db, "Scorpions", "Rock", "Are You The One", R.raw.scorpions__are_you_the_one);
        insertSong(db, "Scorpions", "Rock", "The Game Of Life", R.raw.scorpions_the_game_of_life);

    }

    private static void insertSong(SQLiteDatabase db, String artist, String genre, String title, int path) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ARTIST, artist);
        contentValues.put(FIELD_GENRE, genre);
        contentValues.put(FIELD_TITLE, title);
        contentValues.put(FIELD_PATH, path);
        db.insert(TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
