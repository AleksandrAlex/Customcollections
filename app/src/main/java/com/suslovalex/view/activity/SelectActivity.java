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
import androidx.recyclerview.widget.RecyclerView;
import com.suslovalex.Matching.SongMapper;
import com.suslovalex.customcollections.R;
import com.suslovalex.model.Song;
import com.suslovalex.model.SongDatabaseHelper;
import com.suslovalex.view.adapter.SelectSongRecyclerAdapter;
import com.suslovalex.view.contracts.SelectContract;
import com.suslovalex.view.presenter.SelectPresenter;


import java.util.List;

public class SelectActivity extends AppCompatActivity implements SelectContract.SelectView {

    private static final String TAG = SelectActivity.class.getSimpleName();
    private static final String ALL = "All";
    private Spinner mArtistSpinner;
    private Spinner mGenreSpinner;
    private RecyclerView mSelectSongRecyclerView;
    private Button mShowButton;
    private String mSelectArtist = ALL;
    private String mSelectGenre = ALL;
    private SelectSongRecyclerAdapter mSongRecyclerAdapter;
    private RecyclerView.LayoutManager mLinearLayoutManager;
    private SelectPresenter selectPresenter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_song_activity);
        initSelectPresenter();
        selectPresenter.addDataToDB();
        Log.d(PlayerActivity.MY_LOGS, "SelectActivity addDataToDB()");
        initialization();
        Log.d(PlayerActivity.MY_LOGS, "SelectActivity initialization()");
    }

    private void initSelectPresenter() {
        selectPresenter = new SelectPresenter(this);
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
                prepareSpinners();
                passArtistFieldAndGenreFieldToSelectPresenter();
                selectPresenter.putSongsToRecyclerAdapter();
            }
        });
    }

    private void passArtistFieldAndGenreFieldToSelectPresenter() {
        selectPresenter.setSelectArtistField(mSelectArtist);
        selectPresenter.setSelectGenreField(mSelectGenre);
    }

    private void prepareSpinners() {
        mSelectArtist = mArtistSpinner.getSelectedItem().toString();
        mSelectGenre = mGenreSpinner.getSelectedItem().toString();
    }

    private void initialization() {
        initializeViews();
        selectPresenter.initializeParametres();
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
        String[] array = selectPresenter.getGenreArray();
        ArrayAdapter<?> genreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenreSpinner.setAdapter(genreAdapter);
    }

    private void setArtistSpinnerAdapter() {
        String[] array = selectPresenter.getArtistArray();
        ArrayAdapter<?> artistAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);
        artistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mArtistSpinner.setAdapter(artistAdapter);
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
