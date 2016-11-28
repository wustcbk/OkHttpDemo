package com.example.ede67167;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by EDE67167 on 2016-01-25.
 */
public class MyApplication extends Application {

    private static MyApplication mApp;
    private RefWatcher mRefWatcher;

    public static MyApplication getInstance() {
        return mApp;
    }
    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        mRefWatcher=LeakCanary.install(this);
    }

}