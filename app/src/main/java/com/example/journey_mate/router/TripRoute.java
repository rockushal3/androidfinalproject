package com.example.journey_mate.router;


import com.example.journey_mate.model.SearchResponse;
import com.example.journey_mate.model.Trip;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TripRoute {

    @POST("createTrip")
    Call<Void> addTrip(@Header("Authorization") String auth,@Body Trip trip);

    @GET("search")
    Call<List<SearchResponse>> search(@Query("trip_name") String trip_name);

}