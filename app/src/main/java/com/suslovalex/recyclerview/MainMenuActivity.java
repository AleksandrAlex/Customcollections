package com.suslovalex.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.suslovalex.customcollections.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This is Activity for MainMenu. It consist of some items.
 */
public class MainMenuActivity extends AppCompatActivity {

    private List<Item> mItems = new ArrayList<>();
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
        mMainMenuRecyclerAdapter = new MainMenuRecyclerAdapter(mItems/*, this*/);
        mRecyclerView.setAdapter(mMainMenuRecyclerAdapter);
    }

    /**
     * This method define some items
     */
    private void prepareCardsData() {

        mItems.add(new Item("title: first item","description: first item description",R.drawable.ic_android));
        mItems.add(new Item("title: second item","description: second item description",R.drawable.ic_build));
        mItems.add(new Item("title: third item","description: third item description",R.drawable.ic_wb_sunny));
    }
}
