package com.suslovalex.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.suslovalex.customcollections.R;
import com.suslovalex.view.adapter.SelectSongRecyclerAdapter;
import com.suslovalex.view.fragment.PlayerFragment;

public class PlayerActivity extends AppCompatActivity {

    public static final String MyLogs = "MyLogs";
    public final static int DEFAULT_SONG_ID = -1;
    public final static String INTENT_KEY_SONG_PATH = "intent_key_song_path";
    public final static String KEY_SONG_ID = "intent_key_song_id";
    private int mSongId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initSongId();

        Log.d(MyLogs, "PlayerActivity onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        createFragment();
        Log.d(MyLogs, "PlayerActivity onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MyLogs, "PlayerActivity onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MyLogs, "PlayerActivity onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MyLogs, "PlayerActivity onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MyLogs, "PlayerActivity onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MyLogs, "PlayerActivity onDestroy()");
    }

    private void initSongId() {
        Intent intentFromSongReceiver = getIntent();
        if (intentFromSongReceiver == null) {
            mSongId = DEFAULT_SONG_ID;
        } else {
            mSongId = intentFromSongReceiver.getIntExtra(SelectSongRecyclerAdapter.INTENT_KEY_SONG_ID, DEFAULT_SONG_ID);
        }
    }

    private void createFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlayerFragment fragment = PlayerFragment.getPlayerFragment();
        //passSongIdToFragment();
        Bundle arg = new Bundle();
        arg.putInt(KEY_SONG_ID, mSongId);
        fragment.setArguments(arg);
        //fragment.setSongId(mSongId);
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
    }

}
