package com.suslovalex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.suslovalex.presenter.BreakingNewsPresenter;
import com.suslovalex.adapter.NewsAdapter;
import com.suslovalex.customcollections.R;
import com.suslovalex.model.Article;

import java.util.ArrayList;
import java.util.List;

public class BreakingNewsMainActivity extends AppCompatActivity implements IBreakingNewsView {

    private RecyclerView mNewsRecyclerView;
    private NewsAdapter mNewsAdapter;
    private BreakingNewsPresenter mPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressDialog mDialog;
    private Spinner spinner;
    private ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareRecyclerView();
        initPresenter();
        prepareSpinner();
        loadData();
        prepareSwipeRefreshLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.closeNetwork();
    }

    @Override
    public void showNews(List<Article> articles){
        mNewsAdapter.setArticles(articles);
    }

    @Override
    public void showDialogLoading() {
        mDialog = new ProgressDialog(this);
        mDialog.setCancelable(false);
        mDialog.setTitle("Breaking News!");
        mDialog.setMessage("Loading...");
        mDialog.show();
    }

    @Override
    public void showDialogError() {
        mDialog.setMessage("Error....sorry");
        mDialog.show();
    }

    @Override
    public void hideDialog() {
        mDialog.dismiss();
    }


    private void prepareRecyclerView() {
        mNewsRecyclerView = findViewById(R.id.recycler_news);
        mNewsAdapter = new NewsAdapter();
        mNewsAdapter.setArticles(new ArrayList<Article>());
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNewsRecyclerView.setAdapter(mNewsAdapter);
    }

    private void initPresenter() {
        mPresenter = new BreakingNewsPresenter(this);
    }

    private void prepareSwipeRefreshLayout() {
       mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
       mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 loadData();
                 mNewsAdapter.notifyDataSetChanged();
                 mSwipeRefreshLayout.setRefreshing(false);
             }
         });
    }


    private void loadData() {
        mPresenter.loadNews();
    }

    private void prepareSpinner() {
        spinner = findViewById(R.id.news_spinner);
        String [] newsArray = {"TECHNOLOGY", "SPORT", "SCIENCE", "HEALTH", "ENTERTAINMENT"};
        spinnerAdapter = new ArrayAdapter<>(this ,
                android.R.layout.simple_spinner_item, newsArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedNews = parent.getSelectedItem().toString();
                Toast.makeText(BreakingNewsMainActivity.this, ""+selectedNews, Toast.LENGTH_SHORT).show();
                mPresenter.setSelectedNews(selectedNews);
                //loadData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}


