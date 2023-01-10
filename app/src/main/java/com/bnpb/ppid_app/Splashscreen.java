package com.bnpb.ppid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splashscreen extends AppCompatActivity {
    ImageView GambarGif;
    private int waktu_loading = 5000; // 2000 = 2 detikx
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent pindah = new Intent(Splashscreen.this, Login.class);
                startActivity(pindah);
                finish();
            }
        }, waktu_loading);

        GambarGif = (ImageView) findViewById(R.id.SplashScreenImage);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_animation);
        GambarGif.startAnimation(animation);
    }
}