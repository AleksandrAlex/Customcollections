package com.suslovalex.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.suslovalex.customcollections.R;

import static com.suslovalex.view.activity.PlayerActivity.SONG;

public class ServicePlayer extends Service {

    private MediaPlayer mPlayer;
    private int mPathToSong;
    private final IBinder mBinder = new PlayerBinder();

    public class PlayerBinder extends Binder{

       public ServicePlayer getPlayer(){
            return ServicePlayer.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPathToSong = intent.getIntExtra(SONG ,R.raw.moby_flower);//!!!!

        mPlayer = MediaPlayer.create(this, mPathToSong);//!!!!
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // !!!!!
       // playMusic();


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopMusic();
    }

    public void playMusic() {
        if (mPlayer == null){
            mPlayer = MediaPlayer.create(this,R.raw.kassabian_fire);
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
}
