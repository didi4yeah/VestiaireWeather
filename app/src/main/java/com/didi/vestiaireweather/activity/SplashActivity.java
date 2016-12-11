package com.didi.vestiaireweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by didi on 11/12/2016.
 * Splash activity just to show our logo
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Enjoy a little bit our splash
        //Ideally we should init/load some contents for later
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
        finish();
    }
}