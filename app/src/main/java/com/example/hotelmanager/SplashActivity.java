package com.example.hotelmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageView=(ImageView)findViewById(R.id.pic);
        Runnable imageRunnable=new Runnable() {
            @Override
            public void run() {
                    imageView.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(
                        SplashActivity.this, R.anim.splash_image_trans);
                    imageView.startAnimation(animation);
                }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    SplashActivity.this.runOnUiThread(imageRunnable);
                    try {
                    Thread.sleep(2000);
                    }catch (InterruptedException e){
                    e.printStackTrace();
                    }
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(intent);
                    finish();
                }
        }).start();
    }
}