package com.example.journey_mate.router;

import com.example.journey_mate.model.FriendRelationResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FriendRequestRoute {
    @GET("getrequest/{id}")
    Call<List<FriendRelationResponce>> getRequest(@Path("id") String id);
    @GET("allfriend/{id}")
    Call<List<FriendRelationResponce>> getFriendList(@Path("id") String id);
    @PUT("acceptfriend/{id}")
    Call<Void> acceptFriend(@Path("id") String id);

    @GET("checkRelation")
    Call<FriendRelationResponce> checkRelation(@Query("user_id_1") String user_id_1, @Query("user_id_2") String user_id_2);


}
