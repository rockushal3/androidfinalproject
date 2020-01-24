package com.example.journey_mate.controller;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.journey_mate.R;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.model.User;


public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText logemail,logpassword;
    Button login,signup;
    UserApi userApi = new UserApi();
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
        if(userApi.checkLoginStatus()){
            Intent intent = new Intent(Login.this,Home.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent ;
        switch (view.getId()){
            case R.id.btn_login:
                User user = new User(logemail.getText().toString(),logpassword.getText().toString());

                if(userApi.userLogin(user)){
                    intent = new Intent(Login.this,Home.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this,
                            "wrong id or password", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_signup:
                intent = new Intent(Login.this,registration.class);
                startActivity(intent);
                break;
        }
    }
}
