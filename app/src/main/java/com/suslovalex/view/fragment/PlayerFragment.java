package com.suslovalex.view.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.suslovalex.customcollections.R;
import com.suslovalex.model.Song;
import com.suslovalex.view.activity.PlayerActivity;
import com.suslovalex.view.contracts.PlayerContract;
import com.suslovalex.view.presenter.PlayerPresenter;

public class PlayerFragment extends Fragment implements View.OnClickListener, PlayerContract.View {

    private static PlayerFragment playerFragment;
    private TextView mSongTitleTextView;
    private TextView mSongArtistTextView;
    private TextView mSongGenreTextView;
    private Button mBtnPlay;
    private Button mBtnPause;
    private Button mBtnStop;
    private Button mBtnSelect;
    private PlayerPresenter mPlayerPresenter;
    private int mSongId;


    private PlayerFragment() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment PlayerFragment(): ");

    }

    public static PlayerFragment getPlayerFragment(){
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment getPlayerFragment() ");
        if (playerFragment == null){
            playerFragment = new PlayerFragment();
        }
        return playerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment onCreateView()");
        View v = inflater.inflate(R.layout.fragment, container, false);
        initViewElements(v);
        mPlayerPresenter.prepareSong();
        setTextViewValues();
        mPlayerPresenter.prepareIntentToService();

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment onCreate()");
        initParameters();
        passSongIdToPlayerPresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment onStart()");
        mPlayerPresenter.bindPlayService();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(PlayerActivity.MY_LOGS,"PlayerFragment onStop()");
        mPlayerPresenter.unbindPlayerService();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment onDestroyView()");
        mPlayerPresenter.saveMusic();
        mPlayerPresenter.unbindPlayerService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment onDetach()");
    }

    @Override
    public Context getViewContext() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment getViewContext()");
        return getContext();
    }

    private void setTextViewValues() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment setTextViewValues()");
        Song song = mPlayerPresenter.getSong();
        if (song != null) {
            mSongArtistTextView.setText(song.getArtist());
            mSongTitleTextView.setText(song.getTitle());
            mSongGenreTextView.setText(song.getGenre());
        }
    }

    private void initViewElements(View view) {
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment initViewElements()");
        initializeViewByID(view);
        setButtonListeners();
    }

    private void setButtonListeners() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment setButtonListener");
        mBtnPlay.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnPause.setOnClickListener(this);
        mBtnSelect.setOnClickListener(this);
    }

    private void initializeViewByID(View view) {
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment initializeViewByID()");
        mSongTitleTextView = view.findViewById(R.id.song);
        mSongArtistTextView = view.findViewById(R.id.artist);
        mSongGenreTextView = view.findViewById(R.id.genre);
        mBtnPlay = view.findViewById(R.id.playBtn);
        mBtnPause = view.findViewById(R.id.pauseBtn);
        mBtnStop = view.findViewById(R.id.stopBtn);
        mBtnSelect = view.findViewById(R.id.selectBtn);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment onClick()");
        switch (v.getId()) {
            case R.id.playBtn:
                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mPlayerPresenter.playMusic();
                    }
                });
                thread1.start();
                break;

            case R.id.pauseBtn:
                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mPlayerPresenter.pauseMusic();
                    }
                });
                thread2.start();
                break;

            case R.id.stopBtn:
                Thread thread3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mPlayerPresenter.stopMusic();
                    }
                });
                thread3.start();
                break;

            case R.id.selectBtn:
               mPlayerPresenter.sendIntentToSelectActivity();
        }
    }

    public void setSongId(int songId) {
        mPlayerPresenter.setSongId(songId);
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment setSongId: ");
    }

    private void initParameters() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment initParameters()");
        mPlayerPresenter = new PlayerPresenter(this);
      // if (getArguments() != null) {
      //     mSongId = getArguments().getInt(PlayerActivity.KEY_SONG_ID);
      // }

    }

    private void passSongIdToPlayerPresenter() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerFragment passSongIdToPlayerPresenter()");
        mPlayerPresenter.setSongId(mSongId);
    }
}
