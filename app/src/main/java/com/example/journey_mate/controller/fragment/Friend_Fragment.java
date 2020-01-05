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
import com.example.journey_mate.adaptor.PostAdaptor;
import com.example.journey_mate.controller.profile;

public class Friend_Fragment extends Fragment {

    TextView name;
    CircleImageView profileImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_friend_, container, false);
        name = root.findViewById(R.id.menu_username);
        profileImage = root.findViewById(R.id.profile_image);


        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), profile.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(profileImage,"profileImage");
                pairs[1] = new Pair<View,String>(name,"profileName");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(),
                        pairs);
                startActivity(intent,options.toBundle());
            }
        });


        return root;
    }

}
