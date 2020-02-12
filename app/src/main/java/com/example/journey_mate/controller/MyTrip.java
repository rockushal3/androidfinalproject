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

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journey_mate.R;
import com.example.journey_mate.adaptor.TripAdaptor;
import com.example.journey_mate.api.Retro;
import com.example.journey_mate.api.TripApi;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.controller.fragment.addTrip;
import com.example.journey_mate.model.Trip;
import com.example.journey_mate.router.UserRoute;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class MyTrip extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    Button menu,search_btn;
    ActionBarDrawerToggle drawerToggle ;
    FloatingActionButton addtrip;
    TextView drawer_name,drawer_address;
    CircleImageView drawer_image;
    RecyclerView tripRecycle;

    TripApi tripApi = new TripApi();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trip);

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

        //Get Trip
        tripRecycle = findViewById(R.id.Trip_recycle);
        TripAdaptor adapter = new TripAdaptor(this, tripApi.getTripByid(UserApi.loginUserDetail.get_id()));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        tripRecycle.setLayoutManager(layoutManager);
        tripRecycle.setAdapter(adapter);


        //hover item selected in navigation drawer
        MenuItem item = navigationView.getMenu().findItem(R.id.trip);
        item.setCheckable(true);
        item.setChecked(true);

        search_btn = findViewById(R.id.btn_search);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyTrip.this,search.class);
                startActivity(intent);
            }
        });


        //floating Button
        addtrip = findViewById(R.id.add_trip);
        addtrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTrip dialog_pop_up = new addTrip();
                dialog_pop_up.show(getSupportFragmentManager(), "123");
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
