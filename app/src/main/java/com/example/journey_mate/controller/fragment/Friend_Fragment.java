package com.example.journey_mate.controller.fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.journey_mate.R;
import com.example.journey_mate.adaptor.FriendListAdaptor;
import com.example.journey_mate.adaptor.FriendRequestAdaptor;
import com.example.journey_mate.adaptor.PostAdaptor;
import com.example.journey_mate.api.FriendRequestApi;
import com.example.journey_mate.controller.profile;

public class Friend_Fragment extends Fragment {

    RecyclerView friendrecycle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_friend_, container, false);

        friendrecycle= root.findViewById(R.id.recycle_friend);
        FriendRequestApi friendRequestApi = new FriendRequestApi();

        FriendListAdaptor adapter = new FriendListAdaptor(getContext(), friendRequestApi.getFriendList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        friendrecycle.setLayoutManager(layoutManager);
        friendrecycle.setAdapter(adapter);




        return root;
    }

}
