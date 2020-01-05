package com.example.journey_mate.controller.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.journey_mate.R;
import com.example.journey_mate.adaptor.PostAdaptor;


public class Home_Fragment extends Fragment {

    private RecyclerView postview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_home_, container, false);
        postview = root.findViewById(R.id.postlist);
        PostAdaptor adapter = new PostAdaptor(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        postview.setLayoutManager(layoutManager);
        postview.setAdapter(adapter);
        return root;
    }


}
