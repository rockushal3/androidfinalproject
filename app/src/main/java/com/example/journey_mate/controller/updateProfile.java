package com.example.journey_mate.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journey_mate.R;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class updateProfile extends AppCompatActivity {

    TextInputEditText name,email,phone, dob,address,gender;
    Button btn_back, update;
    UserApi userApi = new UserApi();
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.Phone);
        dob = findViewById(R.id.dob);
        address = findViewById(R.id.Address);
        gender = findViewById(R.id.gender);
        btn_back= findViewById(R.id.btn_back);
        update = findViewById(R.id.update);
        title= findViewById(R.id.tool_title);
        //change id to Your id
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        title.setText("Update Profile");
        name.setText(UserApi.loginUserDetail.getName());
        email.setText(UserApi.loginUserDetail.getEmail());
        phone.setText(UserApi.loginUserDetail.getPhone());
        dob.setText(UserApi.loginUserDetail.getDob());
        address.setText(UserApi.loginUserDetail.getAddress());
        gender.setText(UserApi.loginUserDetail.getGender());

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(updateProfile.this, profile.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(name.getText().toString(),address.getText().toString(),phone.getText().toString(),
                        gender.getText().toString(),dob.getText().toString(),email.getText().toString());
                if(userApi.updateProfile(user)){
                    Intent intent = new Intent(updateProfile.this, profile.class);
                    startActivity(intent);
                    Toast.makeText(updateProfile.this, "User Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(updateProfile.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
