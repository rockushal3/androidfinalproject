package com.example.journey_mate.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.journey_mate.R;
import com.example.journey_mate.adaptor.FriendRequestAdaptor;
import com.example.journey_mate.adaptor.SearchAdaptor;
import com.example.journey_mate.api.FriendRequestApi;
import com.example.journey_mate.api.TripApi;
import com.example.journey_mate.controller.fragment.Friend_Request_Fragment;

public class search extends AppCompatActivity {

    Button back_btn;
    SearchView search_friend;
    RecyclerView serchresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        serchresult= findViewById(R.id.serchresult);
        search_friend = findViewById(R.id.search_friend);
        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        search_friend.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                TripApi tripApi = new TripApi();

                SearchAdaptor adapter = new SearchAdaptor(search.this, tripApi.searchTrip(s));
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(search.this);
                serchresult.setLayoutManager(layoutManager);
                serchresult.setAdapter(adapter);
                return true;
            }
        });

    }
}
