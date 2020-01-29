package com.example.journey_mate.controller.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.journey_mate.R;
import com.example.journey_mate.api.PostApi;
import com.example.journey_mate.model.Post;

public class Option extends DialogFragment {

    Button delete_btn, cancel_btn;
    String id;

    public Option(String id) {
        this.id = id;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_option, null);
        delete_btn = view.findViewById(R.id.delete_btn);
        cancel_btn = view.findViewById(R.id.cancel_btn);

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostApi postApi = new PostApi();
                if(postApi.deletePost(id)){
                    Toast.makeText(getContext(), "Post Has Been Deleted", Toast.LENGTH_SHORT).show();
                    dismiss();
                    reload();
                }
            }
        });

        cancel_btn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        }));

        builder.setView(view);
        return builder.create();
    }

    public void reload() {
        Intent intent = getActivity().getIntent();
        getActivity().overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
        startActivity(intent);
    }

}
