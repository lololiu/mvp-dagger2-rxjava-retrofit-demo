package com.royll.demo;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roy on 2016/7/19.
 * desc:
 */
@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application){
        this.mApplication=application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return mApplication;
    }
}
