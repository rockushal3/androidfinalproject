package com.example.journey_mate.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.journey_mate.R;
import com.example.journey_mate.api.FriendRequestApi;
import com.example.journey_mate.api.Retro;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.model.FriendRelationResponce;
import com.example.journey_mate.model.User;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class UserProfile extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Button menu;
    CircleImageView profileImage;
    ImageView coverimage;
    ActionBarDrawerToggle drawerToggle ;
    GridLayout FriendsofUser,FriendRequestofUser;
    LinearLayout Nofriends,sendFriendRequest;
    UserApi userApi = new UserApi();
    FriendRequestApi friendRequestApi = new FriendRequestApi();
    String Id;
    User userdetail;
    FriendRelationResponce friendRelationResponce;
    TextView profilename,userdob,useraddress,userphone,usergender,useremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //change id to Your id
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Drawer Menu
        drawerLayout = findViewById(R.id.drawerllayout);
        NavigationView navigationView = findViewById(R.id.navView);
        menu = findViewById(R.id.btn_menu);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.setDrawerListener(drawerToggle);
        menu.setOnClickListener(this);

        //For User Relation Status
        sendFriendRequest = findViewById(R.id.sendFriendRequest);
        Nofriends = findViewById(R.id.Nofriends);
        FriendsofUser = findViewById(R.id.FriendsofUser);
        FriendRequestofUser = findViewById(R.id.FriendRequestofUser);
        Id = getIntent().getStringExtra("Id");
        userdetail=userApi.getuserbyid(Id);
        friendRelationResponce = friendRequestApi.checkFriendStatus(Id);
        System.out.println(friendRelationResponce.getStatus());
        if(friendRelationResponce.getStatus().equals("Friends")){
            FriendsofUser.setVisibility(View.VISIBLE);
        }
        if(friendRelationResponce.getStatus().equals("Requested") && friendRelationResponce.getUser_id_1().get_id().equals(UserApi.loginUserDetail.get_id())){
            sendFriendRequest.setVisibility(View.VISIBLE);
        }
        if(friendRelationResponce.getStatus().equals("Requested") && friendRelationResponce.getUser_id_2().get_id().equals(UserApi.loginUserDetail.get_id())){
            FriendRequestofUser.setVisibility(View.VISIBLE);
        }
        if(friendRelationResponce== null){
            Nofriends.setVisibility(View.VISIBLE);
        }

        //profile detail
        useraddress= findViewById(R.id.useradderss);
        userdob= findViewById(R.id.userdob);
        useremail= findViewById(R.id.useremail);
        userphone= findViewById(R.id.userphone);
        usergender= findViewById(R.id.usergender);
        profilename= findViewById(R.id.profile_name);
        coverimage=findViewById(R.id.coverpic);
        profileImage =findViewById(R.id.profile_img);

        useraddress.setText("From "+userdetail.getAddress());
        usergender.setText(userdetail.getGender());
        userphone.setText("Mobile No. +977 " + userdetail.getPhone());
        useremail.setText(UserApi.loginUserDetail.getEmail());
        userdob.setText("Birthday "+userdetail.getDob());
        profilename.setText(userdetail.getName());

        if(UserApi.loginUserDetail.getCoverimage() != null) {
            Picasso.with(this).load(Retro.IMG_URL + userdetail.getCoverimage()).into(coverimage);
        }
        if(UserApi.loginUserDetail.getImage() != null) {
            Picasso.with(this).load(Retro.IMG_URL + userdetail.getImage()).into(profileImage);
        }






    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_menu:
                openDrawer();
             break;
        }

    }

    private void CloseDrawer() {
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.END);
    }
}
