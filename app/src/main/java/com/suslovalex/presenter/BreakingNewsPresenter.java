package com.suslovalex.presenter;

import android.os.CountDownTimer;
import android.util.Log;
import com.suslovalex.model.News;
import com.suslovalex.retrofit.ApiFactory;
import com.suslovalex.retrofit.ApiService;
import com.suslovalex.view.IBreakingNewsView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BreakingNewsPresenter {

    private Disposable mDisposable;
    private IBreakingNewsView mView;

    public BreakingNewsPresenter(IBreakingNewsView view) {
        mView = view;
    }

    public void loadNews(){
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        mDisposable = apiService.getArticles()
                .subscribeOn(Schedulers.io())
                .timeout(5000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {
                        mView.showDialogLoading();
                        // Timer????
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                mView.hideDialog();
                            }
                        }, 2000);

                        mView.showNews(news.getArticles());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Error", "Error connection");
                        mView.showDialogError();
                    }
                });
    }

    public void closeNetwork(){
        mDisposable.dispose();
    }
}
