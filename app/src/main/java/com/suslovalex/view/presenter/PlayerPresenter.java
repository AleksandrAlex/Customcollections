package com.suslovalex.view.presenter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;
import com.suslovalex.Matching.SongMapper;
import com.suslovalex.model.Song;
import com.suslovalex.model.SongDatabaseHelper;
import com.suslovalex.service.ServicePlayer;
import com.suslovalex.view.activity.PlayerActivity;
import com.suslovalex.view.activity.SelectActivity;
import com.suslovalex.view.contracts.PlayerContract;
import java.util.List;
import static com.suslovalex.provider.ProviderDB.SONG_CONTENT_URI;
import static com.suslovalex.view.activity.PlayerActivity.INTENT_KEY_SONG_PATH;

public class PlayerPresenter implements PlayerContract.Presenter {

    private int mSongId;
    private Song mSong;
    private Intent mIntent;
    private ServicePlayer mServicePlayer;
    private boolean mBound = false;
    private PlayerContract.View mView;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(PlayerActivity.MY_LOGS, ". Service Connection onServiceConnected()");
            ServicePlayer.PlayerBinder playerBinder = (ServicePlayer.PlayerBinder) service;
            mServicePlayer = playerBinder.getPlayer();
            mServicePlayer.loadMusic();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(PlayerActivity.MY_LOGS, ". Service Connection onServiceDisconnected() ");
            mBound = false;
            mServicePlayer.stopMusic();
            mServicePlayer = null;
        }
    };

    public PlayerPresenter() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter. PlayerPresenter() ");
    }

    public PlayerPresenter(PlayerContract.View playerFragment) {
        Log.d(PlayerActivity.MY_LOGS, ". PlayerPresenter(PlayerContract.SelectView playerFragment) ");
        mView = playerFragment;
    }



    @Override
    public void prepareSong() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter. prepareSong()  ");
        mSong = getSongFromDB();
    }

    @Override
    public void prepareIntentToService() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter. prepareIntentToService() ");
        mIntent = new Intent(mView.getViewContext(), ServicePlayer.class);
        mIntent.putExtra(INTENT_KEY_SONG_PATH, mSong.getPath());
    }

    @Override
    public void bindPlayService() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter.  bindPlayService()");
        if (mView.getViewContext() != null)
            mView.getViewContext().bindService(mIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    public void unbindPlayerService(){
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter.  unbindPlayerService()");
        if (mBound) {
            if (mView.getViewContext() != null)
                mView.getViewContext().unbindService(mServiceConnection);
            mBound = false;
        }
    }

    public Song getSong() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter.  getSong()");
        return mSong;
    }

    public void setSongId(int songId) {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter. passSongIdToPresenter ");
        mSongId = songId;

    }

    public void setView(PlayerContract.View view) {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter. setView ");
        mView = view;
    }

    private Song getSongFromDB() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter. getSongFromDB() ");
        Song song = new Song();
        if (mView != null) {
            Cursor cursor = mView.getViewContext().getContentResolver()
                    .query(SONG_CONTENT_URI,
                            null,
                            SongDatabaseHelper.FIELD_ID + "=?",
                            new String[]{String.valueOf(mSongId)},
                            null);
            SongMapper mapper = new SongMapper();
            if (cursor != null) {
                List<Song> listSong = mapper.mappCursorToSongsList(cursor);
                for (Song tempSong : listSong) {
                    int id = tempSong.getId();
                    if (id == mSongId) {
                        song = tempSong;
                    }
                }
                cursor.close();
            }
        }
        return song;
    }
    @Override
    public void saveMusic() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter.  saveMusic()");
        mServicePlayer.stopMusic();
    }

    @Override
    public void playMusic() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter.  playMusic()");
        mServicePlayer.playMusic();
    }

    @Override
    public void pauseMusic() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter.  pauseMusic()");
        mServicePlayer.pauseMusic();
    }

    @Override
    public void stopMusic() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter. stopMusic() ");
        mServicePlayer.stopMusic();
    }

    @Override
    public void sendIntentToSelectActivity() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerPresenter.  sendIntentToSelectActivity()");
        Intent mIntentToSelectActivity;
        mIntentToSelectActivity = new Intent(mView.getViewContext(), SelectActivity.class);
        if (mServicePlayer.isPlaying()) {
            mServicePlayer.stopMusic();
        }
        mView.getViewContext().startActivity(mIntentToSelectActivity);
    }
}
