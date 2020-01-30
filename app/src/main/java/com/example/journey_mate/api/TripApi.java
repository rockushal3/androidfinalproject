package com.example.journey_mate.api;

import com.example.journey_mate.model.SearchResponse;
import com.example.journey_mate.model.Trip;
import com.example.journey_mate.model.User;
import com.example.journey_mate.router.TripRoute;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class TripApi {
    TripRoute tripRoute =Retro.getInstance()
            .create(TripRoute.class);
    boolean trip_Added = false;

    public boolean createTrip(Trip apitrip){
        Call<Void> userCall = tripRoute.addTrip(Retro.token,apitrip);
        Strict.StrictMode();
        try {
            Response<Void> loginResponse = userCall.execute();
            if(loginResponse.isSuccessful()){
                trip_Added = true;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return trip_Added;
    }

    public List<SearchResponse> searchTrip(String location){
        List<SearchResponse> searchResponses=null;
        Call<List<SearchResponse>> searchcall = tripRoute.search(location);
        Strict.StrictMode();
        try {
            Response<List<SearchResponse>> checkresponse = searchcall.execute();
            if(checkresponse.isSuccessful()){
               searchResponses=checkresponse.body();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return searchResponses;
    }

    public List<Trip> getTripByid(String id){
        List<Trip> triplist=null;
        Call<List<Trip>> searchcall = tripRoute.getTripById(id);
        Strict.StrictMode();
        try {
            Response<List<Trip>> checkresponse = searchcall.execute();
            if(checkresponse.isSuccessful()){
                triplist=checkresponse.body();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return triplist;
    }
}
