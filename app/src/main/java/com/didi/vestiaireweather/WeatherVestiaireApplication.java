package com.didi.vestiaireweather;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by didi on 11/12/2016.
 */

public class WeatherVestiaireApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
