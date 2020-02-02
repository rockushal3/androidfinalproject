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
import android.widget.TextView;

import com.example.journey_mate.R;
import com.example.journey_mate.api.Retro;
import com.example.journey_mate.api.UserApi;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class About extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Button menu,search_btn;
    ActionBarDrawerToggle drawerToggle ;
    TextView drawer_name,drawer_address;
    CircleImageView drawer_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

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
        MenuItem item = navigationView.getMenu().findItem(R.id.about);
        item.setCheckable(true);
        item.setChecked(true);

        search_btn = findViewById(R.id.btn_search);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(About.this,search.class);
                startActivity(intent);
            }
        });
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
}
