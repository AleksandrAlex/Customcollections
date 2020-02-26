package com.suslovalex.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.suslovalex.view.activity.PlayerActivity;
import com.suslovalex.view.adapter.SelectSongRecyclerAdapter;


public class SongReceiver extends BroadcastReceiver {

    public static final String KEY_SONG_ID = "song_key_id";
    private static final String TAG = "Receiver";
    private int id;
    private Intent intentToPlayerActivity;


    @Override
    public void onReceive(Context context, Intent intent) {
        id = intent.getIntExtra(SelectSongRecyclerAdapter.INTENT_KEY_SONG_ID, -1);
        Log.d(TAG, String.valueOf(id));
      // intentToPlayerActivity = new Intent(context, PlayerActivity.class);
      // intentToPlayerActivity.putExtra(KEY_SONG_ID, id);
      // context.startActivity(intent);
    }
}
