package com.suslovalex.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;

import com.suslovalex.customcollections.R;
import com.suslovalex.view.adapter.SelectSongRecyclerAdapter;
import com.suslovalex.view.fragment.PlayerFragment;


public class PlayerActivity extends AppCompatActivity {

    public final static String INTENT_KEY_SONG_PATH = "intent_key_song_path";
    private int mSongId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initSongId();
        createFragment();
    }

    private void initSongId() {
        Intent intentFromSelectActivity = getIntent();
        mSongId = intentFromSelectActivity.getIntExtra(SelectSongRecyclerAdapter.INTENT_KEY_SONG_ID, -1);
        if (mSongId == -1){
            finish();
        }
    }

    private void createFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlayerFragment fragment = new PlayerFragment();
        fragment.setSongId(mSongId);
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
