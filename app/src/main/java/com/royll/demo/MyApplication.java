package com.royll.demo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Roy on 2016/7/19.
 * desc:
 */
public class MyApplication extends Application {

    private AppComponent mAppComponent;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
