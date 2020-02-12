package com.example.journey_mate.controller.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.journey_mate.R;
import com.example.journey_mate.adaptor.FriendRequestAdaptor;
import com.example.journey_mate.adaptor.PostAdaptor;
import com.example.journey_mate.api.FriendRequestApi;
import com.example.journey_mate.api.PostApi;


public class Friend_Request_Fragment extends Fragment {

    RecyclerView requestrecycle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_friend__request_, container, false);
        requestrecycle = root.findViewById(R.id.requested_recycle);
        FriendRequestApi friendRequestApi = new FriendRequestApi();

        FriendRequestAdaptor adapter = new FriendRequestAdaptor(getContext(), friendRequestApi.findrequestByuserId(),Friend_Request_Fragment.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        requestrecycle.setLayoutManager(layoutManager);
        requestrecycle.setAdapter(adapter);

        return root;
    }

}
