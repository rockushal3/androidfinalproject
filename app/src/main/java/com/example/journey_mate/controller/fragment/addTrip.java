package com.example.journey_mate.controller.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.journey_mate.R;
import com.example.journey_mate.api.TripApi;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.controller.MyTrip;
import com.example.journey_mate.model.Trip;


public class addTrip extends DialogFragment {
    EditText trip_name, desc,date;
    Button btn_addtrip;
    TripApi tripApi = new TripApi();

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_trip, null);
        //Trip add
        btn_addtrip = view.findViewById(R.id.btn_addtotrip);
        trip_name = view.findViewById(R.id.trip_name);
        desc = view.findViewById(R.id.trip_desc);
        date = view.findViewById(R.id.trip_date);

        //add trip function
        btn_addtrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Trip trip = new Trip(UserApi.loginUserDetail.get_id(),trip_name.getText().toString(),desc.getText().toString(),date.getText().toString());
        if(tripApi.createTrip(trip)){
            reload();
            Toast.makeText(getContext(), "Trip has been added", Toast.LENGTH_SHORT).show();
        }
        else {
            reload();
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
            }
        });

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


