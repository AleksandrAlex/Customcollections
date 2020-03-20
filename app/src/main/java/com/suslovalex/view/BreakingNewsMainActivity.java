package com.suslovalex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.suslovalex.presenter.BreakingNewsPresenter;
import com.suslovalex.adapter.NewsAdapter;
import com.suslovalex.customcollections.R;
import com.suslovalex.model.Article;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class BreakingNewsMainActivity extends AppCompatActivity implements IBreakingNewsView {

    private RecyclerView mNewsRecyclerView;
    private NewsAdapter mNewsAdapter;
    private BreakingNewsPresenter mPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressDialog dialog;
    private Disposable disposable;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareRecyclerView();
        initPresenter();
        loadData();
        prepareSwipeRefreshLayout();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.closeNetwork();
        disposable.dispose();
    }


    @Override
    public void showNews(List<Article> articles){
        mNewsAdapter.setArticles(articles);
    }

    @Override
    public void showDialogLoading() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setTitle("Breaking News!");
        dialog.setMessage("Loading...");
        dialog.show();
    }

    @Override
    public void showDialogError() {
        dialog.setMessage("Error....sorry");
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
       swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
       swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 //showDialogLoading();
                 loadData();
                 mNewsAdapter.notifyDataSetChanged();
                 swipeRefreshLayout.setRefreshing(false);
                 //hideDialog();

             }
         });
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();
    }

    private void loadData() {
        mPresenter.loadNews();
    }
}


