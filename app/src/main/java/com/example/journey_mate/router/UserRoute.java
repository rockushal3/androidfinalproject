package com.example.journey_mate.router;

import com.example.journey_mate.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserRoute {
    @POST("login")
    Call<User> userLogin(@Body User user);

    @POST("createUser")
    Call<Void> userRegister(@Body User user);

    @GET("checkLogin")
    Call<Void> checkLogin(@Header("Authorization") String auth);

}
