package com.example.journey_mate.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.an.biometric.BiometricCallback;
import com.an.biometric.BiometricManager;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.journey_mate.R;
import com.example.journey_mate.api.UserApi;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity implements BiometricCallback {

    private static int Splash_time_out = 3000;
    private ImageView imageView6, imageView4;
    UserApi userApi = new UserApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView6 = findViewById(R.id.imageView6);
        imageView4 = findViewById(R.id.imageView4);
        imageView4.setAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
        imageView6.setAnimation(AnimationUtils.loadAnimation(this, R.anim.lefttoright));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
                String token = sharedPreferences.getString("Token", "");
                if (userApi.checkLoginStatus(token)) {
                    new BiometricManager.BiometricBuilder(SplashScreen.this)
                            .setTitle("Fingerprint Authentication")
                            .setDescription("Use your fingerprint to access")
                            .setNegativeButtonText("Cancel")
                            .build()
                            .authenticate(SplashScreen.this);


                } else {
                    Intent intent = new Intent(SplashScreen.this, Login.class);
                    Animatoo.animateZoom(SplashScreen.this);
                    startActivity(intent);
                    finish();
                }
            }
        }, Splash_time_out);


    }

    @Override
    public void onSdkVersionNotSupported() {
        Intent intent = new Intent(SplashScreen.this, Home.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBiometricAuthenticationNotSupported() {
        Intent intent = new Intent(SplashScreen.this, Home.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBiometricAuthenticationNotAvailable() {
        Intent intent = new Intent(SplashScreen.this, Home.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBiometricAuthenticationPermissionNotGranted() {

    }

    @Override
    public void onBiometricAuthenticationInternalError(String error) {

    }

    @Override
    public void onAuthenticationFailed() {

    }

    @Override
    public void onAuthenticationCancelled() {

    }

    @Override
    public void onAuthenticationSuccessful() {
        Intent intent = new Intent(SplashScreen.this, Home.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

    }
}
