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

    public static final String MY_LOGS = "MY_LOGS";
    public final static int DEFAULT_SONG_ID = -1;
    public final static String INTENT_KEY_SONG_PATH = "intent_key_song_path";
    public final static String KEY_SONG_ID = "intent_key_song_id";
    private int mSongId;
    private PlayerFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MY_LOGS, "PlayerActivity onCreate()"+ this.toString());
        setContentView(R.layout.activity_player);
        initSongId();
        createFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MY_LOGS, "PlayerActivity onStart()");
        fragment.setSongId(mSongId);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MY_LOGS, "PlayerActivity onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MY_LOGS, "PlayerActivity onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MY_LOGS, "PlayerActivity onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MY_LOGS, "PlayerActivity onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MY_LOGS, "PlayerActivity onDestroy()");
    }

    private void initSongId() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerActivity initSongId: ");
        Intent intentFromSongReceiver = getIntent();
        if (intentFromSongReceiver == null) {
            mSongId = DEFAULT_SONG_ID;
        } else {
            mSongId = intentFromSongReceiver.getIntExtra(SelectSongRecyclerAdapter.INTENT_KEY_SONG_ID, DEFAULT_SONG_ID);
        }
    }

    private void createFragment() {
        Log.d(PlayerActivity.MY_LOGS, "PlayerActivity createFragment: ");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = PlayerFragment.getPlayerFragment();
        //passSongIdToFragment();
       //Bundle arg = new Bundle();
       //arg.putInt(KEY_SONG_ID, mSongId);
       //fragment.setArguments(arg);

        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
    }

}
