package com.suslovalex.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    public static final String END_URL = "top-headlines?country=ru&category=technology&apiKey=26e7164f91404030a051e442b76aaf77";
    private static ApiFactory apiFactory;
    private static final String BASE_URL = "http://newsapi.org/v2/";
    private static Retrofit mRetrofit;

    private ApiFactory() {
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static ApiFactory getInstance() {
        if (apiFactory == null) {
            apiFactory = new ApiFactory();
        }
        return apiFactory;
    }

    public ApiService getApiService(){
        return mRetrofit.create(ApiService.class);

    }
}
