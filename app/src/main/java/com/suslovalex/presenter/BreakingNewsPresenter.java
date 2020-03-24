package com.suslovalex.presenter;

import android.util.Log;
import com.suslovalex.model.News;
import com.suslovalex.retrofit.ApiFactory;
import com.suslovalex.retrofit.ApiService;
import com.suslovalex.view.IBreakingNewsView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BreakingNewsPresenter {

    private Disposable mDisposable;
    private IBreakingNewsView mView;
    private String mSelectedNews = "TECHNOLOGY";

    public BreakingNewsPresenter(IBreakingNewsView view) {
        mView = view;
    }

    public void loadNews() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        Observable<News> observable = null;
        switch (mSelectedNews) {
            case ("TECHNOLOGY"):
                observable = apiService.getTechnologyArticles();
                break;
            case ("HEALTH"):
                observable = apiService.getHealthArticles();
                break;
            case ("SPORT"):
                observable = apiService.getSportArticles();
                break;
            case ("ENTERTAINMENT"):
                observable = apiService.getEntertainmentArticles();
                break;
            case ("SCIENCE"):
                observable = apiService.getScienceArticles();
                break;
            default:
                break;
        }
        if (observable != null) {
            mDisposable = observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<News>() {
                        @Override
                        public void accept(News news) throws Exception {
                            mView.showDialogLoading();
                            // Timer????
                            mView.hideDialog();
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
    }

    public void closeNetwork(){
        mDisposable.dispose();
    }

    public void setSelectedNews(String selectedNews) {
        mSelectedNews = selectedNews;
        //loadNews();
    }
}
