package com.example.journey_mate.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//note this import
import com.example.journey_mate.R;
import com.example.journey_mate.api.PostApi;
import com.example.journey_mate.api.Retro;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.controller.fragment.Friend_Fragment;
import com.example.journey_mate.controller.fragment.Friend_Request_Fragment;
import com.example.journey_mate.controller.fragment.Home_Fragment;
import com.example.journey_mate.controller.fragment.NotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import de.hdodenhof.circleimageview.CircleImageView;


public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    Button menu;
    ActionBarDrawerToggle drawerToggle ;

    BottomNavigationView home_navigation;
    Home_Fragment home_fragment;
    Friend_Fragment friend_fragment;
    Friend_Request_Fragment friend_request_fragment;
    NotificationFragment notificationFragment;
    TextView drawer_name,drawer_address;
    CircleImageView drawer_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Drawer Menu
        drawerLayout = findViewById(R.id.drawerllayout);
        NavigationView navigationView = findViewById(R.id.navView);
        menu = findViewById(R.id.btn_menu);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.setDrawerListener(drawerToggle);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });
        View header = navigationView.getHeaderView(0);
        drawer_name = header.findViewById(R.id.drawer_name);
        drawer_address = header.findViewById(R.id.drawer_address);
        drawer_image = header.findViewById(R.id.drawer_image);
        drawer_address.setText(UserApi.loginUserDetail.getAddress());
        drawer_name.setText(UserApi.loginUserDetail.getName());
        if(!UserApi.loginUserDetail.getImage().isEmpty()){
            Picasso.with(this).load(Retro.IMG_URL + UserApi.loginUserDetail.getImage()).into(drawer_image);
        }

        //hover item selected in navigation drawer
        MenuItem item = navigationView.getMenu().findItem(R.id.home_nav);
        item.setCheckable(true);
        item.setChecked(true);


        // Top Navigation View
        home_navigation = findViewById(R.id.homeNavigation);
        home_fragment = new Home_Fragment();
        setFragment(home_fragment);




        home_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:

                        setFragment(home_fragment);
                        Toast.makeText(Home.this, "home", Toast.LENGTH_SHORT).show();
                    break;

                    case R.id.nav_friend:
                        friend_fragment = new Friend_Fragment();
                        setFragment(friend_fragment);
                        Toast.makeText(Home.this, "friend", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_addfriend:
                        friend_request_fragment = new Friend_Request_Fragment();
                        setFragment(friend_request_fragment);
                        Toast.makeText(Home.this, "Friend Request", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_notification:
                        notificationFragment = new NotificationFragment();
                        setFragment(notificationFragment);
                        Toast.makeText(Home.this, "notification", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
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

    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }


}
