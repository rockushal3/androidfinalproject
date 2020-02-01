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
import com.example.journey_mate.adaptor.NotificationAdaptor;
import com.example.journey_mate.api.FriendRequestApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    RecyclerView requestrecycle;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_notification, container, false);
        requestrecycle = root.findViewById(R.id.notificationlist);
        FriendRequestApi friendRequestApi = new FriendRequestApi();

        NotificationAdaptor adapter = new NotificationAdaptor(getContext(), friendRequestApi.getAllRelation());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        requestrecycle.setLayoutManager(layoutManager);
        requestrecycle.setAdapter(adapter);
        return root;
    }

}
