package com.example.journey_mate.router;


import com.example.journey_mate.model.Trip;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TripRoute {

    @POST("createTrip")
    Call<Void> addTrip(@Header("Authorization") String auth,@Body Trip trip);

}