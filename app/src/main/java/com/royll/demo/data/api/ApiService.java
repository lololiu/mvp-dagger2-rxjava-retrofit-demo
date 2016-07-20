package com.royll.demo.data.api;

import com.royll.demo.data.model.MovieBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liulou on 2016/7/20.
 * desc:
 */
public interface ApiService {

    @GET("v2/movie/top250")
    Observable<MovieBean> getTop250Movies(@Query("start") int start);
}
