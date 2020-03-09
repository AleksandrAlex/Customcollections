package com.suslovalex.view.activity;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.suslovalex.Matching.SongMapper;
import com.suslovalex.customcollections.R;
import com.suslovalex.model.Song;
import com.suslovalex.model.SongDatabaseHelper;
import com.suslovalex.view.adapter.SelectSongRecyclerAdapter;
import com.suslovalex.view.contracts.SelectContract;


import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity implements SelectContract.SelectView {

    private static final String TAG = SelectActivity.class.getSimpleName();
    private static final String ALL = "All";
    private static final Uri URI = Uri.parse("content://com.suslovalex.provider/My_Songs");
    private Spinner mArtistSpinner;
    private Spinner mGenreSpinner;
    private RecyclerView mSelectSongRecyclerView;
    private Button mShowButton;
    private String mSelectArtist = ALL;
    private String mSelectGenre = ALL;
    private SongMapper mSongMapper;
    private List<Song> mSongs;
    private SelectSongRecyclerAdapter mSongRecyclerAdapter;
    private RecyclerView.LayoutManager mLinearLayoutManager;
    private SelectContract.SelectPresenter selectPresenter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_song_activity);
        selectPresenter.addDataToDB();
        Log.d(PlayerActivity.MY_LOGS, "SelectActivity addDataToDB()");
        initialization();
        Log.d(PlayerActivity.MY_LOGS, "SelectActivity initialization()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(PlayerActivity.MY_LOGS, "SelectActivity onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(PlayerActivity.MY_LOGS, "SelectActivity onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(PlayerActivity.MY_LOGS, "SelectActivity onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(PlayerActivity.MY_LOGS, "SelectActivity onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(PlayerActivity.MY_LOGS, "SelectActivity onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(PlayerActivity.MY_LOGS, "SelectActivity onDestroy()");
    }

    private void setViewElementsListeners() {
        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursorClick = prepareCursorClick();
                mSongs = mSongMapper.mappCursorToSongsList(cursorClick);
                mSongRecyclerAdapter.setSongs(mSongs);
                mSongRecyclerAdapter.notifyDataSetChanged();


                for (Song song : mSongs) {
                    Log.d(TAG, song.toString());
                }
            }
        });
    }

    private Cursor prepareGenreCursor() {
        String[] projection = new String[]{"DISTINCT " + SongDatabaseHelper.FIELD_GENRE};
        return getContentResolver().query(URI, projection, null, null, null);
    }

    private Cursor prepareArtistCursor() {
        String[] projection = new String[]{"DISTINCT " + SongDatabaseHelper.FIELD_ARTIST};
        return getContentResolver().query(URI, projection, null, null, null);
    }

    private Cursor prepareCursorClick() {

        String[] protection = null;
        String selection = null;
        String[] selectionArgs = null;
        mSelectArtist = mArtistSpinner.getSelectedItem().toString();
        mSelectGenre = mGenreSpinner.getSelectedItem().toString();
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

        return getContentResolver().query(URI, protection, selection, selectionArgs, null);
    }

    private void initialization() {
        initializeViews();
        initializeParametres();
        setViewElementsListeners();
        setSpinnersAdapters();
        setRecyclerParametres();
    }

    private void setRecyclerParametres() {
        mSelectSongRecyclerView.setAdapter(mSongRecyclerAdapter);
        mSelectSongRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSelectSongRecyclerView.setHasFixedSize(true);
    }

    private void setSpinnersAdapters() {
        mArtistSpinner.setPrompt("Sorting by artist");
        mGenreSpinner.setPrompt("Sorting by title");
        setArtistSpinnerAdapter();
        setGenreSpinnerAdapter();
    }

    private void setGenreSpinnerAdapter() {
        Cursor cursor = prepareGenreCursor();
        String[] array = mSongMapper.mappCursorToGenre(cursor);
        ArrayAdapter<?> genreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenreSpinner.setAdapter(genreAdapter);
        cursor.close();
    }

    private void setArtistSpinnerAdapter() {
        Cursor cursor = prepareArtistCursor();
        String[] array = mSongMapper.mappCursorToArtist(cursor);
        ArrayAdapter<?> artistAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);
        artistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mArtistSpinner.setAdapter(artistAdapter);
        cursor.close();
    }

    private void initializeParametres() {
        mSongMapper = new SongMapper();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mSongs = new ArrayList<>();
        mSongRecyclerAdapter = new SelectSongRecyclerAdapter(mSongs);

    }


    private void initializeViews() {
        mShowButton = findViewById(R.id.showBtn);
        mArtistSpinner = findViewById(R.id.spinnerArtist);
        mGenreSpinner = findViewById(R.id.spinnerGenre);
        mSelectSongRecyclerView = findViewById(R.id.select_song_recycler_view);
    }





    @Override
    public Context getViewContext() {
        return getApplicationContext();
    }
}
