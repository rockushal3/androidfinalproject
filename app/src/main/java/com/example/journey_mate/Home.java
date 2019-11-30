package com.example.journey_mate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
//note this import
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    Button menu;
    ActionBarDrawerToggle drawerToggle ;
    BottomNavigationView home_navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
        home_navigation = findViewById(R.id.homeNavigation);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String itemName = (String)item.getTitle();

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
