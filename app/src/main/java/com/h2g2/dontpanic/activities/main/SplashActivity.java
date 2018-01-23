package com.h2g2.dontpanic.activities.main;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.activities.base.BaseActivity;
import com.h2g2.dontpanic.databinding.ActivitySplashBinding;
import com.h2g2.dontpanic.models.database.AppDatabase;
import com.h2g2.dontpanic.utils.DatabaseInitializer;

public class SplashActivity extends BaseActivity {

    protected int secondsDelayed = 3;
    protected Handler splashFadeIn = null;
    protected Runnable splashFadeRun = null;
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        //create database if doesn't exist
        DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(this));

        //show animation
        showDelay(secondsDelayed);
    }

    @Override
    public void onResume(){
        super.onResume();
        splashLogoAnimation();
    }

    private void setSplashTouchEvent(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("EVENTO!");
                return true;
            }
        });
    }

    private void splashLogoAnimation() {
        splashFadeIn = new Handler();
        splashFadeRun=new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeInLeft).duration(2000).playOn(binding.dontPanicLogo);
                splashFadeIn.postDelayed(this,3000);
            }
        };
        splashFadeIn.post(splashFadeRun);
    }

    private void showDelay(int seconds) {

        new Handler().postDelayed(new Runnable() {
            public void run() {
                splashLogoAnimation();
                navigateToActivity(MainActivity.class);
                SplashActivity.this.finish();
            }
        }, seconds * 1000);
    }
}
