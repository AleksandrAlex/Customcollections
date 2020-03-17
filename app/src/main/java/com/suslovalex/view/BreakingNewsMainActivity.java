package com.suslovalex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareRecyclerView();
        initPresenter();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.closeNetwork();
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

    private void loadData() {
        mPresenter.loadNews();
    }

    @Override
    public void showNews(List<Article> articles){
        mNewsAdapter.setArticles(articles);
    }
}
