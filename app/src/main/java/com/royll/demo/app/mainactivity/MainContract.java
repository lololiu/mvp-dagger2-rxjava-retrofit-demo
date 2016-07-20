package com.royll.demo.app.mainactivity;

import com.royll.demo.base.BasePresenter;
import com.royll.demo.base.BaseView;
import com.royll.demo.data.model.MovieBean;

import java.util.List;

/**
 * Created by liulou on 2016/7/20.
 * desc:
 */
public interface MainContract {
    interface View extends BaseView{
        void initViews();
        void initMovieList(MovieBean movieBean);
        void showToast(String text);
    }
    interface Presenter extends BasePresenter{
        void toast(String text);
        void getMovies(int page);
    }
}
