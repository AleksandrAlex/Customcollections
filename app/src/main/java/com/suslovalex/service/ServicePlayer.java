package com.suslovalex.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.suslovalex.customcollections.R;
import com.suslovalex.view.activity.PlayerActivity;

public class ServicePlayer extends Service {

    public static int CURRENT_SONG_POSITION;
    public static final String POSITION = "current position";
    private MediaPlayer mPlayer;
    private SharedPreferences mSharedPreferences;
    private final IBinder mBinder = new PlayerBinder();
    private int mPathToSong;

    public class PlayerBinder extends Binder {

        public ServicePlayer getPlayer() {
            return ServicePlayer.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(PlayerActivity.MY_LOGS, "ServicePlayer onBind()");
        getPathToSong(intent);
        mPlayer = MediaPlayer.create(getApplicationContext(), mPathToSong);

        return mBinder;
    }

    private void getPathToSong(Intent intent) {
        mPathToSong = intent.getIntExtra(PlayerActivity.INTENT_KEY_SONG_PATH, -1);
        if (mPathToSong == -1) {
            mPathToSong = R.raw.ozzy_osbourne__i_just_want_you;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(PlayerActivity.MY_LOGS, "ServicePlayer onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(PlayerActivity.MY_LOGS, "ServicePlayer onStartCommand()");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(PlayerActivity.MY_LOGS, "ServicePlayer onDestroy()");
    }

    public void playMusic() {
        Log.d(PlayerActivity.MY_LOGS, "ServicePlayer. playMusic()");
        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(this, mPathToSong);
        }
        mPlayer.start();
    }

    public void pauseMusic() {
        Log.d(PlayerActivity.MY_LOGS, "ServicePlayer. pauseMusic()");
        if (mPlayer != null) {
            mPlayer.pause();
        }
    }

    public void stopMusic() {
        Log.d(PlayerActivity.MY_LOGS, "ServicePlayer. stopMusic()");
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void saveMusic() {
        CURRENT_SONG_POSITION = mPlayer.getCurrentPosition();
        mSharedPreferences = getSharedPreferences(POSITION, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(POSITION, CURRENT_SONG_POSITION);
        editor.apply();
    }

    public void loadMusic() {
        Log.d(PlayerActivity.MY_LOGS, "ServicePlayer. loadMusic()");
        mSharedPreferences = getSharedPreferences(POSITION, MODE_PRIVATE);
        int loadCurrentSongPosition = mSharedPreferences.getInt(POSITION, 0);
        mPlayer.seekTo(loadCurrentSongPosition);
    }

    public void setSong(int path) {
        mPathToSong = path;
        if (mPlayer != null) {
            mPlayer.stop();
        }
        if (mPathToSong == -1) {
            mPathToSong = R.raw.kassabian__fire;
        }
        mPlayer = MediaPlayer.create(getApplicationContext(), mPathToSong);
    }

    public boolean isPlaying() {
        if (mPlayer.isPlaying()) {
            return true;
        } else {
            return false;
        }
    }
}
