package com.suslovalex.retrofit;

import com.suslovalex.model.News;
import io.reactivex.Observable;
import retrofit2.http.GET;



public interface ApiService {

    @GET(ApiFactory.END_URL)
    Observable<News> getArticles();
}
