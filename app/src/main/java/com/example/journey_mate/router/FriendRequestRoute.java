package com.example.journey_mate.router;

import com.example.journey_mate.model.FriendRelationResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FriendRequestRoute {
    @GET("getrequest/{id}")
    Call<List<FriendRelationResponce>> getRequest(@Path("id") String id);
}
