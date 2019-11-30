package com.example.journey_mate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText logemail,logpassword;
    Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logemail = findViewById(R.id.login_email);
        logpassword= findViewById(R.id.login_password);
        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.btn_signup);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent ;
        switch (view.getId()){
            case R.id.btn_login:
                intent = new Intent(Login.this,Home.class);
                startActivity(intent);
                break;
            case R.id.btn_signup:
                intent = new Intent(Login.this,registration.class);
                startActivity(intent);
                break;
        }
    }
}
