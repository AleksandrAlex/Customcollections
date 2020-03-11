package com.suslovalex.view.presenter;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.suslovalex.Matching.SongMapper;
import com.suslovalex.customcollections.R;
import com.suslovalex.model.Song;
import com.suslovalex.model.SongDatabaseHelper;
import com.suslovalex.view.contracts.SelectView;
import java.util.ArrayList;
import java.util.List;
import moxy.InjectViewState;
import moxy.MvpPresenter;

import static androidx.constraintlayout.widget.Constraints.TAG;

@InjectViewState
public class SelectPresenter extends MvpPresenter<SelectView> {

    private static final String ALL = "All";
    private static final Uri URI = Uri.parse("content://com.suslovalex.provider/My_Songs");
    private Application mView;
    private SongMapper mSongMapper;
    private List<Song> mSongs;
    private String mSelectArtist = ALL;
    private String mSelectGenre = ALL;

    public SelectPresenter(Application view) {
        mView = view;
    }

    public void addDataToDB() {
        final Cursor cursor = mView.getContentResolver().query(URI, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                Log.d(TAG, "Amount of return;notes is " + cursor.getCount());
            } else {
                initData();
            }
            cursor.close();
        }
    }
    private void initData() {
        addSingleSongToDB(new Song(1, "The Beatles", "Pop", "Let it be", R.raw.beatles__let_it_be));
        addSingleSongToDB(new Song(2, "Scorpions", "Pop", "We were born to fly", R.raw.scorpions__we_were_born_to_fly));
        addSingleSongToDB(new Song(3, "Red Hot Chili Peppers", "Rock", "Cant Stop", R.raw.red_hot_chili_pappers__cant_stop));
        addSingleSongToDB(new Song(4, "Metallica", "Rock", "Unforgiven", R.raw.metallica__the_unforgiven));
        addSingleSongToDB(new Song(5, "Linkin Park", "Rock", "Numb", R.raw.linkin_park__numb));
        addSingleSongToDB(new Song(6, "Korn", "Rock", "Hater", R.raw.korn__hater));
        addSingleSongToDB(new Song(7, "Kassabian", "Rock", "Club Foot", R.raw.kasabian__club_foot));
        addSingleSongToDB(new Song(8, "Imagine Dragons", "Rock", "Polaroid", R.raw.imagine_dragons__polaroid));
        addSingleSongToDB(new Song(9, "Ozzy Osbourne", "Rock", "I Just Want You", R.raw.ozzy_osbourne__i_just_want_you));
        addSingleSongToDB(new Song(10, "Imagine Dragons", "Rock", "Demons", R.raw.imagine_dragons__demons));
        addSingleSongToDB(new Song(11, "Coldplay", "Pop", "Christmas light", R.raw.coldplay__christmas_lights));
        addSingleSongToDB(new Song(12, "Coldplay", "Pop", "Adventure of a lifetime", R.raw.coldplay__adventure_of_a_lifetime));
        addSingleSongToDB(new Song(13, "Kanye West", "Pop", "All Of The Lights", R.raw.kanye_west__all_of_the_lights));
        addSingleSongToDB(new Song(14, "Kassabian", "Rock", "L.S.F", R.raw.kassabian__l_s_f));
        addSingleSongToDB(new Song(15, "Linkin Park", "Rock", "What I've done", R.raw.linkin_park__what_i_have_done));
        addSingleSongToDB(new Song(16, "Ozzy Osbourne", "Rock", "Let It Die", R.raw.ozzy_osbourne__let_it_die));
        addSingleSongToDB(new Song(17, "Linkin Park", "Rock", "By Myself", R.raw.linkin_park__by_yself));
        addSingleSongToDB(new Song(18, "Red Hot Chili Peppers", "Rock", "Otherside", R.raw.red_hot_chili_peppers__otherside));
        addSingleSongToDB(new Song(19, "Red Hot Chili Peppers", "Rock", "Snow", R.raw.red_hot_chilli_peppers__snow));
        addSingleSongToDB(new Song(20, "Rihanna", "Pop", "Dancing In the Dark", R.raw.rihanna__dancing_in_the_dark));
        addSingleSongToDB(new Song(21, "Scorpions", "Rock", "Are You The One", R.raw.scorpions__are_you_the_one));
        addSingleSongToDB(new Song(22, "Scorpions", "Pop", "The Game Of Life", R.raw.scorpions_the_game_of_life));

    }
    private void addSingleSongToDB(Song song) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SongDatabaseHelper.FIELD_ARTIST, song.getArtist());
        contentValues.put(SongDatabaseHelper.FIELD_GENRE, song.getGenre());
        contentValues.put(SongDatabaseHelper.FIELD_TITLE, song.getTitle());
        contentValues.put(SongDatabaseHelper.FIELD_PATH, song.getPath());

        mView.getContentResolver().insert(URI, contentValues);
    }


    public void initialize() {
        mSongMapper = new SongMapper();
        mSongs = new ArrayList<>();
    }


    public void prepareSongs() {
        Cursor cursorClick = prepareCursorClick();
        mSongs = mSongMapper.mappCursorToSongsList(cursorClick);
        cursorClick.close();

        for (Song song : mSongs) {
            Log.d(TAG, song.toString());
        }
    }

    private Cursor prepareCursorClick() {

        String[] protection = null;
        String selection = null;
        String[] selectionArgs = null;
        if (mSelectArtist.equals("ALL") && mSelectGenre.equals("ALL")) {
            selection = null;
            selectionArgs = null;
        } else if (mSelectArtist.equals("ALL")) {
            selection = SongDatabaseHelper.FIELD_GENRE + "=?";
            selectionArgs = new String[]{mSelectGenre};
        } else if (mSelectGenre.equals("ALL")) {
            selection = SongDatabaseHelper.FIELD_ARTIST + "=?";
            selectionArgs = new String[]{mSelectArtist};
        } else {
            selection = SongDatabaseHelper.FIELD_ARTIST + "=?" + " AND " + SongDatabaseHelper.FIELD_GENRE + "=?";
            selectionArgs = new String[]{mSelectArtist, mSelectGenre};
        }

        return mView.getContentResolver().query(URI, protection, selection, selectionArgs, null);
    }


    public void setSelectArtistField(String artist){
        mSelectArtist = artist;
    }


    public void setSelectGenreField(String genre){
        mSelectGenre = genre;
    }


    public String [] getArtistArray(){
        Cursor cursor = prepareArtistCursor();
        String[] array = mSongMapper.mappCursorToArtist(cursor);
        cursor.close();
        return array;
    }


    public String[] getGenreArray() {
        Cursor cursor = prepareGenreCursor();
        String[] array = mSongMapper.mappCursorToGenre(cursor);
        cursor.close();
        return array;
    }

    private Cursor prepareGenreCursor() {
        String[] projection = new String[]{"DISTINCT " + SongDatabaseHelper.FIELD_GENRE};
        return mView.getContentResolver().query(URI, projection, null, null, null);
    }

    private Cursor prepareArtistCursor() {
        String[] projection = new String[]{"DISTINCT " + SongDatabaseHelper.FIELD_ARTIST};
        return mView.getContentResolver().query(URI, projection, null, null, null);
    }

    public List<Song> getSongs() {
        return mSongs;
    }
}
