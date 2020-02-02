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
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.journey_mate.R;
import com.example.journey_mate.adaptor.PostAdaptor;
import com.example.journey_mate.api.PostApi;
import com.example.journey_mate.api.Retro;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.controller.fragment.addTrip;
import com.example.journey_mate.controller.fragment.cover_pic;
import com.example.journey_mate.controller.fragment.profile_image;
import com.example.journey_mate.model.User;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{
    DrawerLayout drawerLayout;
    Button menu,btn_edit_profile,btn_addtrip,friends_btn,search_btn;
    ImageButton updateprofileimage,updatecover;
    ActionBarDrawerToggle drawerToggle ;
    CircleImageView profileImage;
    ImageView coverimage;
    private RecyclerView postview;
    TextView drawer_name,drawer_address;
    CircleImageView drawer_image;

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
        View header = navigationView.getHeaderView(0);
        drawer_name = header.findViewById(R.id.drawer_name);
        drawer_address = header.findViewById(R.id.drawer_address);
        drawer_image = header.findViewById(R.id.drawer_image);
        drawer_address.setText(UserApi.loginUserDetail.getAddress());
        drawer_name.setText(UserApi.loginUserDetail.getName());
        if(!UserApi.loginUserDetail.getImage().isEmpty()){
            Picasso.with(this).load(Retro.IMG_URL + UserApi.loginUserDetail.getImage()).into(drawer_image);
        }
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
        PostApi postApi = new PostApi();
        PostAdaptor adapter = new PostAdaptor(this,postApi.findpostByuserId(UserApi.loginUserDetail.get_id()));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        postview.setLayoutManager(layoutManager);
        postview.setAdapter(adapter);

        //edit profile
        btn_edit_profile = findViewById(R.id.btn_edit_profile);
        btn_edit_profile.setOnClickListener(this);
        btn_addtrip= findViewById(R.id.btn_addtrip);
        btn_addtrip.setOnClickListener(this);

        //profile detail
        useraddress= findViewById(R.id.useradderss);
        userdob= findViewById(R.id.userdob);
        useremail= findViewById(R.id.useremail);
        userphone= findViewById(R.id.userphone);
        usergender= findViewById(R.id.usergender);
        profilename= findViewById(R.id.profile_name);
        coverimage=findViewById(R.id.coverpic);
        profileImage =(CircleImageView) findViewById(R.id.profile_img);

        useraddress.setText("From "+UserApi.loginUserDetail.getAddress());
        usergender.setText(UserApi.loginUserDetail.getGender());
        userphone.setText("Mobile No. +977 " + UserApi.loginUserDetail.getPhone());
        useremail.setText(UserApi.loginUserDetail.getEmail());
        userdob.setText("Birthday "+UserApi.loginUserDetail.getDob());
        profilename.setText(UserApi.loginUserDetail.getName());

        if(UserApi.loginUserDetail.getCoverimage() != null) {
            Picasso.with(this).load(Retro.IMG_URL + UserApi.loginUserDetail.getCoverimage()).into(coverimage);
        }
        if(UserApi.loginUserDetail.getImage() != null) {
            Picasso.with(this).load(Retro.IMG_URL + UserApi.loginUserDetail.getImage()).into(profileImage);
        }


        //Image update
        updateprofileimage = findViewById(R.id.btn_profile_pic);
        updatecover = findViewById(R.id.update_cover);

        updateprofileimage.setOnClickListener(this);
        updatecover.setOnClickListener(this);

        friends_btn= findViewById(R.id.friends_btn);
        friends_btn.setOnClickListener(this);

        search_btn = findViewById(R.id.btn_search);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this,search.class);
                startActivity(intent);
            }
        });

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
            case R.id.friendsList:
                intent = new Intent(this,Friends.class);
                intent.putExtra("Id", UserApi.loginUserDetail.get_id());
                startActivity(intent);
                break;
            case R.id.setting:
                intent = new Intent(this,Settings.class);
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
            case R.id.btn_profile_pic:
               profile_image profile_image = new profile_image();
                profile_image.show(getSupportFragmentManager(), "12");
                break;
            case R.id.update_cover:
                cover_pic cover_pic = new cover_pic();
                cover_pic.show(getSupportFragmentManager(), "123");
                break;
            case R.id.btn_addtrip:
                intent = new Intent(profile.this,MyTrip.class);
                startActivity(intent);
                break;
            case R.id.friends_btn:
                intent = new Intent(profile.this,Friends.class);
                intent.putExtra("Id", UserApi.loginUserDetail.get_id());
                startActivity(intent);
        }
    }
}
