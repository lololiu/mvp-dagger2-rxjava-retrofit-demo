package com.royll.demo.app.mainactivity;

import com.royll.demo.ActivityScope;
import com.royll.demo.AppComponent;

import dagger.Component;

/**
 * Created by liulou on 2016/7/20.
 * desc:
 */
@Component(modules = MainPresenterModule.class,dependencies = AppComponent.class)
@ActivityScope
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
