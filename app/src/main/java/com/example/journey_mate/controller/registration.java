package com.example.journey_mate.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journey_mate.R;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.model.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class registration extends AppCompatActivity implements View.OnClickListener ,RadioGroup.OnCheckedChangeListener {
    private LinearLayout layoutForWelcom;
    private LinearLayout layoutForFullName;
    private LinearLayout layoutForDob;
    private LinearLayout layoutForGender;
    private LinearLayout layoutForPhone, getLayoutForEmail, getLayoutForPassword;
    private TextView toolbarhead;
    private RelativeLayout layoutForFinish;
    TextInputEditText fname, lname, phone, email, password;
    DatePicker dob;
    RadioGroup gender;
    String udob, uname, ugender, uphone, uemail, upassword;
    UserApi userApi = new UserApi();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //change id to Your id
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        Button btnStart = findViewById(R.id.startsignup);
        Button btnTakeName = findViewById(R.id.btnTakeName);
        Button btnTakeDob = findViewById(R.id.btnTakeDob);
        Button btnTakeGender = findViewById(R.id.btnTakeGender);
        Button btnTakePhone = findViewById(R.id.btnTakePhone);
        Button btnTakeEmail = findViewById(R.id.btnTakeEmail);
        Button btnTakePassword = findViewById(R.id.btnTakePassword);
        Button btnSignupBack = findViewById(R.id.btn_back);
        Button btnSignup = findViewById(R.id.btnTakeSignup);
        //for User Name
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        toolbarhead = findViewById(R.id.tool_title);

        //For Data of birth
        dob = findViewById(R.id.datePicker);
        Calendar c = Calendar.getInstance();
        c.set(2000, 11, 31);//Year,Mounth -1,Day
        dob.setMaxDate(c.getTimeInMillis());

        //for gender
        gender = findViewById(R.id.rgGender);

        //for Phone
        phone = findViewById(R.id.etPhone);

        //for Email
        email = findViewById(R.id.etEmail);

        //for Phone
        password = findViewById(R.id.etPassword);


        layoutForFullName = findViewById(R.id.layoutForFullName);
        layoutForDob = findViewById(R.id.layoutForDob);
        layoutForGender = findViewById(R.id.layoutForGender);
        layoutForPhone = findViewById(R.id.layoutForPhone);
        layoutForWelcom = findViewById(R.id.layoutForWelcome);
        getLayoutForEmail = findViewById(R.id.layoutForEmail);
        getLayoutForPassword = findViewById(R.id.layoutForPassword);
        layoutForFinish = findViewById(R.id.layoutForSignUp);


        btnStart.setOnClickListener(this);
        btnTakeName.setOnClickListener(this);
        btnTakeDob.setOnClickListener(this);
        btnTakeGender.setOnClickListener(this);
        btnTakePhone.setOnClickListener(this);
        btnTakeEmail.setOnClickListener(this);
        btnTakePassword.setOnClickListener(this);
        btnSignupBack.setOnClickListener(this);
        gender.setOnCheckedChangeListener(this);
        btnSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.startsignup:

                layoutForFullName.setVisibility(View.VISIBLE);
                layoutForWelcom.setVisibility(View.GONE);
                toolbarhead.setText("Name");
                showKeyboard();
                break;

            case R.id.btnTakeName:
                String ffname = fname.getText().toString();
                String llname = lname.getText().toString();
                if (TextUtils.isEmpty(ffname) || TextUtils.isEmpty(llname)) {

                    fname.setError("Enter First Name");
                    lname.setError("Enter Last Name");
                } else {

                    layoutForDob.setVisibility(View.VISIBLE);
                    layoutForFullName.setVisibility(View.GONE);
                    toolbarhead.setText("Birthday");
                    uname = fname.getText().toString() + " " + lname.getText().toString();
                    hideKeyboardHere();
                }
                break;

            case R.id.btnTakeDob:
                layoutForDob.setVisibility(View.GONE);
                layoutForGender.setVisibility(View.VISIBLE);
                toolbarhead.setText("Gender");
                udob = dob.getDayOfMonth() + "/" + (dob.getMonth() + 1) + "/" + dob.getYear();

                break;
            case R.id.btnTakeGender:
                if (TextUtils.isEmpty(ugender)) {
                    Toast.makeText(this, "Select Your Gender", Toast.LENGTH_SHORT).show();
                } else {
                    layoutForGender.setVisibility(View.GONE);
                    layoutForPhone.setVisibility(View.VISIBLE);
                    toolbarhead.setText("Mobile Number");
                    phone.requestFocus();
                    showKeyboard();
                }
                break;
            case R.id.btnTakePhone:
                String ephone = phone.getText().toString();
                if (TextUtils.isEmpty(ephone)) {
                    phone.setError("Enter Your Phone");
                } else {
                    layoutForPhone.setVisibility(View.GONE);
                    getLayoutForEmail.setVisibility(View.VISIBLE);
                    toolbarhead.setText("Email");
                    uphone = phone.getText().toString();
                    email.requestFocus();
                }
                break;
            case R.id.btnTakeEmail:
                String eemail = email.getText().toString();
                if (TextUtils.isEmpty(eemail)) {
                    email.setError("Enter Your Email");
                } else if (userApi.checkemail(eemail)) {
                    email.setError("Email Already Exist");

                } else {
                    getLayoutForEmail.setVisibility(View.GONE);
                    getLayoutForPassword.setVisibility(View.VISIBLE);
                    toolbarhead.setText("Password");
                    uemail = email.getText().toString();

                }
                ;
                break;
            case R.id.btnTakePassword:
                String epassword = password.getText().toString();
                if (TextUtils.isEmpty(epassword)) {
                    password.setError("Enter Your Password");
                } else {
                    getLayoutForPassword.setVisibility(View.GONE);
                    layoutForFinish.setVisibility(View.VISIBLE);
                    toolbarhead.setText("Terms & Privacy");
                    upassword = password.getText().toString();
                    hideKeyboardHere();

                }
                break;
            case R.id.btnTakeSignup:
                User usr = new User(uname, "", uphone, ugender, udob, uemail, upassword, "", "", "");
                if (userApi.userRegistration(usr)) {
                    User user = new User(uemail,upassword);

                    if(userApi.userLogin(user,registration.this)){
                        intent = new Intent(registration.this,Home.class);
                        startActivity(intent);
                        finish();
                    }
                    Toast.makeText(registration.this,
                            "User Register", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(registration.this,
                            "Something went wrong", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_back:
                intent = new Intent(registration.this, Login.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.rbMale) {
            ugender = "Male";
            Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();
        }
        if (i == R.id.rbFemale) {
            ugender = "Female";
            Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show();
        }
        if (i == R.id.rbOther) {
            ugender = "Other";
            Toast.makeText(this, "Others", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        String headingTitle = toolbarhead.getText().toString();
        switch (headingTitle) {
            case "Create an Account":
            case "Name":
                Intent intent = new Intent(registration.this, Login.class);
                startActivity(intent);
                break;
            case "Birthday":
                layoutForDob.setVisibility(View.GONE);
                layoutForFullName.setVisibility(View.VISIBLE);
                toolbarhead.setText("Name");
                break;
            case "Gender":
                layoutForGender.setVisibility(View.GONE);
                layoutForDob.setVisibility(View.VISIBLE);
                toolbarhead.setText("Birthday");
                break;

            case "Mobile Number":
                layoutForPhone.setVisibility(View.GONE);
                layoutForGender.setVisibility(View.VISIBLE);
                toolbarhead.setText("Gender");
                break;
            case "Email":
                getLayoutForEmail.setVisibility(View.GONE);
                layoutForPhone.setVisibility(View.VISIBLE);
                toolbarhead.setText("Mobile Number");
                break;
            case "Password":
                getLayoutForPassword.setVisibility(View.GONE);
                getLayoutForEmail.setVisibility(View.VISIBLE);
                toolbarhead.setText("Email");
                break;
            case "Terms & Privacy":
                layoutForFinish.setVisibility(View.GONE);
                getLayoutForPassword.setVisibility(View.VISIBLE);
                toolbarhead.setText("Password");
                break;
        }
    }

    private void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    private void hideKeyboardHere(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(dob.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(gender.getWindowToken(), 0);
        }
    }
}
