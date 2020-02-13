package com.suslovalex.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;

import com.suslovalex.customcollections.R;
import com.suslovalex.view.fragment.PlayerFragment;


public class PlayerActivity extends AppCompatActivity {

    public final static String SONG = "song";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlayerFragment fragment = new PlayerFragment();
        fragment.setSongId(R.raw.kassabian__fire);
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
