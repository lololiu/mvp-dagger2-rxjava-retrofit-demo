package com.royll.demo.app.mainactivity;

import android.util.Log;
import android.widget.Toast;

import com.royll.demo.data.api.ApiService;
import com.royll.demo.data.model.MovieBean;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liulou on 2016/7/20.
 * desc:
 */
public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";
    private final MainContract.View mMainView;

    private MovieBean mMovieBean;
    @Inject
    ApiService mApiService;

    @Inject
    public MainPresenter(MainContract.View view) {
        mMainView = view;
    }

    @Override
    public void toast(String text) {
        Toast.makeText((MainActivity) mMainView, text, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "toast: text:" + text);
    }

    @Override
    public void getMovies(int page, int pagecount) {
        if (mApiService != null) {
            Log.d(TAG, "getMovies: mapiservice not null");
        }
        mApiService.getTop250Movies(page * pagecount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                        if (mMovieBean != null) {
                            mMainView.updateMovieList(mMovieBean);
                        } else {
                            mMainView.showToast("加载电影失败！");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "onError: e" + e.toString());
                    }

                    @Override
                    public void onNext(MovieBean movieBean) {
                        Log.d(TAG, "onNext: movie:" + movieBean.getTitle() + " count:" + movieBean.getCount() + " start:" + movieBean.getStart());
                        mMovieBean = movieBean;
                    }
                });
    }
}
