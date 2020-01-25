package com.example.journey_mate.controller;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.journey_mate.R;
import com.example.journey_mate.adaptor.PostAdaptor;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.model.User;
import com.google.android.material.navigation.NavigationView;

public class profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{
    DrawerLayout drawerLayout;
    Button menu,btn_edit_profile;
    ActionBarDrawerToggle drawerToggle ;
    private RecyclerView postview;

    TextView profilename,userdob,useraddress,userphone,usergender,useremail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_profile);

        //change id to Your id
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Drawer Menu
        drawerLayout = findViewById(R.id.drawerllayout);
        NavigationView navigationView = findViewById(R.id.navView);
        menu = findViewById(R.id.btn_menu);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.setDrawerListener(drawerToggle);
        // Top Navigation View
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });

        //hover item selected in navigation drawer
        MenuItem item = navigationView.getMenu().findItem(R.id.profile_nav);
        item.setCheckable(true);
        item.setChecked(true);

        //Post Adaptor data code
        postview = findViewById(R.id.post_list_profile);
        PostAdaptor adapter = new PostAdaptor(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        postview.setLayoutManager(layoutManager);
        postview.setAdapter(adapter);

        //edit profile
        btn_edit_profile = findViewById(R.id.btn_edit_profile);
        btn_edit_profile.setOnClickListener(this);

        //profile detail
        useraddress= findViewById(R.id.useradderss);
        userdob= findViewById(R.id.userdob);
        useremail= findViewById(R.id.useremail);
        userphone= findViewById(R.id.userphone);
        usergender= findViewById(R.id.usergender);
        profilename= findViewById(R.id.profile_name);


        useraddress.setText("From "+UserApi.loginUserDetail.getAddress());
        usergender.setText(UserApi.loginUserDetail.getGender());
        userphone.setText("Mobile No. +977 " + UserApi.loginUserDetail.getPhone());
        useremail.setText(UserApi.loginUserDetail.getEmail());
        userdob.setText("Birthday "+UserApi.loginUserDetail.getDob());
        profilename.setText(UserApi.loginUserDetail.getName());


    }




    // drawer Navigation
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.profile_nav:
                intent = new Intent(this,profile.class);
                startActivity(intent);
                break;
            case R.id.home_nav:
                intent = new Intent(this,Home.class);
                startActivity(intent);
                break;
            case R.id.logout_nav:
                intent = new Intent(this,Login.class);
                startActivity(intent);
                break;
            case R.id.trip:
                intent = new Intent(this,MyTrip.class);
                startActivity(intent);
                break;

            case R.id.about:
                intent = new Intent(this,About.class);
                startActivity(intent);
                break;
        }
        CloseDrawer();
        return true;
    }

    private void CloseDrawer() {
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.END);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_edit_profile:
                intent = new Intent(profile.this,updateProfile.class);
                startActivity(intent);
                break;
        }
    }
}
