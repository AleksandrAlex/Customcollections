package com.suslovalex.recyclerview.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.suslovalex.customcollections.R;
import com.suslovalex.recyclerview.Model.MainMenuItem;
import com.suslovalex.recyclerview.Model.MainMenuRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * This is Activity for MainMenu. It consist of some items.
 */
public class MainMenuActivity extends AppCompatActivity {

    private List<MainMenuItem> mMainMenuItems = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MainMenuRecyclerAdapter mMainMenuRecyclerAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareCardsData();

        mRecyclerView = findViewById(R.id.myRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMainMenuRecyclerAdapter = new MainMenuRecyclerAdapter(mMainMenuItems/*, this*/);

        mRecyclerView.setAdapter(mMainMenuRecyclerAdapter);
    }

    /**
     * This method define some items
     */
    private void prepareCardsData() {

        mMainMenuItems.add(new MainMenuItem("title: first item","description: first item description",R.drawable.ic_android));
        mMainMenuItems.add(new MainMenuItem("title: second item","description: second item description",R.drawable.ic_build));
        mMainMenuItems.add(new MainMenuItem("title: third item","description: third item description",R.drawable.ic_wb_sunny));
    }
}
