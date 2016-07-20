package com.royll.demo;

import android.app.Application;

import com.royll.demo.data.api.ApiService;
import com.royll.demo.data.api.ApiServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Roy on 2016/7/19.
 * desc:
 */
@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {
    Application getApplication();
    ApiService getApiService();
}
