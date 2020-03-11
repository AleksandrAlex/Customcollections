package com.suslovalex.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.suslovalex.customcollections.R;
import com.suslovalex.view.adapter.SelectSongRecyclerAdapter;
import com.suslovalex.view.contracts.SelectView;
import com.suslovalex.view.presenter.SelectPresenter;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class SelectActivity extends MvpAppCompatActivity implements SelectView {

    private static final String FIX = "Click!";
    private static final String ALL = "All";
    private Spinner mArtistSpinner;
    private Spinner mGenreSpinner;
    private RecyclerView mSelectSongRecyclerView;
    private Button mShowButton;
    private String mSelectArtist = ALL;
    private String mSelectGenre = ALL;
    private SelectSongRecyclerAdapter mSongRecyclerAdapter;
    private RecyclerView.LayoutManager mLinearLayoutManager;
    @InjectPresenter
    SelectPresenter selectPresenter;
    @ProvidePresenter
    SelectPresenter providePresenter(){
        return new SelectPresenter(this.getApplication());
    }

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
                showChoseSongs();

            }
        });
    }

    @Override
    public void showChoseSongs() {
        prepareSpinners();
        passArtistFieldAndGenreFieldToSelectPresenter();
        selectPresenter.prepareSongs();
        setSongsToRecyclerAdapter();
    }

    private void setSongsToRecyclerAdapter() {
        mSongRecyclerAdapter.setSongs(selectPresenter.getSongs());
        mSongRecyclerAdapter.notifyDataSetChanged();
    }

    private void passArtistFieldAndGenreFieldToSelectPresenter() {
        selectPresenter.setSelectArtistField(mSelectArtist);
        selectPresenter.setSelectGenreField(mSelectGenre);
        Log.d(FIX, "passArtistFieldAndGenreFieldToSelectPresenter: " +mSelectArtist+" - "+ mSelectGenre);
    }

    private void prepareSpinners() {
        mSelectArtist = mArtistSpinner.getSelectedItem().toString();
        mSelectGenre = mGenreSpinner.getSelectedItem().toString();
        Log.d(FIX, "prepareSpinners: ");
    }

    private void initialization() {
        initializeViews();
        initializeParametres();
        selectPresenter.initialize();
        setViewElementsListeners();
        setSpinnersAdapters();
        setRecyclerParametres();
    }

    private void initializeParametres() {
        selectPresenter.initialize();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mSongRecyclerAdapter = new SelectSongRecyclerAdapter(selectPresenter.getSongs());
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

}
