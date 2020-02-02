package com.example.journey_mate.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journey_mate.R;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class Settings extends AppCompatActivity {

    Button back,save;
    TextInputEditText oldpassword,newpassword,confirmpassword;
    TextView title;
    UserApi userApi =new UserApi();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        back= findViewById(R.id.btn_back);
        title=findViewById(R.id.tool_title);
        save =findViewById(R.id.changepassword);
        oldpassword =findViewById(R.id.oldpassword);
        newpassword =findViewById(R.id.newpassword);
        confirmpassword =findViewById(R.id.confirmpassword);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass,newpass,confirmpass;
                oldpass=oldpassword.getText().toString();
                newpass=newpassword.getText().toString();
                confirmpass=confirmpassword.getText().toString();
                if(oldpass.isEmpty() && newpass.isEmpty()) {
                    newpassword.setError("enter a password");
                }
                else{
                    if (oldpass.equals(UserApi.loginUserDetail.getPassword())) {
                        if (confirmpass.equals(newpass)) {
                            if (userApi.updatepassword(new User(newpass))) {
                                Toast.makeText(Settings.this, "User Password Change", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        } else {
                            newpassword.setError("password mismatch");
                            confirmpassword.setError("password mismatch");
                        }
                    } else {
                        oldpassword.setError("password incorrect");
                    }
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
