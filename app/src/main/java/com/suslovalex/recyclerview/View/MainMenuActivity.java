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
    private void prepareItemsData() {

        mMainMenuItems.add(new MainMenuItem("title: first item","description: first item description",R.drawable.ic_android));
        mMainMenuItems.add(new MainMenuItem("title: second item","description: second item description",R.drawable.ic_build));
        mMainMenuItems.add(new MainMenuItem("title: third item","description: third item description",R.drawable.ic_wb_sunny));
        mMainMenuItems.add(new MainMenuItem("title: fourth item","description: fourth item description",R.drawable.ic_announcement));
        mMainMenuItems.add(new MainMenuItem("title: fifth item","description: fifth item description",R.drawable.ic_audiotrack));
        mMainMenuItems.add(new MainMenuItem("title: sixth item","description: sixth item description",R.drawable.ic_call));
        mMainMenuItems.add(new MainMenuItem("title: seventh item","description: seventh item description",R.drawable.ic_camera));
        mMainMenuItems.add(new MainMenuItem("title: eighth item","description: eighth item description",R.drawable.ic_content_cut));
        mMainMenuItems.add(new MainMenuItem("title: ninth item","description: ninth item description",R.drawable.ic_email));
        mMainMenuItems.add(new MainMenuItem("title: tenth item","description: tenth item description",R.drawable.ic_flight));
        mMainMenuItems.add(new MainMenuItem("title: eleventh item","description: eleventh item description",R.drawable.ic_info));
        mMainMenuItems.add(new MainMenuItem("title: twelfth item","description: twelfth item description",R.drawable.ic_location));
        mMainMenuItems.add(new MainMenuItem("title: thirteenth item","description: thirteenth item description",R.drawable.ic_play));
    }
}
