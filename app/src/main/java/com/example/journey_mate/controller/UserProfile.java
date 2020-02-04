package com.example.journey_mate.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journey_mate.R;
import com.example.journey_mate.adaptor.PostAdaptor;
import com.example.journey_mate.api.FriendRequestApi;
import com.example.journey_mate.api.PostApi;
import com.example.journey_mate.api.Retro;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.model.FriendRelation;
import com.example.journey_mate.model.FriendRelationResponce;
import com.example.journey_mate.model.User;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class UserProfile extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Button menu, sendrequest, confirm_button, Friendsbtn, search_btn, delete_request,cancel_request,btn_viewtrip;
    CircleImageView profileImage;
    ImageView coverimage;
    ActionBarDrawerToggle drawerToggle;
    GridLayout FriendsofUser, FriendRequestofUser;
    LinearLayout Nofriends, sendFriendRequest;
    UserApi userApi = new UserApi();
    FriendRequestApi friendRequestApi = new FriendRequestApi();
    String Id;
    User userdetail;
    FriendRelationResponce friendRelationResponce;
    TextView profilename, userdob, useraddress, userphone, usergender, useremail;
    RecyclerView postview;
    TextView drawer_name, drawer_address;
    CircleImageView drawer_image;

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
        View header = navigationView.getHeaderView(0);
        drawer_name = header.findViewById(R.id.drawer_name);
        drawer_address = header.findViewById(R.id.drawer_address);
        drawer_image = header.findViewById(R.id.drawer_image);
        drawer_address.setText(UserApi.loginUserDetail.getAddress());
        drawer_name.setText(UserApi.loginUserDetail.getName());
        if (!UserApi.loginUserDetail.getImage().isEmpty()) {
            Picasso.with(this).load(Retro.IMG_URL + UserApi.loginUserDetail.getImage()).into(drawer_image);
        }
        menu.setOnClickListener(this);

        //For User Relation Status
        btn_viewtrip=findViewById(R.id.btn_viewtrip);
        cancel_request= findViewById(R.id.cancel_request);
        delete_request = findViewById(R.id.delete_request);
        Friendsbtn = findViewById(R.id.Friendsbtn);
        confirm_button = findViewById(R.id.confirm_button);
        sendrequest = findViewById(R.id.sendrequest);
        sendFriendRequest = findViewById(R.id.sendFriendRequest);
        Nofriends = findViewById(R.id.Nofriends);
        FriendsofUser = findViewById(R.id.FriendsofUser);
        FriendRequestofUser = findViewById(R.id.FriendRequestofUser);
        Id = getIntent().getStringExtra("Id");
        userdetail = userApi.getuserbyid(Id);
        friendRelationResponce = friendRequestApi.checkFriendStatus(Id);
        if (friendRelationResponce == null) {
            Nofriends.setVisibility(View.VISIBLE);
            sendrequest.setOnClickListener(this);
        } else {
            System.out.println(friendRelationResponce.getStatus());
            if (friendRelationResponce.getStatus().equals("Friends")) {
                FriendsofUser.setVisibility(View.VISIBLE);

                //Post Adaptor data code
                postview = findViewById(R.id.post_list_profile);
                PostApi postApi = new PostApi();
                PostAdaptor adapter = new PostAdaptor(this, postApi.findpostByuserId(Id));
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                postview.setLayoutManager(layoutManager);
                postview.setAdapter(adapter);
                Friendsbtn.setOnClickListener(this);
                btn_viewtrip.setOnClickListener(this);
            } else if (friendRelationResponce.getStatus().equals("Requested") && friendRelationResponce.getUser_id_1().get_id().equals(UserApi.loginUserDetail.get_id())) {
                sendFriendRequest.setVisibility(View.VISIBLE);
                cancel_request.setOnClickListener(this);
            } else if (friendRelationResponce.getStatus().equals("Requested") && friendRelationResponce.getUser_id_2().get_id().equals(UserApi.loginUserDetail.get_id())) {
                FriendRequestofUser.setVisibility(View.VISIBLE);
                confirm_button.setOnClickListener(this);
                delete_request.setOnClickListener(this);
            }
        }

        //profile detail
        useraddress = findViewById(R.id.useradderss);
        userdob = findViewById(R.id.userdob);
        useremail = findViewById(R.id.useremail);
        userphone = findViewById(R.id.userphone);
        usergender = findViewById(R.id.usergender);
        profilename = findViewById(R.id.profile_name);
        coverimage = findViewById(R.id.coverpic);
        profileImage = findViewById(R.id.profile_img);

        useraddress.setText("From " + userdetail.getAddress());
        usergender.setText(userdetail.getGender());
        userphone.setText("Mobile No. +977 " + userdetail.getPhone());
        useremail.setText(userdetail.getEmail());
        userdob.setText("Birthday " + userdetail.getDob());
        profilename.setText(userdetail.getName());

        if (UserApi.loginUserDetail.getCoverimage() != null) {
            Picasso.with(this).load(Retro.IMG_URL + userdetail.getCoverimage()).into(coverimage);
        }
        if (UserApi.loginUserDetail.getImage() != null) {
            Picasso.with(this).load(Retro.IMG_URL + userdetail.getImage()).into(profileImage);
        }

        search_btn = findViewById(R.id.btn_search);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this, search.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.profile_nav:
                intent = new Intent(this, profile.class);
                startActivity(intent);
                break;
            case R.id.home_nav:
                intent = new Intent(this, Home.class);
                startActivity(intent);
                break;
            case R.id.logout_nav:
                SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Token","");
                editor.commit();
                Retro.token ="";
                intent = new Intent(this,Login.class);
                startActivity(intent);
                finish();
                break;
            case R.id.trip:
                intent = new Intent(this, MyTrip.class);
                startActivity(intent);
                break;
            case R.id.about:
                intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            case R.id.friendsList:
                intent = new Intent(this, Friends.class);
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


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_menu:
                openDrawer();
                break;
            case R.id.sendrequest:
                FriendRelation friendRelation = new FriendRelation(UserApi.loginUserDetail.get_id(), Id, "");
                friendRequestApi.sendRequest(friendRelation);
                intent = new Intent(UserProfile.this, UserProfile.class);
                intent.putExtra("Id", Id);
                startActivity(intent);
                finish();
                break;
            case R.id.confirm_button:
                if (friendRequestApi.AcceptFriend(friendRelationResponce.get_id())) {
                    Toast.makeText(UserProfile.this, friendRelationResponce.getUser_id_1().getName() + " and " + UserApi.loginUserDetail.getName() + " are Friends", Toast.LENGTH_SHORT).show();
                    intent = new Intent(UserProfile.this, UserProfile.class);
                    intent.putExtra("Id", Id);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.Friendsbtn:
                intent = new Intent(UserProfile.this, Friends.class);
                intent.putExtra("Id", Id);
                startActivity(intent);
                break;
            case R.id.delete_request:
                if (friendRequestApi.deletePost(friendRelationResponce.get_id())) {
                    Toast.makeText(UserProfile.this, friendRelationResponce.getUser_id_1().getName() + " Friend Request Has been deleted", Toast.LENGTH_SHORT).show();
                    intent = new Intent(UserProfile.this, UserProfile.class);
                    intent.putExtra("Id", Id);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.cancel_request:
                if (friendRequestApi.deletePost(friendRelationResponce.get_id())) {
                    Toast.makeText(UserProfile.this, friendRelationResponce.getUser_id_2().getName() + " send Request Has been deleted", Toast.LENGTH_SHORT).show();
                    intent = new Intent(UserProfile.this, UserProfile.class);
                    intent.putExtra("Id", Id);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.btn_viewtrip:
                intent = new Intent(UserProfile.this, ListTri.class);
                intent.putExtra("Id", Id);
                startActivity(intent);
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
