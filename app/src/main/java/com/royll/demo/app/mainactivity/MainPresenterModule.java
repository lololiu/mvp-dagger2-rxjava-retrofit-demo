package com.royll.demo.app.mainactivity;

import com.royll.demo.ActivityScope;
import com.royll.demo.data.model.MovieBean;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liulou on 2016/7/20.
 * desc:
 */
@Module
public class MainPresenterModule {
    private final MainContract.View mView;

    public MainPresenterModule(MainContract.View view){
        mView=view;
    }

    @Provides
    MainContract.View provideMainContractView(){
        return mView;
    }

    @Provides
    MovieBean provideMovieBean(){
        MovieBean movieBean=new MovieBean();
        movieBean.setSubjects(new ArrayList<MovieBean.SubjectsBean>());
        return movieBean;
    }

}
