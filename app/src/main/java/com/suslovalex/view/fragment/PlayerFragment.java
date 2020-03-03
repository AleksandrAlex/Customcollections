package com.suslovalex.view.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
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

import com.suslovalex.Matching.SongMapper;
import com.suslovalex.customcollections.R;
import com.suslovalex.model.Song;
import com.suslovalex.model.SongDatabaseHelper;
import com.suslovalex.service.ServicePlayer;
import com.suslovalex.view.activity.PlayerActivity;
import com.suslovalex.view.activity.SelectActivity;

import java.util.List;

import static com.suslovalex.provider.ProviderDB.SONG_CONTENT_URI;
import static com.suslovalex.view.activity.PlayerActivity.INTENT_KEY_SONG_PATH;

public class PlayerFragment extends Fragment implements View.OnClickListener {

    private TextView mSong;
    private TextView mArtist;
    private TextView mGenre;
    private Button mPlay;
    private Button mPause;
    private Button mStop;
    private Button mSelect;
    private Intent mIntent;
    private Intent mIntentToSelectActivity;
    private ServicePlayer mServicePlayer;
    private boolean mBound = false;
    private int mSongId;
    private int mSongPath;

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ServicePlayer.PlayerBinder playerBinder = (ServicePlayer.PlayerBinder) service;
            mServicePlayer = playerBinder.getPlayer();
            //mServicePlayer.loadMusic();
            mBound = true;

            mServicePlayer.setSong(mSongPath);

            Log.d(PlayerActivity.MyLogs, "PlayerFragment. Service Connection onServiceConnected()");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            mServicePlayer.stopMusic();
            mServicePlayer.onDestroy();

            Log.d(PlayerActivity.MyLogs, "Player Fragment. Service Connection onServiceDisconnected() ");
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment, container, false);
        bindViews(v);
        mSongPath = getSongPathFromDB();
        setTitleToTextViews();
        putIntentToService();
        bindService();

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
        Log.d(PlayerActivity.MyLogs,"PlayerFragment onStop()");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mServicePlayer.saveMusic();
        if (mBound) {
            if (getContext() != null)
                getContext().unbindService(mServiceConnection);
            mBound = false;
        }
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

    private void setTitleToTextViews() {
        if (getContext() != null) {
            Cursor cursor = getContext().getContentResolver()
                    .query(SONG_CONTENT_URI,
                            null,
                            SongDatabaseHelper.FIELD_ID + "=?",
                            new String[]{String.valueOf(mSongId)},
                            null);
            SongMapper mapper = new SongMapper();
            if (cursor != null) {
                List<Song> listSong = mapper.mappCursorToSongsList(cursor);
                for (Song song : listSong) {
                    int id = song.getId();
                    if (id == mSongId) {
                        mSong.setText(song.getTitle());
                        mArtist.setText(song.getArtist());
                        mGenre.setText(song.getGenre());
                    }
                }
                cursor.close();
            }
        }
    }

    private void putIntentToService() {
        mIntent = new Intent(getContext(), ServicePlayer.class);
        mIntent.putExtra(INTENT_KEY_SONG_PATH, mSongPath);
    }

    private void bindService() {
        if (getContext() != null)
            getContext().bindService(mIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private int getSongPathFromDB() {
        int songPath = -1;
        if (getContext() != null) {
            Cursor cursor = getContext().getContentResolver()
                    .query(SONG_CONTENT_URI,
                            null,
                            SongDatabaseHelper.FIELD_ID + "=?",
                            new String[]{String.valueOf(mSongId)},
                            null);
            SongMapper mapper = new SongMapper();
            if (cursor != null) {
                List<Song> listSong = mapper.mappCursorToSongsList(cursor);
                for (Song song : listSong) {
                    int id = song.getId();
                    if (id == mSongId) {
                        songPath = song.getPath();
                    }
                }
                cursor.close();
            }
        }
        return songPath;
    }

    private void bindViews(View view) {
        initializeViewByID(view);
        setButtonListeners();

    }

    private void setButtonListeners() {
        mPlay.setOnClickListener(this);
        mStop.setOnClickListener(this);
        mPause.setOnClickListener(this);
        mSelect.setOnClickListener(this);
    }

    private void initializeViewByID(View view) {
        mSong = view.findViewById(R.id.song);
        mArtist = view.findViewById(R.id.artist);
        mGenre = view.findViewById(R.id.genre);
        mPlay = view.findViewById(R.id.playBtn);
        mPause = view.findViewById(R.id.pauseBtn);
        mStop = view.findViewById(R.id.stopBtn);
        mSelect = view.findViewById(R.id.selectBtn);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playBtn:
                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mServicePlayer.playMusic();
                    }
                });
                thread1.start();
                break;

            case R.id.pauseBtn:
                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mServicePlayer.pauseMusic();
                    }
                });
                thread2.start();
                break;

            case R.id.stopBtn:
                Thread thread3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mServicePlayer.stopMusic();
                    }
                });
                thread3.start();
                break;

            case R.id.selectBtn:
                mIntentToSelectActivity = new Intent(getContext(), SelectActivity.class);
               if (mServicePlayer.isPlaying()) {
                   mServicePlayer.stopMusic();
               }
                startActivity(mIntentToSelectActivity);
        }
    }

    public void setSongId(int songId) {
        mSongId = songId;
    }
}
