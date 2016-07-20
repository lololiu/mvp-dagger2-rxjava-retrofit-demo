package com.royll.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.royll.demo.AppComponent;
import com.royll.demo.MyApplication;

/**
 * Created by liulou on 2016/7/20.
 * desc:
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityCompoent(MyApplication.get(this).getAppComponent());
    }


    protected abstract void setupActivityCompoent(AppComponent appComponent);
}
