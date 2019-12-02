package com.example.journey_mate;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    Button menu;
    ActionBarDrawerToggle drawerToggle ;

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
