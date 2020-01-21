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

//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://192.168.100.70:3000/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                UserApi user_interface = retrofit.create(UserApi.class);
//                User user = new User(logemail.getText().toString(),logpassword.getText().toString());
//                Call<User> userlogin = user_interface.userLogin(user);
//                userlogin.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//                        Toast.makeText(Login.this, "kushal", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//
//                    }
//                });


                break;
            case R.id.btn_signup:
                intent = new Intent(Login.this,registration.class);
                startActivity(intent);
                break;
        }
    }
}
