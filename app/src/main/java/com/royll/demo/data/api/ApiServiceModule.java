package com.royll.demo.data.api;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liulou on 2016/7/20.
 * desc:
 */
@Module
public class ApiServiceModule {

    public static final long HTTP_CONNECT_TIMEOUT = 15 * 1000;
    public static final long HTTP_READ_TIMEOUT = 15 * 1000;

    public static final String BASE_URL = "https://api.douban.com/";

    @Provides
    @Singleton
    OkHttpClient providePkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }


    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
