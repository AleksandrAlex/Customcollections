package com.suslovalex.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.suslovalex.view.activity.PlayerActivity;
import com.suslovalex.view.adapter.SelectSongRecyclerAdapter;


public class SongReceiver extends BroadcastReceiver {

    private int id;
    private Intent intentToPlayerActivity;

    @Override
    public void onReceive(Context context, Intent intent) {
        id = intent.getIntExtra(SelectSongRecyclerAdapter.INTENT_KEY_SONG_ID, PlayerActivity.DEFAULT_SONG_ID);

        intentToPlayerActivity = new Intent(context, PlayerActivity.class);
        intentToPlayerActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentToPlayerActivity.putExtra(SelectSongRecyclerAdapter.INTENT_KEY_SONG_ID, id);
        context.startActivity(intentToPlayerActivity);

        Log.d(PlayerActivity.MY_LOGS, "SongReceiver onReceive()");
    }
}
