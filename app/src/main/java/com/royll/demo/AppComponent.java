package com.royll.demo;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Roy on 2016/7/19.
 * desc:
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Application getApplication();
}
