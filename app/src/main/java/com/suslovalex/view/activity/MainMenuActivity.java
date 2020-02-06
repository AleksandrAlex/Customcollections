package com.suslovalex.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.suslovalex.customcollections.R;
import com.suslovalex.model.MainMenuItem;
import com.suslovalex.view.adapter.MainMenuRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * This is Activity for MainMenu. It consist of some items.
 */
public class MainMenuActivity extends AppCompatActivity {

    private final String ITEM = " item";
    private final String DESCRIPTION = "Description: ";


    public List<MainMenuItem> mMainMenuItems = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MainMenuRecyclerAdapter mMainMenuRecyclerAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareItemsData();

        mRecyclerView = findViewById(R.id.myRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMainMenuRecyclerAdapter = new MainMenuRecyclerAdapter(mMainMenuItems);
        mRecyclerView.setAdapter(mMainMenuRecyclerAdapter);
    }
    /**
     * This method define some items
     */
    private void prepareItemsData() {//!!!!for

        mMainMenuItems.add(new MainMenuItem("first"+ITEM,DESCRIPTION+"first item description",R.drawable.ic_android));
        mMainMenuItems.add(new MainMenuItem("second"+ITEM,DESCRIPTION+"second item description",R.drawable.ic_build));
        mMainMenuItems.add(new MainMenuItem("third"+ITEM,DESCRIPTION+"third item description",R.drawable.ic_wb_sunny));
        mMainMenuItems.add(new MainMenuItem("fourth"+ITEM,DESCRIPTION+"fourth item description",R.drawable.ic_announcement));
        mMainMenuItems.add(new MainMenuItem("fifth"+ITEM,DESCRIPTION+"fifth item description",R.drawable.ic_audiotrack));
        mMainMenuItems.add(new MainMenuItem("sixth"+ITEM,DESCRIPTION+"sixth item description",R.drawable.ic_call));
        mMainMenuItems.add(new MainMenuItem("seventh"+ITEM,DESCRIPTION+"seventh item description",R.drawable.ic_camera));
        mMainMenuItems.add(new MainMenuItem("eighth"+ITEM,DESCRIPTION+"eighth item description",R.drawable.ic_content_cut));
        mMainMenuItems.add(new MainMenuItem("ninth"+ITEM,DESCRIPTION+"ninth item description",R.drawable.ic_email));
        mMainMenuItems.add(new MainMenuItem("tenth"+ITEM,DESCRIPTION+"tenth item description",R.drawable.ic_flight));
        mMainMenuItems.add(new MainMenuItem("eleventh"+ITEM,DESCRIPTION+"eleventh item description",R.drawable.ic_info));
        mMainMenuItems.add(new MainMenuItem("twelfth"+ITEM,DESCRIPTION+"twelfth item description",R.drawable.ic_location));
        mMainMenuItems.add(new MainMenuItem("thirteenth"+ITEM,DESCRIPTION+"thirteenth item description",R.drawable.ic_play));
    }
}
