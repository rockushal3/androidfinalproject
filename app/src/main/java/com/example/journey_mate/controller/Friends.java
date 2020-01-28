package com.example.journey_mate.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.journey_mate.R;
import com.example.journey_mate.adaptor.FriendListAdaptor;
import com.example.journey_mate.api.FriendRequestApi;

public class Friends extends AppCompatActivity {

    RecyclerView recyclerView;
    String Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        recyclerView= findViewById(R.id.friendslistrecycle);
        FriendRequestApi friendRequestApi = new FriendRequestApi();
        Id = getIntent().getStringExtra("Id");
        FriendListAdaptor adapter = new FriendListAdaptor(this, friendRequestApi.getFriendList(Id));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
