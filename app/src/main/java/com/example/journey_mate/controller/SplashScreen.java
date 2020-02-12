package com.example.journey_mate.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.journey_mate.R;
import com.example.journey_mate.api.UserApi;

public class SplashScreen extends AppCompatActivity {

    private static int Splash_time_out =3000;
    private ImageView imageView6,imageView4;
    UserApi userApi = new UserApi();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView6=findViewById(R.id.imageView6);
        imageView4=findViewById(R.id.imageView4);
        imageView4.setAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate));
        imageView6.setAnimation(AnimationUtils.loadAnimation(this, R.anim.lefttoright));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String token =  sharedPreferences.getString("Token", "");
                System.out.println(token);
        if(userApi.checkLoginStatus(token)){
            Intent intent = new Intent(SplashScreen.this,Home.class);
            startActivity(intent);
            finish();
        }
        else {
            Intent intent = new Intent(SplashScreen.this, Login.class);
            Animatoo.animateZoom(SplashScreen.this);
            startActivity(intent);
            finish();
        }
            }
        },Splash_time_out);


    }
}
