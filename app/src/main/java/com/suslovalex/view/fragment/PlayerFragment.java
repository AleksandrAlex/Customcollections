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


    private PlayerFragment() {
        //initParameters();
    }

    public static PlayerFragment getPlayerFragment(){
        if (playerFragment == null){
            playerFragment = new PlayerFragment();
        }
        return playerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment, container, false);
        initViewElements(v);
        //initParameters();
        mPlayerPresenter.prepareSong();
        setTextViewValues();
        mPlayerPresenter.prepareIntentToService();


        Log.d(PlayerActivity.MyLogs, "Player Fragment onCreateView()");
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(PlayerActivity.MyLogs, "PlayerFragment onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(PlayerActivity.MyLogs, "PlayerFragment onCreate()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(PlayerActivity.MyLogs, "PlayerFragment onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        mPlayerPresenter.bindPlayService();
        Log.d(PlayerActivity.MyLogs, "PlayerFragment onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(PlayerActivity.MyLogs, "PlayerFragment onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(PlayerActivity.MyLogs, "PlayerFragment onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        mPlayerPresenter.unbindPlayerService();
        Log.d(PlayerActivity.MyLogs,"PlayerFragment onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPlayerPresenter.saveMusic();
        mPlayerPresenter.unbindPlayerService();
        Log.d(PlayerActivity.MyLogs, "PlayerFragment onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(PlayerActivity.MyLogs, "PlayerFragment onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(PlayerActivity.MyLogs, "PlayerFragment onDetach()");
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    private void setTextViewValues() {
        Song song = mPlayerPresenter.getSong();
        if (song != null) {
            mSongArtistTextView.setText(song.getArtist());
            mSongTitleTextView.setText(song.getTitle());
            mSongGenreTextView.setText(song.getGenre());
        }
    }

    private void initViewElements(View view) {
        initializeViewByID(view);
        setButtonListeners();
    }

    private void setButtonListeners() {
        mBtnPlay.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnPause.setOnClickListener(this);
        mBtnSelect.setOnClickListener(this);
    }

    private void initializeViewByID(View view) {
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
        Log.d(PlayerActivity.MyLogs, "setSongId: ");
    }

     private void initParameters() {
         mPlayerPresenter = new PlayerPresenter(this);
     }
}
