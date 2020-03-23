package com.suslovalex.retrofit;

import com.suslovalex.model.News;
import io.reactivex.Observable;
import retrofit2.http.GET;



public interface ApiService {

    @GET(ApiFactory.END_URL_TECHNOLOGY)
    Observable<News> getTechnologyArticles();

    @GET(ApiFactory.END_URL_HEALTH)
    Observable<News> getHealthArticles();

    @GET(ApiFactory.END_URL_SPORT)
    Observable<News> getSportArticles();

    @GET(ApiFactory.END_URL_SCIENCE)
    Observable<News> getScienceArticles();

    @GET(ApiFactory.END_URL_ENTERTAINMENT)
    Observable<News> getEntertainmentArticles();
}
