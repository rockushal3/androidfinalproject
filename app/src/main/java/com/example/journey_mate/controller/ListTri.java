package com.example.journey_mate.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.journey_mate.R;
import com.example.journey_mate.adaptor.TripAdaptor;
import com.example.journey_mate.api.TripApi;
import com.example.journey_mate.api.UserApi;

public class ListTri extends AppCompatActivity {

    String id;
    TripApi tripApi = new TripApi();
    RecyclerView tripRecycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tri);

        //Get Trip
        tripRecycle = findViewById(R.id.Trip_recycle);
        id = getIntent().getStringExtra("Id");
        TripAdaptor adapter = new TripAdaptor(this, tripApi.getTripByid(id));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        tripRecycle.setLayoutManager(layoutManager);
        tripRecycle.setAdapter(adapter);

    }
}
