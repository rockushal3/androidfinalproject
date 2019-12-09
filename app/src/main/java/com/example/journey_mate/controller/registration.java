package com.example.journey_mate.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.journey_mate.R;

public class registration extends AppCompatActivity implements View.OnClickListener {
    EditText signname,signpassword,signemail;
    Button register,log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        signname = findViewById(R.id.sign_name);
        signemail = findViewById(R.id.sign_email);
        signpassword = findViewById(R.id.sign_psw);
        register = findViewById(R.id.btn_register);
        log =findViewById(R.id.btn_log);

        register.setOnClickListener(this);
        log.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:

                break;
            case R.id.btn_log:
                Intent intent = new Intent(registration.this,Login.class);
                startActivity(intent);
                break;
        }
    }
}
