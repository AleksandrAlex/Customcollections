package com.suslovalex.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.suslovalex.customcollections.R;

import static com.suslovalex.view.activity.PlayerActivity.INTENT_KEY_SONG_PATH;

public class ServicePlayer extends Service {

    public static int CURRENT_SONG_POSITION;
    public static final String POSITION = "current position";
    private MediaPlayer mPlayer;
    private SharedPreferences sharedPreferences;
    private final IBinder mBinder = new PlayerBinder();

    public class PlayerBinder extends Binder{

       public ServicePlayer getPlayer(){
            return ServicePlayer.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // two bloks
        int pathToSong = intent.getIntExtra(INTENT_KEY_SONG_PATH ,-1);
        if (pathToSong==-1){
            pathToSong = R.raw.ozzy_osbourne__i_just_want_you;
        }
        mPlayer = MediaPlayer.create(getApplicationContext(), pathToSong);
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void playMusic() {
        if (mPlayer == null){
            mPlayer = MediaPlayer.create(this,R.raw.kassabian__fire);
        }
        mPlayer.start();
    }

    public void pauseMusic(){
        if (mPlayer !=null) {
            mPlayer.pause();
        }
    }

    public void stopMusic(){
        if (mPlayer !=null) {
            mPlayer.release();
            mPlayer =null;
        }
    }

    public void saveMusic(){
        CURRENT_SONG_POSITION = mPlayer.getCurrentPosition();
        sharedPreferences = getSharedPreferences(POSITION, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(POSITION, CURRENT_SONG_POSITION);
        editor.commit();
    }

    public void loadMusic(){
        sharedPreferences = getSharedPreferences(POSITION, MODE_PRIVATE);
        int loadCurrentSongPosition = sharedPreferences.getInt(POSITION,0);
        mPlayer.seekTo(loadCurrentSongPosition);

    }
}
