package com.blueangels.psfm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.blueangels.psfm.R;
import com.blueangels.psfm.ui.activities.home.HomeActivity;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }, SPLASH_TIME_OUT);
    }
}
