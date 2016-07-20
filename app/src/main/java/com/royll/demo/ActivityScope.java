package com.royll.demo;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by liulou on 2016/7/20.
 * desc:
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {

}
