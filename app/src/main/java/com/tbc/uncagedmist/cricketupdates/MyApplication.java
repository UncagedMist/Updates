package com.tbc.uncagedmist.cricketupdates;

import android.annotation.SuppressLint;
import android.app.Application;

import com.tbc.uncagedmist.cricketupdates.Tools.AppOpenManager;

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @SuppressLint("StaticFieldLeak")
    private static AppOpenManager appOpenManager;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        appOpenManager = new AppOpenManager(this);
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

}
